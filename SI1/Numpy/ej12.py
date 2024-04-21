import numpy as np
e12 = np.random.randint(1, 50000, size=2500).reshape(50,50)
print("Coordenadas maximo:")
print(np.unravel_index(np.argmax(e12), e12.shape))
print("Coordenadas minimo:")
print(np.unravel_index(np.argmin(e12), e12.shape))