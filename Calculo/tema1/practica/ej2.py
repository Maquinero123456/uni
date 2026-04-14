import numpy as np
import matplotlib.pyplot as plt

# 1. Crear array
x = np.linspace(-10, 10, 160)

# 2. Calcular funciones
y1 = np.exp(x)
y2 = np.exp(-x)

# 3. Graficar
plt.figure()
plt.plot(x, y1, label='exp(x)')
plt.plot(x, y2, label='exp(-x)')

plt.xlabel('x')
plt.ylabel('y')
plt.title('exp(x) vs exp(-x)')
plt.legend()
plt.grid()

plt.show()