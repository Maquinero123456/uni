import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv('bmw.csv')

x = np.arange(start=0, stop=2*np.pi, step=0.1)

plt.plot(x, np.sin(x) + np.sin(x))
plt.show()
