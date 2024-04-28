import pandas as pd
df = pd.read_csv("bmw.csv")

serie=pd.Series(df["engineSize"])
print("Media: "+str(serie.mean()))
print("Desviacion estandar: "+str(serie.std()))
print("Minimo: "+str(serie.min()))
print("Maximo: "+str(serie.max()))