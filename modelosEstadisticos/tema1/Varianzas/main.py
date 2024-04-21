import pandas as pd
import random
import statistics

df = pd.read_excel("datos.xls")
varianza = []

for i in range(5000):
    muestra = df.sample(random.randint(50,700))
    varianza.append(muestra.var().iloc[0])

print(df.var().iloc[0])
print(statistics.mean(varianza))