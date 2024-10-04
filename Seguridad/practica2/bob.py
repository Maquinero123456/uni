from funciones_rsa import *
from socket_class import *
from funciones_aes import *

clavePrivadaBob = cargar_RSAKey_Privada("PrivadaBob", "Bob123")
clavePublicaAlice = cargar_RSAKey_Publica("PublicaAlice")

socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket.escuchar()

cifrado = socket.recibir()
firmado = socket.recibir()

datos = descifrarRSA_OAEP(cifrado, clavePrivadaBob)
print(datos)
print(comprobarRSA_PSS(datos, firmado, clavePublicaAlice))

texto = "Hola Alice"
ctr, nonce = iniciarAES_CTR_cifrado(datos)
cifrado = cifrarAES_CTR(ctr, bytes(texto, 'utf-8'))
firmado = firmarRSA_PSS(bytes(texto, 'utf-8'), clavePrivadaBob)

socket.enviar(cifrado)
socket.enviar(firmado)
socket.enviar(nonce)

cifrado = socket.recibir()
firmado = socket.recibir()
nonce = socket.recibir()

ctr = iniciarAES_CTR_descifrado(datos, nonce)
descifrado = descifrarAES_CTR(ctr, cifrado)
print(str(descifrado))
print(comprobarRSA_PSS(descifrado, firmado, clavePublicaAlice))
