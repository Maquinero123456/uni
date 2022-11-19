from pt import pt
import json
import Codigo_RSA as cr

def main():
    trent = pt()
    
    #Recibimos mensaje de alice
    clave = trent.recibir()
    
    clave = clave.decode("utf-8").replace("'", '"')
    clave = json.loads(clave)
    descifrado = cr.descifrarRSA_OAEP(bytes.fromhex(clave[0]), cr.cargar_ECCKey_Privada("privadaTrent.pem", "trent"))
    descifrado = json.loads(descifrado)
    print(descifrado[1])
    print(cr.comprobarRSA_PSS(descifrado[1], bytes.fromhex(clave[1]), cr.cargar_RSAKey_Publica("publicaAlice.pem")))
    
    
    #Terminamos con alice
    trent.cerrar()
    trent.escuchar()
    
    
main()