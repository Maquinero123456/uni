import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class executor {
    public static void main(String[] args) throws IOException {
        // Crea un socket pasivo
        ServerSocket servSock = new ServerSocket(7890);
        Logger logger = Logger.getLogger("servidorDST-C3");
        Executor service = Executors.newCachedThreadPool();
        // Bucle principal
        while (true) {
            Socket clntSock = servSock.accept(); // Espera una peticion
            // Ejecuta una hebra y le asigna el servicio de eco
            service.execute(new EchoProtocol(clntSock, logger));
        }
    }
}