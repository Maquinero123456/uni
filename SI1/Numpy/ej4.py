import numpy as np
ejercicio4 = np.arange(0, 20, 1).reshape(5, 4)
ejercicio4Invertido = np.flip(ejercicio4, axis=0)
print("Matriz normal:")
print(ejercicio4)
print("Matriz invertida:")
print(ejercicio4Invertido)