import pandas as pd
df = pd.read_csv("bmw.csv")

df.loc[df.shape[0]] = [" 3 Series", 2023, 22572, "Automatic",  74120, "Diesel",  160, 58.4,  2.0]
print(df)