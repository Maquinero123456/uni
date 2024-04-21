import numpy as np
e16 = np.random.uniform(size=(6,7))
print("Matriz:")
print(e16)
e16[:,0]=0
e16[:,1]=0
e16[:,-1]=0
e16[:,-2]=0
e16[:,-3]=0
print("Matriz con 0:")
print(e16)