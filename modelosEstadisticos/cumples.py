import random

aleatorio = random
cumplesRepetidos = 0
numRepeticiones = 1000000
for i in range(0,numRepeticiones):
    cumples = []
    for j in range(0,12):
        cumples.append(aleatorio.randint(1,365))
    if(len(set(cumples))<12):
        cumplesRepetidos+=1
    cumples.clear()

print(cumplesRepetidos/numRepeticiones)