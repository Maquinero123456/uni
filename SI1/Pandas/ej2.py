import pandas as pd
df = pd.read_csv("bmw.csv")

print(pd.Series(df.loc[:, "year"]))