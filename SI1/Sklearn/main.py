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
from sklearn.metrics import RocCurveDisplay
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
print("Dataframe:")
print(df)

#Dataframe como X e Y
x = df.iloc[:, :8].values
y = df.iloc[:, 9].values
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=0)
skf = StratifiedKFold(n_splits=10)

#Bayes
print("################## Naive Bayes Gaussiano ##################")
gnb = GaussianNB()
gnb = gnb.fit(x_train, y_train)
y_pred = gnb.predict(x_test)
print("Cantidad de predicciones mal hechas de %d valores : %d" % (x_test.shape[0], (y_test != y_pred).sum()))
print("Accuracy "+str((y_test != y_pred).sum()*100/x_test.shape[0])+"%")

#KNeighbours
print("################## Nearest Neighbor ##################")
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
print("Cantidad de predicciones mal hechas de %d valores : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#Decision Tree
print("################## Decision Tree ##################")
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

plot_tree(treeMejor)
plt.show()

#Support Vector Machine
print("################## SVC ##################")
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
print("Cantidad de predicciones mal hechas de %d valores : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#Graficas
print("################## Graficas ##################")
y_score_Gaussian = gnb.predict_proba(x_test)
y_score_vecinos = vecinosMejor.predict_proba(x_test) 
y_score_tree = treeMejor.predict_proba(x_test)
y_score_svc = svcMejor.predict_proba(x_test)


target_names = [1,2,3,4,5,6,7]
label_binarizer = LabelBinarizer().fit(y_train)
y_onehot_test = label_binarizer.transform(y_test)
n_classes = y_onehot_test.shape[1]
colors = cycle(["aqua", "darkorange", "cornflowerblue", "green", "blue", "yellow", "red", "brown"])

def plots(y_score, y_onehot_test, n_classes, texto):
    colors = cycle(["aqua", "darkorange", "cornflowerblue", "green", "blue", "yellow", "red", "brown"])
    #Gaussian Metrica
    fpr, tpr, roc_auc = dict(), dict(), dict()
    for i in range(n_classes):
        fpr[i], tpr[i], _ = roc_curve(y_onehot_test[:, i], y_score[:, i])
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

    fig, ax = plt.subplots(figsize=(10, 10))
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
            y_score[:, class_id],
            name=f"ROC curve for {target_names[class_id]}",
            color=color,
            ax=ax,
            plot_chance_level=(class_id == 2),
        )

    _ = ax.set(
        xlabel="False Positive Rate",
        ylabel="True Positive Rate",
        title="Extension of Receiver Operating Characteristic\nto One-vs-Rest multiclass("+texto+")",
    )
    plt.tight_layout()
    plt.show()

#Gaussian
plots(y_score_Gaussian, y_onehot_test, n_classes, "Naive Bayes Gaussiano")
#Vecinos
plots(y_score_vecinos, y_onehot_test, n_classes, "Nearest Neighbor")
#Tree
plots(y_score_tree, y_onehot_test, n_classes, "Decision Tree")
#SVC
plots(y_score_svc, y_onehot_test, n_classes, "SVC")

#Metricas comparado un par de valores
def plot_comparar(class_id, y_score_gaussian, y_score_vecinos, y_score_tree, y_score_svc, y_onehot_test):
    fig, ax = plt.subplots(figsize=(6, 6))
    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_gaussian[:, class_id],
        name="Gaussian",
        color="red",
        plot_chance_level=True,
        ax=ax
    )
    _ = ax.set(
        xlabel="False Positive Rate",
        ylabel="True Positive Rate",
        title="Naive Bayes Gaussiano vs Nearest Neighbor vs Decision Tree vs SVC("+str(class_id)+")",
    )

    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_vecinos[:, class_id],
        name="Vecinos",
        color="darkorange",
        ax=ax
    )

    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_tree[:, class_id],
        name="Tree",
        color="blue",
        ax=ax
    )

    RocCurveDisplay.from_predictions(
        y_onehot_test[:, class_id],
        y_score_svc[:, class_id],
        name="SVC",
        color="green",
        ax=ax
    )
    plt.show()

plot_comparar(1, y_score_Gaussian, y_score_vecinos, y_score_tree, y_score_svc, y_onehot_test)
plot_comparar(0, y_score_Gaussian, y_score_vecinos, y_score_tree, y_score_svc, y_onehot_test)