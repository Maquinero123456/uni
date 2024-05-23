import pandas as pd
df = pd.read_csv("bmw.csv")

serie = pd.Series(df.loc[:, "mileage"])
print(serie[serie.index%7==0])