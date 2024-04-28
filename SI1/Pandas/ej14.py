import pandas as pd
df = pd.read_csv("bmw.csv")

print(df.groupby("year")["mileage"].mean())