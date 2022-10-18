

import java.io.*; // envía datos y recibe eco cuando envía . Fin de la
import java.net.*;

public class client {
    static final int serverPort = 6543;

    public static void main(String[] args) throws IOException {
        InetAddress serverAddr = null;
        try {
            serverAddr = InetAddress.getLocalHost();
            Socket sockfd = new Socket(serverAddr, serverPort);
            System.out.println("Conexión local" + serverAddr);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sockfd.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sockfd.getOutputStream())),
                    true);
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            String userInput; // Entrada por teclado
            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equals("."))
                    break; // finaliza con el “.”
                out.println(userInput); // escribo socket
                System.out.println("echo: " + in.readLine()); // leo socket
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