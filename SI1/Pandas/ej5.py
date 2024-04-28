import pandas as pd
df = pd.read_csv("bmw.csv")

serie = pd.Series(df["mileage"])
print(serie[serie<20000])