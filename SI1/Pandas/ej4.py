import pandas as pd
df = pd.read_csv("bmw.csv")

print(pd.Series(df["mileage"]).sample(frac=0.4))