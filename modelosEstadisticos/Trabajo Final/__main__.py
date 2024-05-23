from funciones import *
import matplotlib.pyplot as plt

def exp12():
    print("Experimento 1: 6 menores")
    exp1V, exp1P = probabilidad(10000, dadosXmenores, 10, 8, 6)
    printProb(exp1V, exp1P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp1P[0]+exp1P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp1P[2:4]))+"%")

    print("Experimento 2: 6 mayores")
    exp2V, exp2P = probabilidad(10000, dadosXmayores, 10, 8, 6)
    printProb(exp2V, exp2P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp2P[0]+exp2P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp2P[2:4]))+"%")

    plot(exp1P, exp2P, "Exp1", "Exp2")

def exp34():
    print("Experimento 3: 6 menores 9 dados")
    exp3V, exp3P = probabilidad(10000, dadosXmenores, 10, 9, 6)
    printProb(exp3V, exp3P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp3P[0]+exp3P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp3P[2:4]))+"%")

    print("Experimento 4: 6 mayores 9 dados")
    exp4V, exp4P = probabilidad(10000, dadosXmayores, 10, 9, 6)
    printProb(exp4V, exp4P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp4P[0]+exp4P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp4P[2:4]))+"%")

    plot(exp3P, exp4P, "Exp3", "Exp4")

def exp56():
    print("Experimento 5: 7 dados d10")
    exp5V, exp5P = probabilidadSinOrdenar(10000, dados, 10, 7)
    printProb(exp5V, exp5P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp5P[0]+exp5P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp5P[2:4]))+"%")

    print("Experimento 6: 10 dados d7")
    exp6V, exp6P = probabilidadSinOrdenar(10000, dados, 7, 10)
    printProb(exp6V, exp6P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp6P[0]+exp6P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp6P[2:4]))+"%")

    plot(exp5P, exp6P, "Exp5", "Exp6")

def exp78():
    print("Experimento 7: 8 menores 10 dados")
    exp7V, exp7P = probabilidad(10000, dadosXmenores, 10, 10, 8)
    printProb(exp7V, exp7P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp7P[0]+exp7P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp7P[2:4]))+"%")

    print("Experimento 8: 8 mayores 10 dados")
    exp8V, exp8P = probabilidad(10000, dadosXmayores, 10, 10, 8)
    printProb(exp8V, exp8P)
    print("Probabilidad de que valor sea menor a 0: "+str(exp8P[0]+exp8P[1])+"%")
    print("Probabilidad de que valor sea mayoro igual a 0: "+str(sum(exp8P[2:4]))+"%")

    plot(exp7P, exp8P, "Exp7", "Exp8")


if __name__ == "__main__":
    exp12()
    exp34()
    exp56()
    exp78()