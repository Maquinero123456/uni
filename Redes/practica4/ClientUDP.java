import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;

/**
 *
 * @author <su nombre aquí>
 */

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        // DATOS DEL SERVIDOR:
        //* FIJOS: coméntelos si los lee de la línea de comandos
        String serverName = "127.0.0.1"; //direccion local
        int serverPort = 54322;
        //* VARIABLES: descoméntelos si los lee de la línea de comandos
        //String serverName = args[0];
        //int serverPort = Integer.parseInt(args[1]);

        DatagramSocket serviceSocket = null;

        //* COMPLETAR: crear socket
        serviceSocket = new DatagramSocket();
        // INICIALIZA ENTRADA POR TECLADO
        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in) );
        String userInput;
        System.out.println("Introduzca un texto a enviar que empiece con dígito (sin dígito inicial para acabar): ");
        userInput = stdIn.readLine(); /*CADENA ALMACENADA EN userInput*/

        //* COMPLETAR: Comprobar si el usuario quiere terminar servicio
        while (userInput.matches("[0-9].*")){
            //* COMPLETAR: Crear datagrama con la cadena escrito en el cuerpo
            InetAddress server = InetAddress.getByName(serverName);
            DatagramPacket enviar = new DatagramPacket(userInput.getBytes(), userInput.getBytes().length, server, serverPort);
            //* COMPLETAR: Enviar datagrama a traves del socket
            serviceSocket.send(enviar);
            System.out.println("STATUS: Waiting for the reply");

            //* COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta de máximo 400 bytes
            DatagramPacket recibir = new DatagramPacket(new byte[400], 400);
            //* COMPLETAR: Recibir datagrama de respuesta
            serviceSocket.receive(recibir);
            //* COMPLETAR: Extraer contenido del cuerpo del datagrama en variable line
            String line = new String(recibir.getData(), 0, recibir.getData().length);

            System.out.println("echo: " + line);
            System.out.println("Introduzca un texto a enviar que empiece con dígito (sin dígito inicial para acabar): ");
            userInput = stdIn.readLine();
        }

        System.out.println("STATUS: Closing client");

        //* COMPLETAR Cerrar socket cliente
        serviceSocket.close();
        System.out.println("STATUS: closed");
    }
}
