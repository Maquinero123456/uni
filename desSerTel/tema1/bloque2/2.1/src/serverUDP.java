import java.io.*;
import java.net.*;
import extra.packet;

public class serverUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6789);
        DatagramPacket paquete = new DatagramPacket(new byte[516], 516);
        while (true) {
            socket.receive(paquete); // Recibe un datagrama del cliente
            System.out.println(paquete.toString());
            if(paquete.getData()[0]==1){
                System.out.println("RRQ");
            }else if(paquete.getData()[0]==2){
                System.out.println("WRQ");
            }
        }
        
    }
}
