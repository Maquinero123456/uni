import numpy as np
e14 = np.random.normal(size=(7,5))
negativos = np.where(e14>=0, 1,0)
print("Matriz:")
print(e14)
print("Matriz sin negativos:")
print(np.abs(e14*negativos))