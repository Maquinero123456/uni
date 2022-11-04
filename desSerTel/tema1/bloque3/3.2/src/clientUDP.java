import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class clientUDP {
    private static int maxtries = 5;


    public static void main(String[] args) throws IOException {
        if ((args.length < 1) || (args.length > 2)) {
            throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
        }
        InetAddress serverAddress = InetAddress.getByName(args[0]); // IP Servidor
        int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 6789;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String mensaje="";
        int tries=0;
        DatagramSocket socket = new DatagramSocket();
        while(true){
            System.out.print("> ");
            mensaje=stdIn.readLine();
            if(mensaje.equals(".")){
                break;
            }
            byte[] bytesToSend = mensaje.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bytesToSend, //Datagrama a enviar
            bytesToSend.length, serverAddress, servPort);
            socket.send(sendPacket);
            socket.setSoTimeout(500);
            try {
                DatagramPacket receivePacket = //Datagrama a recibir
                new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
                socket.receive(receivePacket); // Podria no llegar nunca el datagrama de ECO
                if(receivePacket.getAddress().equals(serverAddress)){
                    System.out.println("ECO:"+ new String(receivePacket.getData()));
                }
            }catch(SocketTimeoutException e) {
                tries++;
                System.out.println("Intento "+tries+" de "+maxtries);
                if(tries==maxtries){
                    System.out.println("Maximo numero de intentos alcanzado");
                    System.out.println("Cerrando conexion...");
                    break;
                }
            }
            
        }
        
        socket.close();
    }
}
