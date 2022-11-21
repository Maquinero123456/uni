from pa import pa
import random as rand
import Codigo_RSA as cr
import json
import time
import funciones_aes as aes
from termcolor import cprint

def alice():
    cprint("---ALICE INICIADA---", "blue")
    alice = pa()
    cprint("ALICE: ---PASO 1---", "blue")
    time.sleep(1)
    #------------------------PASO 1------------------------
    alice.conectar("localhost", 3003)
    cprint("ALICE: Conectado a Trent", "blue")
    cprint("ALICE: Envio Kat a Trent", "blue")
    #Genero la clave simetrica
    kat = aes.crear_AESKey()
    #Ciframos la clave junto a la cadena "Alice"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Alice", kat.hex()]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de Alice
    firmado = alice.firmar(str(kat))
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    alice.enviar(bytes(enviar.encode("utf-8")))
    cprint("ALICE: Cerramos conexion para dejar paso Bob", "blue")
    alice.cerrar()
    
    #Esperamos que Bob genere su clave
    time.sleep(10)
    cprint("ALICE: ---PASO 3---", "blue")
    #Alice se conecta con trent porque quiere hablar con bob
    try:
        cprint("ALICE: Conectamos con Trent", "blue")
        alice.conectar("localhost", 3003)
    except:
        cprint("ALICE: Trent no esta disponibe", "red")
        exit(-1)
        
    #Envia mensaje
    cprint("ALICE: Envimos [\"Alice\", \"Bob\"] a Trent", "blue")
    alice.enviar(bytes(json.dumps(["Alice", "Bob"]).encode("utf-8")))
    #Recibe la clave para hablar con Bob
    cprint("ALICE: ---PASO 4---", "blue")
    cprint("ALICE: Recibimos Kab de Trent", "blue")
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
        cprint("ALICE: Mac no valida", "red")
        exit(-1)
    
    
    ts = descifrar[0]
    kab = bytes.fromhex(descifrar[1])
    paraBob = descifrar[2]
    cprint("ALICE: Cerramos conexion con Trent", "blue")
    #Cerramos conexion
    alice.cerrar()
    cprint("ALICE: ---PASO 5---", "blue")
    cprint("ALICE: Nos conectamos a Bob", "blue")
    alice.conectar("localhost", 3002)
    cprint("ALICE: Enviamos Kab a Bob", "blue")
    cifrar = aes.iniciarAES_GCM(kab)
    datosCifrados, mac, nonce = aes.cifrarAES_GCM(cifrar, json.dumps(["Alice", ts]).encode("utf-8"))
    enviarABob = json.dumps([paraBob, datosCifrados.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])
    alice.enviar(bytes(enviarABob.encode("utf-8")))
    cprint("ALICE: ---PASO 6---", "blue")
    cprint("ALICE: Recibimos TS+1 de Bob", "blue")
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
        cprint("ALICE: TS correcto", "blue")
    else:
        cprint("ALICE: TS incorrecto", "red")
        exit(-1)
    alice.cerrar()
    cprint("---ALICE FINALIZADA---", "blue")
    
    