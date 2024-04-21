import numpy as np
ejercicio5 = np.arange(0, 20, 1).reshape(5, 4)
print("Matriz:")
print(ejercicio5)
print("Media:")
print(np.mean(ejercicio5, axis=0))