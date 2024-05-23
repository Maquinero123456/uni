import random
import matplotlib.pyplot as plt

def dadosXmenores(dado: int, numeroDados: int, numMirar: int):
    resultados = []
    for i in range(numeroDados):
        resultados.append(random.Random().randint(1, dado))
        
    resultados.sort()
    return sum(resultados[0:numMirar])

def dadosXmayores(dado: int, numeroDados: int, numMirar: int):
    resultados = []
    for i in range(numeroDados):
        resultados.append(random.Random().randint(1, dado))
    resultados.sort(reverse=True)
    return sum(resultados[0:numMirar])

def probabilidad(ciclos: int, func, dado, numeroDados, numMirar):
    variable = [0,0,0,0,0]
    for i in range(ciclos):
        valor = func(dado, numeroDados, numMirar)
        if valor>=6 and valor<=18:
            variable[0] = variable[0]+1
        elif valor>=19 and valor<=30:
            variable[1] = variable[1]+1
        elif valor>=31 and valor<=42:
            variable[2] = variable[2]+1
        elif valor>=42 and valor<=53:
            variable[3] = variable[3]+1
        else:
            variable[4] = variable[4]+1
    probabilidades = [i/ciclos*100 for i in variable]
    return variable, probabilidades

def dados(dado: int, numeroDados: int):
    resultados = []
    for i in range(numeroDados):
        resultados.append(random.Random().randint(1, dado))
    resultados.sort()
    return sum(resultados)

def probabilidadSinOrdenar(ciclos: int, func, dado, numeroDados):
    variable = [0,0,0,0,0]
    for i in range(ciclos):
        valor = func(dado, numeroDados)
        if valor>=6 and valor<=18:
            variable[0] = variable[0]+1
        elif valor>=19 and valor<=30:
            variable[1] = variable[1]+1
        elif valor>=31 and valor<=42:
            variable[2] = variable[2]+1
        elif valor>=42 and valor<=53:
            variable[3] = variable[3]+1
        else:
            variable[4] = variable[4]+1
    probabilidades = [i/ciclos*100 for i in variable]
    return variable, probabilidades

def printProb(variable, probabilidades):
    for i in range(-2, 3):
        print(str(i)+": "+str(variable[i+2])+"   "+str(probabilidades[i+2])+"%")
    print("Total: "+str(sum(variable))+"   "+str(sum(probabilidades))+"%")

def plot(prob1, prob2, label1, label2):
    posVariables = [-2, -1, 0, 1, 2]

    plt.plot(posVariables, prob1, 'r', label=label1)
    plt.plot(posVariables, prob2, 'b', label=label2)

    plt.tight_layout()
    plt.legend()
    plt.show()