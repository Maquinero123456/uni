
from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

# Lee clave KAT
KAT = open("KAT.bin", "rb").read()

# Paso 3) A->T: KAT(Alice, Na) en AES-GCM
#########################################

# Crear el socket de conexion con T (5552)
print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5552)
socket.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido (los campos binarios en una cadena) y contruyo el mensaje JSON
msg_TE = []
msg_TE.append("Alice")
msg_TE.append(t_n_origen.hex())
json_ET = json.dumps(msg_TE)
print("A -> T (descifrado): " + json_ET)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KAT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine,json_ET.encode("utf-8"))

# Envia los datos
socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)

# Paso 4) T->A: KAT(K1, K2, Na) en AES-GCM
##########################################

cifrado = socket.recibir()
cifrado_mac = socket.recibir()
cifrado_nonce = socket.recibir()

datos_descifrado_ET = funciones_aes.descifrarAES_GCM(KAT, cifrado_nonce, cifrado, cifrado_mac)

# Decodifica el contenido: K1, K2, Na
json_ET = datos_descifrado_ET.decode("utf-8" ,"ignore")
print("T->B (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

# Extraigo el contenido
t_k1, t_k2, t_na = msg_ET
t_k1 = bytearray.fromhex(t_k1)
t_k2 = bytearray.fromhex(t_k2)
t_na = bytearray.fromhex(t_na)

if(t_na!=t_n_origen):
    print("Nb recibido distinto al original")
    sys.exit(1)

# Cerramos el socket entre B y T, no lo utilizaremos mas
socket.cerrar() 

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket.conectar()

msg_AB = []
msg_AB.append("David")
json_AB = json.dumps(msg_AB)
print("A -> B (descifrado): " + json_AB)
print(json_AB.encode("utf-8"))
aes_ctr, nonce = funciones_aes.iniciarAES_CTR_cifrado(t_k1)
cifrado = funciones_aes.cifrarAES_CTR(aes_ctr, json_AB.encode("utf-8"))
datos_hmac = HMAC.new(t_k2, msg=json_AB.encode("utf-8"), digestmod=SHA256).digest()

socket.enviar(cifrado)
socket.enviar(nonce)
socket.enviar(datos_hmac)

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

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
print("B->A (descifrado): " + json.loads(nombre))

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

msg_AB = []
msg_AB.append("END")
json_AB = json.dumps(msg_AB)
print("B -> A (descifrado): " + json_AB)

aes_ctr, nonce = funciones_aes.iniciarAES_CTR_cifrado(t_k1)
cifrado = funciones_aes.cifrarAES_CTR(aes_ctr, json_AB.encode("utf-8"))
datos_hmac = HMAC.new(t_k2, msg=json_AB.encode("utf-8"), digestmod=SHA256).digest()

socket.enviar(cifrado)
socket.enviar(nonce)
socket.enviar(datos_hmac)
socket.cerrar()
print("Terminando programa")
sys.exit(0)
