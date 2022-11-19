from pb import pb
import json
import Codigo_RSA as cr
import random as rand

def main():
    bob = pb()
    while(not bob.conectar("localhost", 3003)):
        print("Servidor en uso")
    kbt = str(rand.randint(10000, 99999))
    #Ciframos la clave junto a la cadena "bob"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Bob", kbt]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de bob
    firmado = bob.firmar(kbt)
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    bob.enviar(bytes(enviar.encode("utf-8")))
    bob.cerrar()
    
    
    bob.escuchar()
    
    bob.cerrar()
    
main()
    