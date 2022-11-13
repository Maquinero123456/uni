

import java.io.*; // envía datos y recibe eco cuando envía . Fin de la
import java.net.*;

public class clientTCP {

    public static void main(String[] args) throws IOException {
        //Creamos direccion del servidor, lo necesitamos aqui fuera para los catch
        InetAddress serverAddr = null;
        try {
            //Si la entrada no es igual a 2, lanzamos excepcion
            if ((args.length < 1) || (args.length > 2)) {
                throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
            }
            //Le damos valores a la direccion del servidor y puerto
            serverAddr = InetAddress.getByName(args[0]); // IP Servidor
            int serverPort = Integer.parseInt(args[1]);
            //Creamos un socket con estos
            Socket sockfd = new Socket(serverAddr, serverPort);
            System.out.println("Conexión local" + serverAddr);
            //Creamos un BufferedReader para que se encargue de recibir mensajes 
            //y un PrintWriter para que se encargue de enviarlos
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sockfd.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sockfd.getOutputStream())),
                    true);
            //Con este BufferedReader leemos los mensajes de la terminal y los escribimos en el String
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            String userInput; // Entrada por teclado
            //Prompt
            System.out.print("> ");
            //Leemos entrada por teclado, si no es nula seguimos
            while ((userInput = stdIn.readLine()) != null) {
                //Si escribimos punto salimos del bucle
                if (userInput.equals("."))
                    break; // finaliza con el “.”
                //Enviamos mensaje
                out.println(userInput); 
                //Recibimos mensaje
                System.out.println("echo: " + in.readLine());
                System.out.print("> ");
            }
            //Cerramos tanto socket como BufferedReader y PrintWriter
            out.close();
            in.close();
            stdIn.close();
            sockfd.close();
        } catch (UnknownHostException e) {
            System.err.println("Unknown: " + serverAddr);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error I/O for " + serverAddr);
            System.exit(1);
        }
    }
}
