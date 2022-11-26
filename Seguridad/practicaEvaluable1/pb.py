from socket_class import SOCKET_SIMPLE_TCP
import Codigo_RSA as cr
import json
import time
import funciones_aes as aes
from termcolor import cprint

class pb:
    def __init__(self) -> None:
        self.escuchaPuerto = 3002
        key = cr.crear_RSAKey()
        cr.guardar_RSAKey_Privada("privadaBob.pem", key, "bob")
        cr.guardar_RSAKey_Publica("publicaBob.pem", key)
        self.socket = SOCKET_SIMPLE_TCP("localhost", 3002)
        
    def conectar(self, host, port):
        try:
            self.socket.cerrar()
            self.socket.setNewServer(host, port)
            self.socket.conectar()
            return True
        except SOCKET_SIMPLE_TCP.error:
            return False
        
    def escuchar(self):
        self.socket.cerrar()
        self.socket.setNewServer("localhost", self.escuchaPuerto)
        self.socket.escuchar()
    
    def enviar(self, datos):
        self.socket.enviar(datos)
        
    def recibir(self):
        return self.socket.recibir()
    
    def cerrar(self):
        self.socket.cerrar()
        
    def firmar(self, datos):
        return cr.firmarRSA_PSS(datos, cr.cargar_ECCKey_Privada("privadaBob.pem", "bob"))

def bob():
    cprint("---BOB INICIADO---", "green")
    bob = pb()
    
    #------------------------PASO 2------------------------
    cprint("BOB: ---PASO 2---", "green")
    #Esperamos que Alice termine de hablar con TTP
    time.sleep(5)
    try:
        bob.conectar("localhost", 3003)
    except:
        cprint("BOB: TTP no disponible", "red")
        exit(-1)
    cprint("BOB: Nos conectamos a TTP", "green")
    kbt = aes.crear_AESKey()
    #Ciframos la clave junto a la cadena "bob"
    cifrado = cr.cifrarRSA_OAEP(json.dumps(["Bob", kbt.hex()]), cr.cargar_RSAKey_Publica("publicaTrent.pem"))
    #Firmamos la clave con la clave privada de bob
    firmado = bob.firmar(str(kbt))
    #Convertimos en json las cosas a enviar
    enviar = json.dumps([cifrado.hex(), firmado.hex()])
    #Enviamos los datos
    cprint("BOB: Envio Kbt a TTP", "green")
    bob.enviar(bytes(enviar.encode("utf-8")))
    bob.cerrar()
    
    #------------------------PASO 5------------------------
    #Esperamos a que Alice termine de hablar con TTP y se conecte con Bob
    cprint("BOB: ---PASO 5---", "green")
    cprint("BOB: Espero conexion de Alice", "green")
    bob.escuchar()
    cprint("BOB: Recibo Kab y TS de Alice", "green")
    #Recibimos mensaje de alice
    clave = bob.recibir()
    clave = clave.decode("utf-8")
    clave = json.loads(clave)
    #Cargamos los datos
    mac = bytes(clave[2], "latin-1")
    nonce = bytes(clave[3], "latin-1")
    ekbt = clave[0]
    ekab = bytes.fromhex(clave[1])
    
    #Cargamos el mensaje Ekbt
    ekbt = json.loads(ekbt)
    datosEKBT = bytes.fromhex(ekbt[0])
    macEKBT = bytes(ekbt[1], "latin-1")
    nonceEKBT = bytes(ekbt[2], "latin-1")
    #Lo desciframos
    datosEKBT = aes.descifrarAES_GCM(kbt, nonceEKBT, datosEKBT, macEKBT)
    datosEKBT = datosEKBT.decode("utf-8")
    datosEKBT = json.loads(datosEKBT)
    #Guardamos TS y Kab
    ts = datosEKBT[0]
    cprint("BOB: Guardamos Kab", "green")
    kab = bytes.fromhex(datosEKBT[1])
    
    #Con Kab desciframos la segunda parte del mensaje
    datosKAB = aes.descifrarAES_GCM(kab, nonce, ekab, mac)
    datosKAB = datosKAB.decode("utf-8")
    datosKAB = json.loads(datosKAB)
    
    #Comprobamos si el mensaje recibido es correcto
    if(datosKAB[0]=="Alice" and datosKAB[1]==ts):
        cprint("BOB: Mensaje correcto de Alice", "green")
    else:
        cprint("BOB: Mensaje no correcto de Alice", "red")
        exit(-1)
    
    #------------------------PASO 6------------------------
    cprint("BOB: ---PASO 6---", "green")
    cprint("BOB: Envio a Alice TS+1", "green")
    #Creamos y ciframos un mensaje que contiene TS+1
    datosAlice, macAlice, nonceAlice = aes.cifrarAES_GCM(aes.iniciarAES_GCM(kab), json.dumps([ts+1]).encode("utf-8"))
    #Lo enviamos
    bob.enviar(bytes(json.dumps([datosAlice.hex(), macAlice.decode("latin-1"), nonceAlice.decode("latin-1")]).encode("utf-8")))
    
    #------------------------PASO 7------------------------
    cprint("BOB: ---PASO 7---", "green")
    cprint("BOB: Recibimos DNI de Alice", "green")
    #Recibimos y cargamos el mensaje recibido
    datos = bob.recibir()
    datos = datos.decode("utf-8")
    datos = json.loads(datos)
    #Desciframos el mensaje
    datos = aes.descifrarAES_GCM(kab, bytes(datos[2], "latin-1"), bytes.fromhex(datos[0]), bytes(datos[1], "latin-1"))
    
    #Si al descifrar hay algun error, lo mostramos
    try:
        datos = datos.decode("utf-8")
        datos = json.loads(datos)
        cprint("BOB: "+datos[0], "green")
    except:
        cprint("Error al descrifrar", "green")
    
    #------------------------PASO 8------------------------
    cprint("BOB: ---PASO 8---", "green")
    cprint("BOB: Enviamos \"Navarro\" a Alice", "green")
    #Creamos el mensaje a enviar a Alice
    datos = ["Navarro"]
    cifrar = aes.iniciarAES_GCM(kab)
    #Ciframos el mensaje
    cifrado, mac, nonce = aes.cifrarAES_GCM(cifrar, json.dumps(datos).encode("utf-8"))
    enviarABob = json.dumps([cifrado.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])
    #Enviamos el mensaje
    bob.enviar(bytes(enviarABob.encode("utf-8")))
    
    #Terminamos
    bob.cerrar()
    cprint("---BOB FINALIZADO---", "green")
    
    