import numpy as np
e11 = np.random.randint(0, 100, size=12).reshape(4,3)
print("Matriz:")
print(e11)
media = np.mean(e11, axis=1)
std = np.std(e11, axis=1)
fil, col  = e11.shape
for i in range(fil):
    for j in range(col):
        e11[i,j] = (e11[i,j]-media[i])/std[i]
print("Matriz normalizada:")
print(e11)