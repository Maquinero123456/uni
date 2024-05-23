import numpy as np
e15 = np.random.randint(0, 50, size=100)
k = 25
mayores = []
for i in range(e15.shape[0]):
    if(e15[i]>k):
        mayores.append(i)

print("Matriz:")
print(e15)
print("Indices de los numeros mayores que "+str(k)+":")
print(mayores)