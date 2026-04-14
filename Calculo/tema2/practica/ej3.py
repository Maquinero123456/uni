import sympy as sp

# Variables
x, y = sp.symbols('x y')

# Función
f = sp.exp(-x**2 - y**2)

# 1. Derivadas parciales
fx = sp.diff(f, x)
fy = sp.diff(f, y)

print("Derivadas parciales:")
print("fx =", fx)
print("fy =", fy)

# 2. Derivadas de segundo orden
fxx = sp.diff(fx, x)
fyy = sp.diff(fy, y)
fxy = sp.diff(fx, y)

print("\nDerivadas de segundo orden:")
print("fxx =", fxx)
print("fyy =", fyy)
print("fxy =", fxy)

# 3. Hessiana
H = sp.hessian(f, (x, y))

print("\nMatriz Hessiana:")
sp.pprint(H)

# 4. Puntos críticos
criticos = sp.solve([fx, fy], (x, y))

print("\nPuntos críticos:")
print(criticos)

print("\nClasificación:")
print("El punto (0,0) es un máximo (campana gaussiana)")