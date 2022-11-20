from pt import pt
import json
import Codigo_RSA as cr
import random as rand
import time
import funciones_aes as aes


def main():
        trent = pt()
        print("---PASO 1---")
        #Recibimos mensaje de alice
        clave = trent.recibir()
        
        clave = clave.decode("utf-8").replace("'", '"')
        clave = json.loads(clave)
        descifrado = cr.descifrarRSA_OAEP(bytes.fromhex(clave[0]), cr.cargar_ECCKey_Privada("privadaTrent.pem", "trent"))
        descifrado = json.loads(descifrado)
        if(cr.comprobarRSA_PSS(str(bytes.fromhex(descifrado[1])), bytes.fromhex(clave[1]), cr.cargar_RSAKey_Publica("publicaAlice.pem"))):
            print("Firma de Alice correcta")
        else:
            print("Firma de Alice incorrecta")
            trent.cerrar()
            exit(-1)
        
        kat = bytes.fromhex(descifrado[1])
        
        #Terminamos con alice
        trent.cerrar()
        print("---PASO 2---")
        trent.escuchar()
        
        clave = trent.recibir()
        
        clave = clave.decode("utf-8").replace("'", '"')
        clave = json.loads(clave)
        descifrado = cr.descifrarRSA_OAEP(bytes.fromhex(clave[0]), cr.cargar_ECCKey_Privada("privadaTrent.pem", "trent"))
        descifrado = json.loads(descifrado)
        if(cr.comprobarRSA_PSS(str(bytes.fromhex(descifrado[1])), bytes.fromhex(clave[1]), cr.cargar_RSAKey_Publica("publicaBob.pem"))):
            print("Firma de Bob correcta")
        else:
            print("Firma de Bob incorrecta")
            trent.cerrar()
            exit(-1)
            
        kbt = bytes.fromhex(descifrado[1])
        
        #Terminamos con Bob
        trent.cerrar()
        print("---PASO 3---")
        trent.escuchar()
        
        clave = trent.recibir()
        clave = clave.decode("utf-8").replace("'", '"')
        clave = json.loads(clave)
        if(clave[0]=="Alice" and clave[1]=="Bob"):
            print("Mensaje correcto")
            print("---PASO 4---")
            kab = aes.crear_AESKey()
            ts = time.time()
            segunda = [ts, kab.hex()]
            segunda = json.dumps(segunda)
            cifrado = aes.iniciarAES_GCM(kbt)
            segunda, mac, nonce = aes.cifrarAES_GCM(cifrado, bytes(segunda.encode("utf-8")))
            
            primero = json.dumps([ts, kab.hex(), json.dumps([segunda.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])])
            
            cifrado = aes.iniciarAES_GCM(kat)
            primero, mac, nonce = aes.cifrarAES_GCM(cifrado, bytes(primero.encode("utf-8")))
            print("MAC: "+str(mac))
            print("NONCE: "+str(nonce))
            trent.enviar(bytes((json.dumps([(primero).hex(), (mac).decode('latin-1'), (nonce).decode('latin-1')]).encode("utf-8"))))
        else:
            print("Mensaje recibido no es el esperado")
            trent.cerrar()
            exit(-1)
    
        trent.cerrar()
        
    
    
    
main()