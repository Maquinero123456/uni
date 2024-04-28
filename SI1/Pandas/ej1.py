import pandas as pd
df = pd.read_csv("bmw.csv")

print(df.iloc[0:10, :])