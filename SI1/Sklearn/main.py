import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split, StratifiedKFold
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.svm import SVC
import matplotlib.pyplot as plt
from sklearn.model_selection import GridSearchCV
from sklearn.preprocessing import LabelBinarizer
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.model_selection import cross_val_score
from itertools import cycle
from sklearn.metrics import auc, roc_curve

columnas = ["Id_number", "RI", "Na", "Mg", "Al", "Si", "K", "Ca", "Ba", "Fe", "Type_of_glass"]
#Type of glass: (class attribute)
#     -- 1 building_windows_float_processed
#     -- 2 building_windows_non_float_processed
#     -- 3 vehicle_windows_float_processed
#     -- 4 vehicle_windows_non_float_processed (none in this database)
#     -- 5 containers
#     -- 6 tableware
#     -- 7 headlamps


df = pd.read_csv('data/glass.data', names = columnas)
df = df.drop('Id_number', axis=1)
print(df)

#Dataframe como X e Y
x = df.iloc[:, :8].values
y = df.iloc[:, 9].values
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=0)
skf = StratifiedKFold(n_splits=10)
valores = skf.split(x, y)


#Bayes
gnb = GaussianNB()
y_pred = gnb.fit(x_train, y_train).predict(x_test)
print("Number of mislabeled points out of a total %d points : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#KNeighbours
#bestNNeigh = 0
#best_pred = 0 
#for i in range(1, 100):
#    neigh = KNeighborsClassifier(n_neighbors=i)
#    neigh.fit(x_train, y_train)
#    y_pred = (y_test == neigh.predict(x_test)).sum()
#    if y_pred > best_pred:
#        bestNNeigh = i
#        best_pred = y_pred
#print("Mejor cantidad de vecinos: ", bestNNeigh)
gsVecinos = GridSearchCV(
    KNeighborsClassifier(),
    param_grid={"n_neighbors":range(1,101)},
    scoring='accuracy',
    cv = skf
)
gsVecinos.fit(x_train, y_train)
print("El mejor numero de vecinos es: ",gsVecinos.best_params_['n_neighbors'])
print("Mejor precision: ", gsVecinos.best_score_)
vecinosMejor = gsVecinos.best_estimator_
y_pred = vecinosMejor.predict(x_test)
print("Cantidad de preddicciones mal hechas de %d valores : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#Decision Tree
#Mejor: 0.7248366013071895
parametros = {
    "criterion" : ["gini", "entropy", "log_loss"],
    "splitter" : ["best", "random"],
    "max_depth" : [None],
    "min_samples_split": [2], 
    "min_samples_leaf": [1], 
    "max_features": [10],
    "class_weight": [None, "balanced"], 
    "ccp_alpha": [0.0, 0.1, 0.2, 0.5]
}

gs = GridSearchCV(
    DecisionTreeClassifier(random_state=1),
    param_grid=parametros,
    scoring='accuracy',
    cv = skf
)
gs.fit(x_train, y_train)
print("Mejor parametros:", gs.best_params_)
print("Mejor precisión: ", gs.best_score_)
treeMejor = gs.best_estimator_
y_pred = treeMejor.predict(x_test)
print("Cantidad de predicciones mal hechas de %d valores : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#TODO quitar comentarios
#plot_tree(treeMejor)
#plt.show()

#Support Vector Machine
parametrosSVC = {
    "kernel" : ['poly'],
    "gamma" : ['scale', 'auto'],
    "degree" : [25,30,35], 
    "shrinking": [True, False], 
    "probability": [True, False],
    "decision_function_shape": ['ovo', 'ovr']
}
#Mejor 0.7071895424836602
gsSVC = GridSearchCV(
    SVC(random_state=1),
    param_grid=parametrosSVC,
    scoring='accuracy',
    cv = skf
)
gsSVC.fit(x_train, y_train)
print("Mejor parametros:", gsSVC.best_params_)
print("Mejor precisión: ", gsSVC.best_score_)
svcMejor = gsSVC.best_estimator_
y_pred = svcMejor.predict(x_test)
print("Cantidad de preddicciones mal hechas de %d valores : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#Metricas
y_score_Gaussian = GaussianNB().fit(x_train, y_train).predict_proba(x_test)
y_score_vecinos = vecinosMejor.predict_proba(x_test) 
y_score_tree = treeMejor.predict_proba(x_test)
y_score_svc = svcMejor.predict_proba(x_test)


target_names = [1,2,3,4,5,6,7]
label_binarizer = LabelBinarizer().fit(y_train)
y_onehot_test = label_binarizer.transform(y_test)
n_classes = y_onehot_test.shape[1]
class_of_interest = 1
colors = cycle(["aqua", "darkorange", "cornflowerblue", "green", "blue", "yellow", "red", "brown"])

#Gaussian Metrica
fpr, tpr, roc_auc = dict(), dict(), dict()
for i in range(n_classes):
    fpr[i], tpr[i], _ = roc_curve(y_onehot_test[:, i], y_score_Gaussian[:, i])
    roc_auc[i] = auc(fpr[i], tpr[i])

fpr_grid = np.linspace(0.0, 1.0, 1000)

# Interpolate all ROC curves at these points
mean_tpr = np.zeros_like(fpr_grid)

for i in range(n_classes):
    mean_tpr += np.interp(fpr_grid, fpr[i], tpr[i])  # linear interpolation

# Average it and compute AUC
mean_tpr /= n_classes

fpr["macro"] = fpr_grid
tpr["macro"] = mean_tpr
roc_auc["macro"] = auc(fpr["macro"], tpr["macro"])

fpr["micro"], tpr["micro"], _ = roc_curve(y_onehot_test.ravel(), y_score_Gaussian.ravel())
roc_auc["micro"] = auc(fpr["micro"], tpr["micro"])

fig, ax = plt.subplots(figsize=(6, 6))
plt.plot(
    fpr["micro"],
    tpr["micro"],
    label=f"micro-average ROC curve (AUC = {roc_auc['micro']:.2f})",
    color="deeppink",
    linestyle=":",
    linewidth=4,
)
plt.plot(
    fpr["macro"],
    tpr["macro"],
    label=f"macro-average ROC curve (AUC = {roc_auc['macro']:.2f})",
    color="navy",
    linestyle=":",
    linewidth=4,
)
for class_id, color in zip(range(n_classes), colors):
    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_Gaussian[:, class_id],
        name=f"ROC curve for {target_names[class_id]}",
        color=color,
        ax=ax,
        plot_chance_level=(class_id == 2),
    )

_ = ax.set(
    xlabel="False Positive Rate",
    ylabel="True Positive Rate",
    title="Extension of Receiver Operating Characteristic\nto One-vs-Rest multiclass",
)
plt.show()


#Vecinos Metrica
fig, ax = plt.subplots(figsize=(6, 6))
for class_id, color in zip(range(n_classes), colors):
    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_vecinos[:, class_id],
        name=f"ROC curve for {target_names[class_id]}",
        color=color,
        ax=ax,
        plot_chance_level=(class_id == 2),
    )

_ = ax.set(
    xlabel="False Positive Rate",
    ylabel="True Positive Rate",
    title="Extension of Receiver Operating Characteristic\nto One-vs-Rest multiclass",
)
plt.show()


#tree Metrica
fig, ax = plt.subplots(figsize=(6, 6))
for class_id, color in zip(range(n_classes), colors):
    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_tree[:, class_id],
        name=f"ROC curve for {target_names[class_id]}",
        color=color,
        ax=ax,
        plot_chance_level=(class_id == 2),
    )

_ = ax.set(
    xlabel="False Positive Rate",
    ylabel="True Positive Rate",
    title="Extension of Receiver Operating Characteristic\nto One-vs-Rest multiclass",
)
plt.show()


#svc Metrica
fig, ax = plt.subplots(figsize=(6, 6))
for class_id, color in zip(range(n_classes), colors):
    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_svc[:, class_id],
        name=f"ROC curve for {target_names[class_id]}",
        color=color,
        ax=ax,
        plot_chance_level=(class_id == 2),
    )

_ = ax.set(
    xlabel="False Positive Rate",
    ylabel="True Positive Rate",
    title="Extension of Receiver Operating Characteristic\nto One-vs-Rest multiclass",
)

plt.show()