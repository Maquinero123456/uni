import java.io.*;
import java.net.*;
import extra.packet;

import java.util.Random;

public class serverUDP {
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("not close")
        DatagramSocket socket = new DatagramSocket(6789);
        DatagramPacket paquete = new DatagramPacket(new byte[516], 516);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Servidor iniciado");
        System.out.println("----------------------------------------------------------------");
        while (true) {
            System.out.println("Esperando entrada");
            System.out.println("----------------------------------------------------------------");
            socket.receive(paquete); // Recibe un datagrama del cliente
            int mode = packet.getMode(paquete.getData());
            if(mode==1){
                System.out.println("----------------------------------------------------------------");
                System.out.println("RRQ");
                InetAddress serveAddress = paquete.getAddress();
                int servPort = paquete.getPort();
                System.out.println(packet.getNameFile(paquete.getData()));
                File archivo = new File(packet.getNameFile(paquete.getData()));
                DatagramPacket receivePacket = packet.ACK(0, paquete.getAddress(), paquete.getPort());
                System.out.println("ACK "+packet.compACK(receivePacket.getData()));
                socket.setSoTimeout(500);
                if(packet.compACK(receivePacket.getData())==0){
                    byte[] bytesToSend = new byte[512];
                    FileInputStream fi = new FileInputStream(archivo);
                    int bloque=0, tam=512;
                    boolean terminado = false;
                    while(!terminado){
                        if(fi.available()<512){
                            tam=fi.available();
                        }
                        if(packet.compACK(receivePacket.getData())==bloque){    
                            bloque++;
                            bytesToSend = new byte[512];
                            fi.read(bytesToSend, 0, tam);
                                
                        }
                        DatagramPacket send = packet.dataPacket(bytesToSend, bloque, serveAddress, servPort);
                        socket.send(send);
                        try{
                            socket.receive(receivePacket);
                            socket.setSoTimeout(500);
                            System.out.println("ACK "+packet.compACK(receivePacket.getData()));
                        } catch (IOException e){
                            receivePacket = packet.ACK(bloque-1, serveAddress, servPort);
                        }
                            
                        if(packet.compACK(receivePacket.getData())==bloque && fi.available()==0){
                            terminado = true;
                        }
                    }
                    fi.close();
                }
                
                socket.setSoTimeout(0);
                System.out.println("----------------------------------------------------------------");
            }else if(mode==2){
                System.out.println("----------------------------------------------------------------");
                System.out.println("WRQ");
                socket.send(packet.ACK(0, paquete.getAddress(), paquete.getPort()));
                boolean wrq = true;
                while(wrq){
                    try {
                        socket.receive(paquete);
                        Random a = new Random();
                        if(a.nextDouble(0, 1)>0.1){
                            if(packet.dataLenght(paquete.getData())<512){
                                wrq=false;
                                System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \"<"+512+" b\"");
                            }else{
                                System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \""+packet.dataLenght(paquete.getData())+" b\"");
                            }
                            
                            socket.send(packet.ACK(packet.getBloque(paquete.getData()), paquete.getAddress(), paquete.getPort()));
                        }else{
                            System.out.println("DATA "+packet.getBloque(paquete.getData()) +" (p)");
                        }
                    } catch (IOException e) {
                        wrq=false;
                    }
                }
                System.out.println("Transmision finalizada");
                System.out.println("----------------------------------------------------------------");
            }
        }
    }
}
