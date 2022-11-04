

import java.io.*; // envía datos y recibe eco cuando envía . Fin de la
import java.net.*;

public class clientTCP {

    public static void main(String[] args) throws IOException {
        InetAddress serverAddr = null;
        try {
            if ((args.length < 1) || (args.length > 2)) {
                throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
            }
            serverAddr = InetAddress.getByName(args[0]); // IP Servidor
            int serverPort = (args.length == 2) ? Integer.parseInt(args[1]) : 6789;
            Socket sockfd = new Socket(serverAddr, serverPort);
            System.out.println("Conexión local" + serverAddr);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sockfd.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sockfd.getOutputStream())),
                    true);
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            String userInput; // Entrada por teclado
            System.out.print("> ");
            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equals("."))
                    break; // finaliza con el “.”
                out.println(userInput); // escribo socket
                System.out.println("echo: " + in.readLine()); // leo socket
                System.out.print("> ");
            }
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
