import numpy as np
ejercicio7 = np.arange(0,16,1)
def arrayABidimensional(array: np.ndarray):
    raiz = np.sqrt(array.size)
    if raiz.is_integer():
        raiz = int(raiz)
        print(array.reshape(raiz, raiz))
    else:
        print("No se puede crear una matriz cuadrada desde ese array")
print("Matriz:")
print(ejercicio7)
print("Matriz transformada:")
arrayABidimensional(ejercicio7)