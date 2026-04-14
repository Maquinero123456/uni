import numpy as np
import matplotlib.pyplot as plt

# 1. Crear malla 2D
x = np.linspace(-1, 1, 100)
y = np.linspace(-1, 1, 100)
X, Y = np.meshgrid(x, y)

# 2. Campo escalar
Z = np.exp(-(X**2 + Y**2))

plt.figure()
plt.imshow(Z, extent=[-1,1,-1,1], origin='lower', cmap='cividis')
plt.colorbar()
plt.title('f(x,y) = exp(-(x^2 + y^2))')
plt.xlabel('x')
plt.ylabel('y')
plt.show()

# 3. Curvas de nivel (contour)
plt.figure()
cont = plt.contour(X, Y, Z, levels=3)
plt.clabel(cont)

plt.title('Curvas de nivel (3 niveles)')
plt.xlabel('x')
plt.ylabel('y')
plt.grid()
plt.show()

# 4. contourf (relleno)
plt.figure()
plt.contourf(X, Y, Z, levels=3, cmap='cividis')
plt.colorbar()

plt.title('Curvas de nivel rellenas (contourf)')
plt.xlabel('x')
plt.ylabel('y')
plt.grid()
plt.show()

# Diferencia:
print("\nDiferencia:")
print("contour -> solo líneas")
print("contourf -> regiones rellenas de color")