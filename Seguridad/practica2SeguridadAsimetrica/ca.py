import Codigo_RSA as rsa
def ca():
    #Alice
    key = rsa.crear_RSAKey()
    rsa.guardar_RSAKey_Privada("privadaAliceRSA.pem", key, "alice")
    rsa.guardar_RSAKey_Publica("publicaAlice.pem", key)

    #Bob
    key = rsa.crear_RSAKey()
    rsa.guardar_RSAKey_Privada("privadaBobRSA.pem", key, "bob")
    rsa.guardar_RSAKey_Publica("publicaBob.pem", key)