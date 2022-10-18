import Codigo_RSA as rsa
def bob():
    #Clave publica de Alice
    alice = rsa.cargar_ECCKey_Publica("publicaAlice.pem")
    #Clave privada de Bob
    bob = rsa.cargar_ECCKey_Privada("privadaBobRSA.pem", "bob")

    cifrado = open("cifradoBob.txt", "rb")
    cifrado = cifrado.read()
    firma = open("firmadoAlice.txt", "rb")
    firma = firma.read()

    descifrado = rsa.descifrarRSA_OAEP(cifrado, bob)
    firma = rsa.comprobarRSA_PSS(descifrado, firma, alice)

    print("Texto descifrado: "+descifrado)

    if(firma):
        print("Firma valida")
    else:
        print("Firma no valida")