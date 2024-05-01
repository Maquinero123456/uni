import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv('bmw.csv')

x = np.linspace(0, 2*np.pi, 100)

plt.plot(x, np.sin(x) + np.sin(x))
plt.show()
