import pandas as pd
df = pd.read_csv("bmw.csv")

df = df.to_numpy()
print(type(df))