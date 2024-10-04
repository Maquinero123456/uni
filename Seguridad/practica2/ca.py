from funciones_rsa import *

rsaKey = crear_RSAKey()
guardar_RSAKey_Privada("PrivadaAlice", rsaKey, "Alice123")
guardar_RSAKey_Publica("PublicaAlice", rsaKey)

rsaKey = crear_RSAKey()
guardar_RSAKey_Privada("PrivadaBob", rsaKey, "Bob123")
guardar_RSAKey_Publica("PublicaBob", rsaKey)