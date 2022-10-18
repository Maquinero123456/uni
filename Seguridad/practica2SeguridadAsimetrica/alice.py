import Codigo_RSA as rsa
def alice():
    #Clave privada de Alice
    alice = rsa.cargar_ECCKey_Privada("privadaAliceRSA.pem", "alice")
    #Clave publica Bob
    bob = rsa.cargar_ECCKey_Publica("publicaBob.pem")

    texto = "Hola amigos de la seguridad"
    cifrado = rsa.cifrarRSA_OAEP(texto, bob)
    firma = rsa.firmarRSA_PSS(texto, alice)

    file_out = open("cifradoBob.txt", "wb")
    file_out.write(cifrado)

    file_out = open("firmadoAlice.txt", "wb")
    file_out.write(firma)