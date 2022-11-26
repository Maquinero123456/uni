from socket_class import SOCKET_SIMPLE_TCP
import Codigo_RSA as cr
import json
import time
import funciones_aes as aes
from termcolor import cprint
class pa:
    def __init__(self) -> None:
        self.escuchaPuerto=3001
        key = cr.crear_RSAKey()
        cr.guardar_RSAKey_Privada("privadaAlice.pem", key, "alice")
        cr.guardar_RSAKey_Publica("publicaAlice.pem", key)
        self.socket = SOCKET_SIMPLE_TCP("localhost", 3002)
        
    def conectar(self, host, port):
        self.socket.cerrar()
        self.socket.setNewServer(host, port)
        self.socket.conectar()
        
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
        return cr.firmarRSA_PSS(datos, cr.cargar_ECCKey_Privada("privadaAlice.pem", "alice"))

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
    
    #------------------------PASO 3------------------------
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
    
    #------------------------PASO 4------------------------
    cprint("ALICE: ---PASO 4---", "blue")
    cprint("ALICE: Recibimos Kab de Trent", "blue")
    clave = alice.recibir()
    #Recibimos [TS, Kab, EKbt]
    clave = clave.decode("utf-8")
    clave = json.loads(clave)
    #Cargamos los valores para descifrar lo recibido
    datosCifrados = bytes.fromhex(clave[0])
    mac = bytes(clave[1], 'latin-1')
    nonce = bytes(clave[2], 'latin-1')
    #Desciframos
    descifrar = aes.descifrarAES_GCM(kat, nonce, datosCifrados, mac)
    #Si no se descifra bien, la variable sera "False" y saltara error, por lo que terminamos
    try:
        descifrar = json.loads(descifrar)
    except:
        cprint("ALICE: Datos de descifrado erroneos", "red")
        exit(-1)
    
    #Guardamos TS y Kab
    ts = descifrar[0]
    kab = bytes.fromhex(descifrar[1])
    #Esto se lo enviamos a Bob(Mensaje cifrado por TTP con Kbt que contiene Kab)
    paraBob = descifrar[2]
    cprint("ALICE: Cerramos conexion con Trent", "blue")
    #Cerramos conexion
    alice.cerrar()
    
    #------------------------PASO 5------------------------
    cprint("ALICE: ---PASO 5---", "blue")
    cprint("ALICE: Nos conectamos a Bob", "blue")
    alice.conectar("localhost", 3002)
    cprint("ALICE: Enviamos Kab a Bob", "blue")
    #Ciframos el mensaje ["Alice", TS]
    cifrar = aes.iniciarAES_GCM(kab)
    datosCifrados, mac, nonce = aes.cifrarAES_GCM(cifrar, json.dumps(["Alice", ts]).encode("utf-8"))
    #Construimos un json con el mensaje de TTP y el cifrado anteriormente
    enviarABob = json.dumps([paraBob, datosCifrados.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])
    alice.enviar(bytes(enviarABob.encode("utf-8")))
    
    #------------------------PASO 6------------------------
    cprint("ALICE: ---PASO 6---", "blue")
    cprint("ALICE: Recibimos TS+1 de Bob", "blue")
    #Cargamos el mensaje recibido de Bob
    ekab = alice.recibir()
    ekab = ekab.decode("utf-8")
    ekab = json.loads(ekab)
    #Cargamos los datos para descifrar el mensaje
    datosEKAB = bytes.fromhex(ekab[0])
    macEKAB = bytes(ekab[1], "latin-1")
    nonceEKAB = bytes(ekab[2], "latin-1")
    
    datosEKAB = aes.descifrarAES_GCM(kab, nonceEKAB, datosEKAB, macEKAB)
    #Lo cargamos como json
    datosEKAB = datosEKAB.decode("utf-8")
    datosEKAB = json.loads(datosEKAB)
    
    #Comprobamos si el mensaje recibido es correcto
    if(ts+1 == datosEKAB[0]):
        cprint("ALICE: TS correcto", "blue")
    else:
        cprint("ALICE: TS incorrecto", "red")
        exit(-1)
    
    #------------------------PASO 7------------------------
    cprint("ALICE: ---PASO 7---", "blue")
    cprint("ALICE: Enviamos DNI a Bob", "blue")
    #Creamos el paquete a enviar
    datos = ["77958167N"]
    cifrar = aes.iniciarAES_GCM(kab)
    #Ciframos el paquete
    cifrado, mac, nonce = aes.cifrarAES_GCM(cifrar, json.dumps(datos).encode("utf-8"))
    enviarABob = json.dumps([cifrado.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])
    #Enviamos el paquete
    #Cifrando con GCM ya tenemos Autentificacion
    alice.enviar(bytes(enviarABob.encode("utf-8")))
    
    #------------------------PASO 8------------------------
    cprint("ALICE: ---PASO 8---", "blue")
    cprint("ALICE: Recibimos \"Navarro\" de Bob", "blue")
    #Recibimos y cargamos el mensaje de Bob
    datos = alice.recibir()
    datos = datos.decode("utf-8")
    datos = json.loads(datos)
    #Lo desciframos
    datos = aes.descifrarAES_GCM(kab, bytes(datos[2], "latin-1"), bytes.fromhex(datos[0]), bytes(datos[1], "latin-1"))
    
    #Si no se descifra bien, saltara error
    try:
        datos = datos.decode("utf-8")
        datos = json.loads(datos)
        cprint("ALICE: "+datos[0], "blue")
    except:
        cprint("Error al descrifrar", "red")
        
    #Terminamos
    alice.cerrar()
    cprint("---ALICE FINALIZADA---", "blue")
    
    