import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class EchoProtocol implements Runnable {
    private Socket clntSock; // Socket de datos
    private Logger logger; // Logger del servidor

    //Contructor del objeto al que le pasamos el socket del cliente y el servidor
    public EchoProtocol(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;
    }

    //Metodo principal
    public static void handleEchoClient(Socket clntSock, Logger logger) {
        //Creamos un String que almacena los mensajes
        String line;
        try {
            System.out.println("Nuevo cliente, socket " + clntSock);
            //Creamos los objetos que se encargan de recibir los mensajes y de enviarlos
            BufferedReader in = new BufferedReader(new InputStreamReader(clntSock.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(clntSock.getOutputStream())), true);
            //Mientras sea falso, nos quedamos en el bucle
            boolean salir = false;
            while (!salir) {
                line = in.readLine(); // lectura socket cliente
                //Si la lectura es nula, es momento de salir del bucle
                if(line==null) {
                   System.out.println("Cerrando conexion");
                    salir = true;
                //Si no es nula, mostramos por pantalla el mensaje recibido y mandamos el mensaje de vuelta
                }else{
                    System.out.println("Nuevo mensaje: "+line);
                    out.println(line);
                }
            }
            //Tras salir del bucle, cerramos el socket
            clntSock.close();
            //Tras esto la hebra se cerrara tambien
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    //Iniciamos la hebra y lanzamos el metodo que se encarga del protocolo
    public void run() {
        handleEchoClient(clntSock, logger);
    }
}