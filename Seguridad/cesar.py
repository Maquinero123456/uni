def cesar(n, key):
    respuesta=""
    for i in n:
        aux = ord(i)+key
        if(aux>122):
            aux=97+(aux-122)
        respuesta+=chr(aux)
    return respuesta

def uncesar(n, key):
    respuesta=""
    for i in n:
        aux = ord(i)-key
        if(aux<97):
            aux=122-(97-aux)
        respuesta+=chr(aux)
    return respuesta

def cesarMayusculas(n, key):
    respuesta=""
    for i in n:
        aux = ord(i)+key
        if(ord(i)<=90 and aux>90):
            aux=65+(aux-90)
        elif(aux>122):
            aux=97+(aux-122)
        respuesta+=chr(aux)
    return respuesta

def uncesarMayusculas(n, key):
    respuesta=""
    for i in n:
        aux = ord(i)-key
        if(aux<65):
            aux=90-(65-aux)
        elif(ord(i)>=95 and aux<95):
            aux=122-(95-aux)
        respuesta+=chr(aux)
    return respuesta


a=cesar('elpatiodemicasaesparticular', 3)
print(a)
print(uncesar(a, 3))

print(uncesarMayusculas(cesarMayusculas('ElpatiodemiCasaesparticular', 3),3))

