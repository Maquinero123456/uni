from socket_class import SOCKET_SIMPLE_TCP
import Codigo_RSA as cr
from termcolor import cprint

class pt:
    def __init__(self) -> None:
        key = cr.crear_RSAKey()
        cr.guardar_RSAKey_Privada("privadaTrent.pem", key, "trent")
        cr.guardar_RSAKey_Publica("publicaTrent.pem", key)
        self.socket = SOCKET_SIMPLE_TCP("localhost", 3003)
        self.socket.escuchar()
    
    def escuchar(self):
        self.socket.escuchar()
        
    def recibir(self):
        return self.socket.recibir()
    
    def enviar(self, datos):
        self.socket.enviar(datos)
        
    def cerrar(self):
        self.socket.cerrar()