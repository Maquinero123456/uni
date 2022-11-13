import java.io.*;
import java.net.*;
import extra.packet;

import java.util.Random;

public class serverTFTPNPL {
    public static void main(String[] args) throws IOException {
        //Creamos el socket y el datagrama para recibir los paquetes
        DatagramSocket socket = new DatagramSocket(6789);
        DatagramPacket paquete = new DatagramPacket(new byte[516], 516);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Servidor iniciado");
        System.out.println("----------------------------------------------------------------");
        while (true) {
            //Limpiamos el paquete
            paquete = new DatagramPacket(new byte[516], 516);
            System.out.println("Esperando entrada");
            System.out.println("----------------------------------------------------------------");
            socket.receive(paquete); // Recibe un datagrama del cliente
            //Vemos que modo es
            int mode = packet.getMode(paquete.getData());
            //Si es 1 tenemos que enviar al cliente
            if(mode==1){
                try{
                    System.out.println("RRQ");
                    //Guardamos la direccion y el puerto del cliente
                    InetAddress serveAddress = paquete.getAddress();
                    int servPort = paquete.getPort();
                    //Cargamos archivo 
                    System.out.println(packet.getNameFile(paquete.getData()));
                    File archivo = new File(packet.getNameFile(paquete.getData()));
                    //Creamos el primer ACK falso solo para continuar con el codigo
                    DatagramPacket receivePacket = packet.ACK(0, paquete.getAddress(), paquete.getPort());
                    socket.setSoTimeout(1000);
                    //Empezamos a enviar el archivo
                    if(packet.compACK(receivePacket.getData())==0){
                        //Creamos un array de bytes y un FileInputStream para leer el archivo en bloques
                        byte[] bytesToSend = new byte[512];
                        FileInputStream fi = new FileInputStream(archivo);
                        int bloque=0, tam=512;
                        boolean terminado = false;
                        while(!terminado){
                            //Si lo que nos queda por enviar es mayor a 512 no lo enviamos
                            if(fi.available()<512){
                                tam=fi.available();
                            }
                            //Si hemos recibido ACK del ultimo bloque leido continuamos leyendo el archivo
                            if(packet.compACK(receivePacket.getData())==bloque){    
                                bloque++;
                                bytesToSend = new byte[512];
                                fi.read(bytesToSend, 0, tam);
                                    
                            }
                            //Enviamos el paquete
                            DatagramPacket send = packet.dataPacket(bytesToSend, bloque, serveAddress, servPort);
                            socket.send(send);
                            //Recibimos el ACK
                            try{
                                socket.receive(receivePacket);
                                socket.setSoTimeout(1000);
                                System.out.println("ACK "+packet.compACK(receivePacket.getData()));
                            //Si nos da timeout, es que se ha perdido
                            //Creamos ACK falso con un bloque anterior para que reenvie
                            } catch (IOException e){
                                receivePacket = packet.ACK(bloque-1, serveAddress, servPort);
                            }
                            //Si recibimos ACK del ultimo bloque y no nos queda nada por leer hemos terminado
                            if(packet.compACK(receivePacket.getData())==bloque && fi.available()==0){
                                terminado = true;
                            }
                        }
                        fi.close();
                    }
                    
                //Si el archivo no existe se lanza esto
                } catch (FileNotFoundException e){
                    System.out.println("Archivo "+packet.getNameFile(paquete.getData())+" no encontrado");
                }
                socket.setSoTimeout(0);
                System.out.println("----------------------------------------------------------------");
            //Si es 2 recibimos del cliente
            }else if(mode==2){
                System.out.println("WRQ");
                //Enviamos el ACK 0
                socket.send(packet.ACK(0, paquete.getAddress(), paquete.getPort()));
                boolean wrq = true;
                //Numero de paquetes totales recibidos(Incluidos los perdidos) y paquetes perdidos
                int paquetesTotales = 0, paquetesPerdidos=0;
                //Bucle infinito para recibir
                while(wrq){
                    try {
                        //Recibimos paquetes
                        socket.receive(paquete);
                        Random a = new Random();
                        paquetesTotales++;
                        //Siempre se recibe
                        if(a.nextDouble(0, 1)>-1){
                            //Si es menor a 512 bytes hemos terminado
                            if(packet.dataLenght(paquete.getData())<512){
                                wrq=false;
                                System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \"<"+512+" b\"");
                            }else{
                                System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \""+packet.dataLenght(paquete.getData())+" b\"");
                            }
                            //Enviamos ACK
                            socket.send(packet.ACK(packet.getBloque(paquete.getData()), paquete.getAddress(), paquete.getPort()));
                        //Hemos perdido el paquete
                        }else{
                            System.out.println("DATA "+packet.getBloque(paquete.getData()) +" (p)");
                            paquetesPerdidos++;
                        }
                        
                    } catch (IOException e) {
                        wrq=false;
                    }
                }
                //Si hemos recibido algun paquete, mostramos las estadisticas
                if(paquetesTotales>0){
                    System.out.println("%"+Integer.toString((paquetesPerdidos*100)/paquetesTotales)+":paquetes perdidos; %"+Integer.toString((100-(paquetesPerdidos*100)/paquetesTotales))+": paquetes retransmitidos");
                }
                System.out.println("Transmision finalizada");
                System.out.println("----------------------------------------------------------------");
            }
        }
    }
}
