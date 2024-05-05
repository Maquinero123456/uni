import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv('bmw.csv')

plt.xlabel("Modelos")
plt.ylabel("Frecuencia")
frecuencia = df['model'].value_counts()
frecuencia.plot(kind='bar')
plt.tight_layout()
plt.savefig('ej3.png')
