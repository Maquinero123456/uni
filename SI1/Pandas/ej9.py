import pandas as pd
df = pd.read_csv("bmw.csv")

df2 = df[["mileage", "price", "mpg"]]
print("Nuevo dataframe:")
print(df2)
print("Sample:")
print(df2.sample(frac=0.2))