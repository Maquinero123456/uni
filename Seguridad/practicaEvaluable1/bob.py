from pb import pb
import json
import Codigo_RSA as cr
import random as rand
import time
import funciones_aes as aes
from termcolor import cprint
def bob():
    cprint("---BOB INICIADO---", "green")
    bob = pb()
    cprint("BOB: ---PASO 2---", "green")
    time.sleep(5)
    try:
        bob.conectar("localhost", 3003)
    except:
        cprint("BOB: Trent no disponible", "red")
        exit(-1)
    kbt = aes.crear_AESKey()
    #Ciframos la clave junto a la cadena "bob"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Bob", kbt.hex()]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de bob
    firmado = bob.firmar(str(kbt))
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    cprint("BOB: Envio Kbt a Trent", "green")
    bob.enviar(bytes(enviar.encode("utf-8")))
    bob.cerrar()
    
    cprint("BOB: ---PASO 5---", "green")
    cprint("BOB: Espero conexion de Alice", "green")
    bob.escuchar()
    cprint("BOB: Recibo Kab y TS de Alice", "green")
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
        cprint("BOB: Mensaje correcto de Alice", "green")
    else:
        cprint("BOB: Mensaje no correcto de Alice", "red")
        exit(-1)
    
    cprint("BOB: ---PASO 6---", "green")
    cprint("BOB: Envio a Alice TS+1", "green")
    datosAlice, macAlice, nonceAlice = aes.cifrarAES_GCM(aes.iniciarAES_GCM(kab), json.dumps([ts+1]).encode("utf-8"))
    bob.enviar(bytes(json.dumps([datosAlice.hex(), macAlice.decode("latin-1"), nonceAlice.decode("latin-1")]).encode("utf-8")))
    
    bob.cerrar()
    cprint("---BOB FINALIZADO---", "green")
    
    