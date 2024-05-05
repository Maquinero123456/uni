import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv('bmw.csv')

plt.xlabel("Price")
plt.hist(df["price"])
plt.tight_layout()
plt.savefig('ej5.png')
