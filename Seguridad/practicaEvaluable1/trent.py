from pt import pt
import json
import Codigo_RSA as cr
import random as rand
import time
import funciones_aes as aes
from termcolor import cprint


def trent():
        cprint("---TRENT INICIADO---", "yellow")
        cprint("TRENT: ---PASO 1---", "yellow")
        #------------------------PASO 1------------------------
        trent = pt()
        #Recibimos mensaje de alice
        clave = trent.recibir()
        #Cargamos el mensaje como json
        clave = clave.decode("utf-8").replace("'", '"')
        clave = json.loads(clave)
        #Clave[0] mensaje cifrado que contiene Kat
        #Clave[1] mensaje firmado que contiene Kat y "Alice"
        cprint("TRENT: Recibo Kat de Alice", "yellow")
        #Desciframos la primera parte del mensaje cifrada con la clave publica de Trent
        descifrado = cr.descifrarRSA_OAEP(bytes.fromhex(clave[0]), cr.cargar_ECCKey_Privada("privadaTrent.pem", "trent"))
        #Cargamos como json el mensaje descirfado
        descifrado = json.loads(descifrado)
        #Comprobamos la firma 
        if(cr.comprobarRSA_PSS(str(bytes.fromhex(descifrado[1])), bytes.fromhex(clave[1]), cr.cargar_RSAKey_Publica("publicaAlice.pem"))):
            cprint("TRENT: Firma de Alice correcta", "yellow")
        else:
            #Si la firma no es correcta terminamos
            cprint("TRENT: Firma de Alice incorrecta", "red")
            trent.cerrar()
            exit(-1)
        #Guardamos kat
        kat = bytes.fromhex(descifrado[1])
        
        #Terminamos con alice
        trent.cerrar()
        cprint("TRENT: ---PASO 2---", "yellow")
        #------------------------PASO 2------------------------
        trent.escuchar()
        
        #Hacemos exactamente lo mismo que con Alice pero con Bob
        clave = trent.recibir()
        clave = clave.decode("utf-8").replace("'", '"')
        clave = json.loads(clave)
        cprint("TRENT: Recibo Kbt de Bob", "yellow")
        descifrado = cr.descifrarRSA_OAEP(bytes.fromhex(clave[0]), cr.cargar_ECCKey_Privada("privadaTrent.pem", "trent"))
        descifrado = json.loads(descifrado)
        if(cr.comprobarRSA_PSS(str(bytes.fromhex(descifrado[1])), bytes.fromhex(clave[1]), cr.cargar_RSAKey_Publica("publicaBob.pem"))):
            cprint("TRENT: Firma de Bob correcta", "yellow")
        else:
            cprint("TRENT: Firma de Bob incorrecta", "red")
            trent.cerrar()
            exit(-1)
            
        kbt = bytes.fromhex(descifrado[1])
        
        
        trent.cerrar()
        #Terminamos con Bob
        #------------------------PASO 3------------------------
        trent.escuchar()
        cprint("TRENT: ---PASO 3---", "yellow")
        cprint("TRENT: Recibo [\"Alice\", \"Bob\"] de Alice", "yellow")
        #Cargamos el mensaje recibido de Alice
        clave = trent.recibir()
        clave = clave.decode("utf-8").replace("'", '"')
        clave = json.loads(clave)
        #Clave[0] = "Alice"
        #Clave[1] = "Bob"
        if(clave[0]=="Alice" and clave[1]=="Bob"):
            cprint("TRENT: Mensaje correcto", "yellow")
        else:
            cprint("TRENT: Mensaje recibido no es el esperado", "red")
            trent.cerrar()
            exit(-1)
        
        #------------------------PASO 3------------------------
        cprint("TRENT: ---PASO 4---", "yellow")
        cprint("TRENT: Envio la clave kat a Alice", "yellow")
        #Creamos la clave kab y el timestamp
        kab = aes.crear_AESKey()
        ts = time.time()
        #Creamos un json con estos
        segunda = [ts, kab.hex()]
        segunda = json.dumps(segunda)
        #Ciframos el json con la clave Kbt
        cifrado = aes.iniciarAES_GCM(kbt)
        segunda, mac, nonce = aes.cifrarAES_GCM(cifrado, bytes(segunda.encode("utf-8")))
        #Creamos json con timestamp, Kab y el mensaje cifrado de antes junto al nonce y mac
        primero = json.dumps([ts, kab.hex(), json.dumps([segunda.hex(), mac.decode("latin-1"), nonce.decode("latin-1")])])
        #Ciframos el json con la clave Kat
        cifrado = aes.iniciarAES_GCM(kat)
        primero, mac, nonce = aes.cifrarAES_GCM(cifrado, bytes(primero.encode("utf-8")))
        #Enviamos json a Alice creado con el contenido cifrado, mac y nonce
        trent.enviar(bytes((json.dumps([(primero).hex(), (mac).decode('latin-1'), (nonce).decode('latin-1')]).encode("utf-8"))))

        #Trent no hace nada mas
        cprint("---TRENT FINALIZADO---", "yellow")
        trent.cerrar()