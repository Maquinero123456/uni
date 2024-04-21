import numpy as np
ejercicio2 = np.arange(0, 20, 1).reshape(5, 4)
print("Matriz:")
print(ejercicio2)
print("Maximos:")
print(np.max(ejercicio2, axis=1))