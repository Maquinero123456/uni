import numpy as np
e9 = np.random.randint(0, 10, size=20)

def contar(array: np.ndarray):
    numeros, valores = np.unique(array, return_counts=True)
    result = {}
    for i in range(len(numeros)):
        result[numeros[i]]=valores[i]
    return result
print("Matriz:")
print(e9)
print("Unicos:")
print(contar(e9))