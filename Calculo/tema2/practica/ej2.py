import numpy as np
import matplotlib.pyplot as plt

# 1. Malla
x = np.linspace(-5, 5, 250)
y = np.linspace(-5, 5, 250)
X, Y = np.meshgrid(x, y)

# 2. Campo escalar
Z = X**2 + 4*Y**2

plt.figure()
plt.imshow(Z, extent=[-5,5,-5,5], origin='lower', cmap='viridis')
plt.colorbar()
plt.title('f(x,y) = x^2 + 4y^2')
plt.xlabel('x')
plt.ylabel('y')

# 3. Curva de nivel N(f,1): x^2 + 4y^2 = 1
t = np.linspace(0, 2*np.pi, 200)
x_curve = np.cos(t)
y_curve = (1/2)*np.sin(t)

plt.plot(x_curve, y_curve, 'r--', label='Nivel f=1')

plt.legend()
plt.show()