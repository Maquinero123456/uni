import java.io.*;
import java.net.*;
import extra.packet;

public class serverUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6789);
        DatagramPacket paquete = new DatagramPacket(new byte[516], 516);
        System.out.println("Servidor iniciado...");
        while (true) {
            socket.receive(paquete); // Recibe un datagrama del cliente
            if(packet.getMode(paquete.getData())==01){
                System.out.println("RRQ");
            }else if(packet.getMode(paquete.getData())==02){
                System.out.println("WRQ");
                socket.send(packet.ACK(0, paquete.getAddress(), paquete.getPort()));
                boolean wrq = true;
                while(wrq){
                    socket.receive(paquete);
                    if(packet.dataLenght(paquete.getData())<512){
                        wrq=false;
                        System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \"<"+512+" b\"");
                    }else{
                        System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \""+packet.dataLenght(paquete.getData())+" b\"");
                    }
                    socket.send(packet.ACK(packet.getBloque(paquete.getData()), paquete.getAddress(), paquete.getPort()));
                }
                System.out.println("Transmision finalizada");
            }
        }
        
    }
}
