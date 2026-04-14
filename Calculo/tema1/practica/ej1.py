import numpy as np

# 1. Definir matriz A y vector b
A = np.array([
    [1, 4, -1],
    [-2, -1, 0],
    [-4, -3, 1]
])

b = np.array([-11, -9, 5])

# 2. Resolver el sistema
sol = np.linalg.solve(A, b)

x, y, z = sol

print("Solución:")
print(f"x = {x}")
print(f"y = {y}")
print(f"z = {z}")

# 3. Comprobación
print("\nComprobación:")

eq1 = x + 4*y - z
eq2 = -2*x - y
eq3 = -4*x - 3*y + z

print(f"Ecuación 1: {eq1} ≈ {-11}")
print(f"Ecuación 2: {eq2} ≈ {-9}")
print(f"Ecuación 3: {eq3} ≈ {5}")