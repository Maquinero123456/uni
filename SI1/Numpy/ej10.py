import numpy as np
e10 = np.random.randint(0, 100, size=12).reshape(4,3)
print("Matriz:")
print(e10)
media = np.mean(e10, axis=0)
std = np.std(e10, axis=0)
fil, col  = e10.shape
for i in range(fil):
    for j in range(col):
        e10[i,j] = (e10[i,j]-media[j])/std[j]

print("Matriz normalizada:")
print(e10)