import pandas as pd
df = pd.read_csv("bmw.csv")

df = df[df["mileage"]<10000]
df = df[df["mpg"]>40]
print(df)