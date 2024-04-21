import numpy as np
e12 = np.random.randint(1, 10, size=25).reshape(5,5)
print("Matriz:")
print(e12)
a = np.argsort(e12[:,0])
e12 = e12[a, :]
print("Matriz ordenada:")
print(e12)