from math import prod
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
from sklearn.model_selection import cross_val_score

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
print(gsVecinos.best_estimator_)

#Decision Tree
parametros = {
    "criterion" : ["gini", "entropy", "log_loss"],
    "splitter" : ["best", "random"],
    "max_depth" : [None, 2, 5, 15, 20, 30]
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
best_model = gs.best_estimator_
y_pred = best_model.predict(x_test)
print("Number of mislabeled points out of a total %d points : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#Support Vector Machine
parametrosSVC = {
    "kernel" : ['linear', 'poly', 'rbf', 'sigmoid'],
    "gamma" : ['scale', 'auto'],
    "degree" : [2, 5, 15, 20, 30]
}

gsSVC = GridSearchCV(
    SVC(random_state=1),
    param_grid=parametrosSVC,
    scoring='accuracy',
    cv = skf
)
gsSVC.fit(x_train, y_train)
print("Mejor parametros:", gsSVC.best_params_)
print("Mejor precisión: ", gsSVC.best_score_)
best_model = gsSVC.best_estimator_
y_pred = best_model.predict(x_test)
print("Number of mislabeled points out of a total %d points : %d" % (x_test.shape[0], (y_test != y_pred).sum()))

#Metricas
y_score = GaussianNB().fit(x_train, y_train).predict_proba(x_test)

label_binarizer = LabelBinarizer().fit(y_train)
y_onehot_test = label_binarizer.transform(y_test)
class_of_interest = 1
class_id = np.flatnonzero(label_binarizer.classes_ == class_of_interest)[0]
display = RocCurveDisplay.from_predictions(
    y_onehot_test[:, class_id],
    y_score[:, class_id],
    name=f"{class_of_interest} vs the rest",
    color="darkorange",
    plot_chance_level=True,
)
_ = display.ax_.set(
    xlabel="False Positive Rate",
    ylabel="True Positive Rate",
    title="One-vs-Rest ROC curves:\nVirginica vs (Setosa & Versicolor)",
)
plt.show()