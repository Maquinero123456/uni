

from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

# Lee clave KBT
KBT = open("KBT.bin", "rb").read()

# Paso 1) B->T: KBT(Bob, Nb) en AES-GCM
#######################################

# Crear el socket de conexion con T (5551)
print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido (los campos binarios en una cadena) y contruyo el mensaje JSON
msg_TE = []
msg_TE.append("Bob")
msg_TE.append(t_n_origen.hex())
json_ET = json.dumps(msg_TE)
print("B -> T (descifrado): " + json_ET)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KBT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine,json_ET.encode("utf-8"))

# Envia los datos
socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)

# Paso 2) T->B: KBT(K1, K2, Nb) en AES-GCM
##########################################
cifrado = socket.recibir()
cifrado_mac = socket.recibir()
cifrado_nonce = socket.recibir()

datos_descifrado_ET = funciones_aes.descifrarAES_GCM(KBT, cifrado_nonce, cifrado, cifrado_mac)

# Decodifica el contenido: K1, K2, Nb
json_ET = datos_descifrado_ET.decode("utf-8" ,"ignore")
print("T->B (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

# Extraigo el contenido
t_k1, t_k2, t_nb = msg_ET
t_k1 = bytearray.fromhex(t_k1)
t_k2 = bytearray.fromhex(t_k2)
t_nb = bytearray.fromhex(t_nb)

if(t_nb!=t_n_origen):
    print("Nb recibido distinto al original")
    sys.exit(1)

# Cerramos el socket entre B y T, no lo utilizaremos mas
socket.cerrar() 

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket.escuchar()

print("Conectado con Alice")

cifrado = socket.recibir()
nonce = socket.recibir()
datos_hmac = socket.recibir()

aes_ctr_descifrado = funciones_aes.iniciarAES_CTR_descifrado(t_k1, nonce)
nombre = funciones_aes.descifrarAES_CTR(aes_ctr_descifrado, cifrado)
comprobar_hmac = HMAC.new(t_k2, msg=nombre, digestmod=SHA256)
try:
    comprobar_hmac.verify(datos_hmac)
except ValueError:
    print("No hay integridad")
    sys.exit(1)
nombre = nombre.decode("utf-8" ,"ignore")

print("A->B (descifrado): " + json.loads(nombre))

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

msg_AB = []
msg_AB.append("Navarro")
json_AB = json.dumps(msg_AB)
print("B -> A (descifrado): " + json_AB)

aes_ctr, nonce = funciones_aes.iniciarAES_CTR_cifrado(t_k1)
cifrado = funciones_aes.cifrarAES_CTR(aes_ctr, json_AB.encode("utf-8"))
datos_hmac = HMAC.new(t_k2, msg=json_AB.encode("utf-8"), digestmod=SHA256).digest()

socket.enviar(cifrado)
socket.enviar(nonce)
socket.enviar(datos_hmac)

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################
cifrado = socket.recibir()
nonce = socket.recibir()
datos_hmac = socket.recibir()

aes_ctr_descifrado = funciones_aes.iniciarAES_CTR_descifrado(t_k1, nonce)
nombre = funciones_aes.descifrarAES_CTR(aes_ctr_descifrado, cifrado)
comprobar_hmac = HMAC.new(t_k2, msg=nombre, digestmod=SHA256)
try:
    comprobar_hmac.verify(datos_hmac)
except ValueError:
    print("No hay integridad")
    sys.exit(1)
nombre = nombre.decode("utf-8" ,"ignore")
print("A->B (descifrado): " + json.loads(nombre))
print("Terminando programa")
socket.cerrar()
sys.exit(0)

