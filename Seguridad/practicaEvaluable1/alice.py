from pa import pa
import random as rand
import Codigo_RSA as cr
import json
import time
def main():
    alice = pa()
    alice.conectar("localhost", 3003)
    print("Conectado")
    #Genero la clave simetrica
    kat = str(rand.randint(10000, 99999))
    #Ciframos la clave junto a la cadena "Alice"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Alice", kat]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de Alice
    firmado = alice.firmar(kat)
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    alice.enviar(bytes(enviar.encode("utf-8")))
    alice.cerrar()
    
    #Esperamos que Bob genere su clave
    time.sleep(5)
    
    #Alice se conecta con trent porque quiere hablar con bob
    alice.conectar("localhost", 3003)
    #Envia mensaje
    alice.enviar(bytes(json.dumps["Alice", "Bob"].enconde("utf-8")))
    #Recibe la clave para hablar con Bob
    claveBob=alice.recibir()
    alice.cerrar()
    
    alice.conectar("localhost", 3002)
    
    #Cerramos conexion
    alice.cerrar()
    
main()