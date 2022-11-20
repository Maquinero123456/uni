from pb import pb
import json
import Codigo_RSA as cr
import random as rand
import time
import funciones_aes as aes

def bob():
    print("BOB INICIADO")
    bob = pb()
    print("BOB: ---PASO 2---")
    time.sleep(10)
    try:
        bob.conectar("localhost", 3003)
    except:
        print("BOB: Trent no disponible")
        exit(-1)
    kbt = aes.crear_AESKey()
    #Ciframos la clave junto a la cadena "bob"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Bob", kbt.hex()]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de bob
    firmado = bob.firmar(str(kbt))
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    bob.enviar(bytes(enviar.encode("utf-8")))
    bob.cerrar()
    
    print("BOB: ---PASO 5---")
    bob.escuchar()
    clave = bob.recibir()
    clave = clave.decode("utf-8")
    clave = json.loads(clave)
    mac = bytes(clave[2], "latin-1")
    nonce = bytes(clave[3], "latin-1")
    ekbt = clave[0]
    ekab = bytes.fromhex(clave[1])
    
    
    ekbt = json.loads(ekbt)
    datosEKBT = bytes.fromhex(ekbt[0])
    macEKBT = bytes(ekbt[1], "latin-1")
    nonceEKBT = bytes(ekbt[2], "latin-1")
    datosEKBT = aes.descifrarAES_GCM(kbt, nonceEKBT, datosEKBT, macEKBT)
    datosEKBT = datosEKBT.decode("utf-8")
    datosEKBT = json.loads(datosEKBT)
    ts = datosEKBT[0]
    kab = bytes.fromhex(datosEKBT[1])
    
    datosKAB = aes.descifrarAES_GCM(kab, nonce, ekab, mac)
    datosKAB = datosKAB.decode("utf-8")
    datosKAB = json.loads(datosKAB)
    
    if(datosKAB[0]=="Alice" and datosKAB[1]==ts):
        print("BOB: Ok")
    else:
        print("BOB: Not ok")
    
    print("BOB: ---PASO 6---")
    datosAlice, macAlice, nonceAlice = aes.cifrarAES_GCM(aes.iniciarAES_GCM(kab), json.dumps([ts+1]).encode("utf-8"))
    bob.enviar(bytes(json.dumps([datosAlice.hex(), macAlice.decode("latin-1"), nonceAlice.decode("latin-1")]).encode("utf-8")))
    
    bob.cerrar()
    
    