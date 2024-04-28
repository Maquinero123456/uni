import pandas as pd
df = pd.read_csv("bmw.csv")

serie = pd.Series(df.loc[:, "mileage"])
print(serie[serie%7==0])