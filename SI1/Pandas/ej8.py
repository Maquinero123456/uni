import pandas as pd
df = pd.read_csv("bmw.csv")

print("Filas: "+str(df.shape[0])+" Columnas: "+str(df.shape[1]))
print("Antepenultima fila: ")
print(df.iloc[df.shape[0]-3,:])