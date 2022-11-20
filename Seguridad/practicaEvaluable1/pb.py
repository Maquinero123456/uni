from socket_class import SOCKET_SIMPLE_TCP
import Codigo_RSA as cr

class pb:
    def __init__(self) -> None:
        self.escuchaPuerto = 3002
        key = cr.crear_RSAKey()
        cr.guardar_RSAKey_Privada("privadaBob.pem", key, "bob")
        cr.guardar_RSAKey_Publica("publicaBob.pem", key)
        self.socket = SOCKET_SIMPLE_TCP("localhost", 3002)
        
    def conectar(self, host, port):
        try:
            self.socket.cerrar()
            self.socket.setNewServer(host, port)
            self.socket.conectar()
            return True
        except SOCKET_SIMPLE_TCP.error:
            return False
        
    def escuchar(self):
        self.socket.cerrar()
        self.socket.setNewServer("localhost", self.escuchaPuerto)
        self.socket.escuchar()
    
    def enviar(self, datos):
        self.socket.enviar(datos)
        
    def recibir(self):
        return self.socket.recibir()
    
    def cerrar(self):
        self.socket.cerrar()
        
    def firmar(self, datos):
        return cr.firmarRSA_PSS(datos, cr.cargar_ECCKey_Privada("privadaBob.pem", "bob"))