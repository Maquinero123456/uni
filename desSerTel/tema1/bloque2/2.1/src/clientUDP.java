import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Random;

import extra.packet;

public class clientUDP {
    //private static int maxtries = 5;
    private static DatagramSocket socket;
    private static InetAddress serveAddress;
    private static int servPort;

    public static void main(String[] args) throws IOException {
        String mensaje="";
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Usa \"help\" para ver informacion sobre los comandos");
        while(!mensaje.equals("quit")){
            System.out.print("> ");
            mensaje=stdIn.readLine();
            if(mensaje.contains("connect")){
                String[] aux = mensaje.split(" ");
                if(aux.length<2){
                    System.out.println("Para conectarte a un servidor usa: connect <Server>");
                }else{
                    servPort = 6789;
                    serveAddress = InetAddress.getByName(aux[1]);
                    socket = new DatagramSocket();
                    System.out.println("Conectado a servidor "+serveAddress+":"+servPort);
                    System.out.println("Si quiere conectarse a otro servidor vuelva a usar connect");
                }
            }else if(mensaje.contains("get")){
                String[] aux = mensaje.split(" ");
                if(aux.length<2){
                    System.out.println("Para recibir un archivo usa: get <Server>");
                }else{
                    DatagramPacket dp = packet.createRRQ(aux[1], serveAddress, servPort);
                    socket.send(dp);
                    socket.setSoTimeout(500);
                    DatagramPacket paquete = new DatagramPacket(new byte[516], 516);
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
                }
            }else if(mensaje.contains("put")){
                String[] aux = mensaje.split(" ");
                if(aux.length<2){
                    System.out.println("Para mandar datos al servidor usa: put <fileName>");
                }else{
                    //Cargo el archivo
                    File archivo = new File(aux[1]);
                    //Mando WRQ
                    DatagramPacket dp = packet.createWRQ(aux[1], serveAddress, servPort);
                    socket.send(dp);
                    socket.setSoTimeout(10000);

                    DatagramPacket receivePacket = new DatagramPacket(new byte[4], 4);
                    socket.receive(receivePacket);
                    System.out.println("ACK "+packet.compACK(receivePacket.getData()));
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

                    
                }
            }else if(mensaje.contains("help")){
                System.out.println("- Para conectarte a un servidor usa: connect <Server>");
                System.out.println("- Para recibir un archivo usa: get <Server>");
                System.out.println("- Para mandar datos al servidor usa: put <fileName>");

            }
        }
    }
}
