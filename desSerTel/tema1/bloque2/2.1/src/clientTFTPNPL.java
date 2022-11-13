import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Random;

import extra.packet;

public class clientTFTPNPL {
    private static int maxtries = 5;
    private static DatagramSocket socket=null;
    private static InetAddress serveAddress=null;
    private static int servPort;

    public static void main(String[] args) throws IOException {
        //Iniciamos el BufferedReader para leer la entrada
        String mensaje="";
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Usa \"help\" para ver informacion sobre los comandos");
        //Entramos en el bucle principal donde tenemos todas las opciones
        while(!mensaje.equals("quit")){
            //Mostramos el prompt y leemos
            System.out.print("> ");
            mensaje=stdIn.readLine();
            //Si el mensaje es connect entramos aqui
            if(mensaje.contains("connect")){
                //Separamos el mensaje por el espacio
                String[] aux = mensaje.split(" ");
                //Si el mensaje tiene menos de 2 args
                if(aux.length<2){
                    System.out.println("Para conectarte a un servidor usa: connect <Server>");
                //Si el mensaje tiene 2 args, ponemos como direccion el segundo argumento y creamos un socket
                }else{
                    servPort = 6789;
                    serveAddress = InetAddress.getByName(aux[1]);
                    socket = new DatagramSocket();
                    System.out.println("Conectado a servidor "+serveAddress+":"+servPort);
                    System.out.println("Si quiere conectarse a otro servidor vuelva a usar connect");
                }
            //Si el mensaje contiene get entramos aqui
            }else if(mensaje.contains("get")){
                //Primero necesitamos conectarnos a un server para usar get
                if(serveAddress==null){
                    System.out.println("Primero usa el comando connect");
                }else{
                    //Dividimos el mensaje de entrada
                    String[] aux = mensaje.split(" ");
                    //Si tiene menos de dos argumentos mostramos error
                    if(aux.length<2){
                        System.out.println("Para recibir un archivo usa: get <Server>");
                    }else{
                        //Enviamos el datagrama RRQ
                        DatagramPacket dp = packet.createRRQ(aux[1], serveAddress, servPort);
                        socket.send(dp);
                        socket.setSoTimeout(1000);
                        //Creamos el datagrama para recibir el archivo
                        DatagramPacket paquete = new DatagramPacket(new byte[516], 516);
                        boolean wrq = true;
                        //Numero de paquetes totales recibidos(Contado los que se pierden) y numerp de paquetes perdidos
                        int paquetesTotales = 0, paquetesPerdidos=0;
                        //Bucle infinito para recibir el archivo
                        while(wrq){
                            try {
                                //Recibimos el siguiente bloque
                                socket.receive(paquete);
                                Random a = new Random();
                                paquetesTotales++;
                                //Siempre se recibe
                                if(a.nextDouble(0, 1)>-1){
                                    //Si el paquete es menor a 512 bytes hemos terminado
                                    if(packet.dataLenght(paquete.getData())<512){
                                        wrq=false;
                                        System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \"<"+512+" b\"");
                                    }else{
                                        System.out.println("DATA "+packet.getBloque(paquete.getData()) +" \""+packet.dataLenght(paquete.getData())+" b\"");
                                    }
                                    //Enviamos el ACK
                                    socket.send(packet.ACK(packet.getBloque(paquete.getData()), paquete.getAddress(), paquete.getPort()));
                                //Se pierde el paquete
                                }else{
                                    paquetesPerdidos++;
                                    System.out.println("DATA "+packet.getBloque(paquete.getData()) +" (p)");
                                }
                            //Si recibimos timeout es que el archivo no existe
                            } catch (IOException e) {
                                System.out.println("Archivo no encontrado");
                                wrq=false;
                            }
                        }
                        //Si hemos recibido algun paquete mostramos las estadisticas
                        if(paquetesTotales>0){
                            System.out.println("%"+Integer.toString((paquetesPerdidos*100)/paquetesTotales)+":paquetes perdidos; %"+Integer.toString((100-(paquetesPerdidos*100)/paquetesTotales))+": paquetes retransmitidos");
                        }
                        System.out.println("Transmision finalizada");
                    }
                }
            //Si el mensaje contiene put entramos aqui
            }else if(mensaje.contains("put")){
                //Para usar este comando primero necesitamos un servidor al que enviar los datos
                if(serveAddress==null){
                    System.out.println("Primero usa el comando connect");
                }else{
                    //Dividimos el mensaje
                    String[] aux = mensaje.split(" ");
                    //Si tiene menos de dos argumentos mostramos error
                    if(aux.length<2){
                        System.out.println("Para mandar datos al servidor usa: put <fileName>");
                    }else{
                        //Cargo el archivo
                        File archivo = new File(aux[1]);
                        //Mando WRQ
                        DatagramPacket dp = packet.createWRQ(aux[1], serveAddress, servPort);
                        socket.send(dp);
                        socket.setSoTimeout(1000);
                        //Creo el datagrama de los ACK
                        DatagramPacket receivePacket = new DatagramPacket(new byte[4], 4);
                        //Recibimos el primero datagrama que sera el ACK 0
                        socket.receive(receivePacket);
                        System.out.println("ACK "+packet.compACK(receivePacket.getData()));
                        //Si hemos recibido el ACK 0 empezamos a enviar el archivo
                        if(packet.compACK(receivePacket.getData())==0){
                            //Creamos el array de bytes donde vamos a guardar el archivo y 
                            //FileInputStream para ir leyendo el archivo por cachitos
                            byte[] bytesToSend = new byte[512];
                            FileInputStream fi = new FileInputStream(archivo);
                            int bloque=0, tam=512;
                            boolean terminado = false;
                            //Bucle de enviar el archivo
                            while(!terminado){
                                //Mientras nos quede mas de 512bytes por leer del archivo no cambiamos el tamaño
                                if(fi.available()<512){
                                    tam=fi.available();
                                }
                                //Si hemos recibido el ack con el ultimo bloque mandado, leemos el siguiente
                                if(packet.compACK(receivePacket.getData())==bloque){
                                    
                                    bloque++;
                                    bytesToSend = new byte[512];
                                    fi.read(bytesToSend, 0, tam);
                                    
                                }
                                //Creamos el datagrama a enviar y enviamos el paquete
                                DatagramPacket send = packet.dataPacket(bytesToSend, bloque, serveAddress, servPort);
                                socket.send(send);
                                //Esperamos un segundo a recibir el paquete, en caso contrario se ha perdido y creamos
                                //un ACK falso para que no continue leyendo el archivo y reenvie
                                try{
                                    socket.receive(receivePacket);
                                    socket.setSoTimeout(1000);
                                    System.out.println("ACK "+packet.compACK(receivePacket.getData()));
                                } catch (IOException e){
                                    receivePacket = packet.ACK(bloque-1, serveAddress, servPort);
                                }
                                //Si nos queda 0 bytes por enviar y hemos recibido el ultimo ACK hemos terminado
                                if(packet.compACK(receivePacket.getData())==bloque && fi.available()==0){
                                    terminado = true;
                                }
                            }
                            //Cerramos el socket
                            fi.close();
                        }

                        
                    }
                }
            //Si el mensaje es help entramos aqui, para ver todos los comandos disponibles
            }else if(mensaje.contains("help")){
                System.out.println("- Para conectarte a un servidor usa: connect <Server>");
                System.out.println("- Para recibir un archivo usa: get <Server>");
                System.out.println("- Para mandar datos al servidor usa: put <fileName>");
                System.out.println("- Para cerrar el cliente usa: quit");
            }
        }
    }
}
