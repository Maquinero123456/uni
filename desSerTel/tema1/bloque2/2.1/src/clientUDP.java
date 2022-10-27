import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.file.Files;

import extra.packet;
import extra.packet.*;

public class clientUDP {
    private static int maxtries = 5;
    private static DatagramSocket socket;
    private static InetAddress serveAddress;
    private static int servPort;

    public static void main(String[] args) throws IOException {
        String mensaje="";
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        while(!mensaje.equals("quit")){
            System.out.print("> ");
            mensaje=stdIn.readLine();
            if(mensaje.contains("connect")){
                String[] aux = mensaje.split(" ");
                if(aux.length<2){
                    System.out.println("Para conectarte a un servidor usa: connect <Server> [<Port>]");
                }else{
                    servPort = 6789;
                    serveAddress = InetAddress.getByName(aux[1]);
                    socket = new DatagramSocket();
                    System.out.println("Conectado a servidor "+serveAddress+":"+servPort);
                    System.out.println("Si quiere conectarse a otro servidor vuelva a usar connect");
                }
            }else if(mensaje.contains("get")){
                System.out.println(mensaje);
            }else if(mensaje.contains("put")){
                
                String[] aux = mensaje.split(" ");
                if(aux.length<2){
                    System.out.println("Para mandar datos al servidor usa: put <fileName>");
                }else{
                    //Cargo el archivo
                    File archivo = new File(aux[1]);
                    System.out.println(mensaje);
                    //Mando WRQ
                    DatagramPacket dp = packet.createWRQ(aux[1], serveAddress, servPort);
                    System.out.println(mensaje);
                    socket.send(dp);
                    socket.setSoTimeout(500);

                    DatagramPacket receivePacket = new DatagramPacket(new byte[4], 4);
                    socket.receive(receivePacket);
                    if(packet.compACK(receivePacket.getData())==0){
                        byte[] bytesToSend = new byte[512];
                        FileInputStream fi = new FileInputStream(archivo);
                        int bloque=1, tam=512;
                        while(fi.available()>0){
                            if(fi.available()<512){
                                tam=fi.available();
                            }
                            fi.read(bytesToSend, 0, tam);
                            DatagramPacket send = packet.dataPacket(bytesToSend, bloque, serveAddress, servPort);
                            socket.send(send);
                            socket.receive(receivePacket);
                            if(packet.compACK(receivePacket.getData())==bloque){
                                fi.skip(tam);
                                bloque++;
                            }
                        }

                        fi.close();
                    }

                    
                }
            }
        }
        System.out.println("Cerrando cliente...");
    }
}
