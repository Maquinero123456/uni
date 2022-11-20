from pa import pa
import random as rand
import Codigo_RSA as cr
import json
import time
import funciones_aes as aes

def main():
    alice = pa()
    print("---PASO 1---")
    alice.conectar("localhost", 3003)
    print("Conectado a Trent")
    #Genero la clave simetrica
    kat = aes.crear_AESKey()
    #Ciframos la clave junto a la cadena "Alice"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Alice", kat.hex()]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de Alice
    firmado = alice.firmar(str(kat))
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    print("Enviamos el paquete del paso 1")
    alice.enviar(bytes(enviar.encode("utf-8")))
    print("Cerramos conexion para dejar paso Bob")
    alice.cerrar()
    
    #Esperamos que Bob genere su clave
    time.sleep(10)
    print("---PASO 3---")
    #Alice se conecta con trent porque quiere hablar con bob
    try:
        alice.conectar("localhost", 3003)
    except:
        print("Trent no esta disponibe")
        exit(-1)
        
    #Envia mensaje
    alice.enviar(bytes(json.dumps(["Alice", "Bob"]).encode("utf-8")))
    #Recibe la clave para hablar con Bob
    print("---PASO 4---")
    clave = alice.recibir()
    clave = clave.decode("utf-8")
    clave = json.loads(clave)
    datosCifrados = bytes.fromhex(clave[0])
    mac = bytes(clave[1], 'latin-1')
    nonce = bytes(clave[2], 'latin-1')
    descifrar = aes.descifrarAES_GCM(kat, nonce, datosCifrados, mac)
    try:
        descifrar = json.loads(descifrar)
    except:
        print("Mac no valida")
        exit(-1)
    
    
    ts = descifrar[0]
    kab = bytes.fromhex(descifrar[1])
    paraBob = descifrar[2]
    print("Cerramos conexion con Trent y nos conectamos a Bob")
    #Cerramos conexion
    alice.cerrar()
    print("---PASO 5---")
    alice.conectar("localhost", 3002)
    
    cifrar = aes.iniciarAES_GCM(kab)
    datosCifrados, mac, nonce = aes.cifrarAES_GCM(cifrar, json.dumps(["Alice", ts]).encode("utf-8"))
    enviarABob = json.dumps([paraBob, datosCifrados.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])
    alice.enviar(bytes(enviarABob.encode("utf-8")))
    print("---PASO 6---")
    ekab = alice.recibir()
    ekab = ekab.decode("utf-8")
    ekab = json.loads(ekab)
    
    datosEKAB = bytes.fromhex(ekab[0])
    macEKAB = bytes(ekab[1], "latin-1")
    nonceEKAB = bytes(ekab[2], "latin-1")
    
    datosEKAB = aes.descifrarAES_GCM(kab, nonceEKAB, datosEKAB, macEKAB)
    datosEKAB = datosEKAB.decode("utf-8")
    datosEKAB = json.loads(datosEKAB)
    
    if(ts+1 == datosEKAB[0]):
        print("TS CORRECTO")
    else:
        print("TS INCORRECTO")
        exit(-1)
    
    
    
main()