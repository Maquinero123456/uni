import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv('bmw.csv')
plt.xlabel("Price")
plt.ylabel("Mileage")
plt.scatter(df['price'], df['mileage'],c=df['mpg'])
plt.tight_layout()
plt.savefig('ej4.png')
