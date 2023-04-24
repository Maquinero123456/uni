/**
 *
 * @author <tu nombre aqui>
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
        // DATOS DEL SERVIDOR:
        //* FIJOS: coméntelos si los lee de la línea de comandos
        String serverName = "127.0.0.1"; // direccion local
        int serverPort = 12345;
        //* VARIABLES: descoméntelos si los lee de la línea de comandos
        //String serverName = args[0];
        //int serverPort = Integer.parseInt(args[1]);

        // SOCKET
        Socket serviceSocket = null;

        // FLUJOS PARA EL ENVÍO Y RECEPCIÓN
        PrintWriter out = null;
        BufferedReader in = null;

        //* COMPLETAR: Crear socket y conectar con servidor
        serviceSocket = new Socket(serverName, serverPort);
        //* COMPLETAR: Inicializar los flujos de entrada/salida del socket conectado en las variables PrintWriter y BufferedReader
        out = new PrintWriter(serviceSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
        // Obtener texto por teclado
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Introduzca un texto a enviar (para acabar introduzca \"FINISH\")");
        System.out.print("> ");
        userInput = stdIn.readLine();

        //* COMPLETAR: Comprobar si el usuario ha iniciado el fin de la interacción
        while (!userInput.equals("FINISH")) { // bucle del servicio
            //* COMPLETAR: Enviar texto en userInput al servidor a través del flujo de salida del socket conectado
            out.println(userInput);
            out.flush();
            //* COMPLETAR: Recibir texto enviado por el servidor a través del flujo de entrada del socket conectado
            String line = in.readLine();
            System.out.println("Servidor: "+line);
            // Leer texto de usuario por teclado
            System.out.println("Introduzca un texto a enviar (para acabar introduzca un texto sin dígito inicial)");
            System.out.print("> ");
            userInput = stdIn.readLine();
        } // Fin del bucle de servicio en cliente

        // Salimos porque el cliente quiere terminar la interaccion, ha introducido TERMINAR
        //* COMPLETAR: Enviar FINISH al servidor para indicar el fin deL Servicio
        System.out.println("Cerrando la conexion");
        out.println("FINISH");
        out.flush();
        System.out.println("Esperando mensaje de confirmacion del servidor");
        //* COMPLETAR: Recibir el OK del Servidor
        while(!in.readLine().equals("OK")){

        }
        System.out.println("Servidor: OK");
        System.out.println("Conexion cerrada");
        //* COMPLETAR Cerrar flujos y socket
        in.close();
        out.close();
        serviceSocket.close();
    }
}
