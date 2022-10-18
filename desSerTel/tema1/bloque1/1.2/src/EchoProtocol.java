import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoProtocol implements Runnable {
    private static final int BUFSIZE = 32; // Tamaño buffer de E/S
    private Socket clntSock; // Socket de datos
    private Logger logger; // Logger del servidor

    public EchoProtocol(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;
    }

    public static void handleEchoClient(Socket clntSock, Logger logger) {
        String line;
        try {
            ServerSocket sockfd = new ServerSocket(6543);
            System.out.println("Inicio servidor " + sockfd);
            while (true) {
                Socket newsockfd = sockfd.accept();
                System.out.println("Nuevo cliente, socket " + newsockfd);
                BufferedReader in = new BufferedReader(new InputStreamReader(newsockfd.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(newsockfd.getOutputStream())), true);
                boolean salir = false;
                while (!salir) {
                    line = in.readLine(); // lectura socket cliente
                    if (line != null) {
                        out.println(line);
                    } // escritura socket cliente
                    else {
                        salir = true;
                    } // cierre socket cliente
                }
                newsockfd.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        handleEchoClient(clntSock, logger);
    }
}