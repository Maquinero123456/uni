from funciones_rsa import *
from socket_class import *
from funciones_aes import *

clavePrivadaAlice = cargar_RSAKey_Privada("PrivadaAlice", "Alice123")
clavePublicaBob = cargar_RSAKey_Publica("PublicaBob")

datos = bytearray(16)
cifrado = cifrarRSA_OAEP(datos, clavePublicaBob)
firmado = firmarRSA_PSS(datos, clavePrivadaAlice)

socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket.conectar()
socket.enviar(cifrado)
socket.enviar(firmado)

cifrado = socket.recibir()
firmado = socket.recibir()
nonce = socket.recibir()

ctr = iniciarAES_CTR_descifrado(datos, nonce)
descifrado = descifrarAES_CTR(ctr, cifrado)
print(str(descifrado))
print(comprobarRSA_PSS(descifrado, firmado, clavePublicaBob))

texto = "Hola Bob"
ctr, nonce = iniciarAES_CTR_cifrado(datos)
cifrado = cifrarAES_CTR(ctr, bytes(texto, 'utf-8'))
firmado = firmarRSA_PSS(bytes(texto, 'utf-8'), clavePrivadaAlice)

socket.enviar(cifrado)
socket.enviar(firmado)
socket.enviar(nonce)