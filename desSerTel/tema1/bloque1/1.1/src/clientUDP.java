import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class clientUDP {
    //Numero maximo de intentos
    private static int maxtries = 5;

    public static void main(String[] args) throws IOException {
        //Si el numero de args son distintos de 2 lanzamos una excepcion
        if ((args.length < 1) || (args.length > 2)) {
            throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
        }
        //Asignamos la direccion recibida por argumentos que sera la direccion del servidor
        InetAddress serverAddress = InetAddress.getByName(args[0]); // IP Servidor
        int servPort = Integer.parseInt(args[1]);
        //Creamos un BufferedReader para leer desde la terminal
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        //Creamos mensaje para guardar lo leido por la terminal
        String mensaje="";
        //Numero de intentos actual al mandar mensaje
        int tries=0;
        //Socket para enviar los mensajes
        DatagramSocket socket = new DatagramSocket();
        //Bucle infinito, hasta que no escribamos "." no terminara
        while(true){
            //Mostramos el prompt y esperamos que el usuario introduzca una linea
            System.out.print("> ");
            mensaje=stdIn.readLine();
            //Si escribimos "." salimos del bucle
            if(mensaje.equals(".")){
                break;
            }
            //Transformamos el mensaje en bytes y creamos un datrgramPacket con el
            byte[] bytesToSend = mensaje.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bytesToSend, //Datagrama a enviar
            bytesToSend.length, serverAddress, servPort);
            //Lo enviamos y le damos un valor al timeout
            socket.send(sendPacket);
            socket.setSoTimeout(500);
            //En caso de recibir el paquete
            try {
                //Creamos el datagramPacket para recibir la respuesta y lo esperamos
                DatagramPacket receivePacket = //Datagrama a recibir
                new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
                socket.receive(receivePacket); // Podria no llegar nunca el datagrama de ECO
                //Si el mensaje recibido es de la direccion ip correcta, lo mostramos por pantalla
                if(receivePacket.getAddress().equals(serverAddress)){
                    System.out.println("ECO:"+ new String(receivePacket.getData()));
                }
            //En caso de timeout
            }catch(SocketTimeoutException e) {
                //Aumentamos el numero de intentos
                tries++;
                System.out.println("Intento "+tries+" de "+maxtries);
                //Si el numero de intentos llega al maximo, salimos del bucle y cerramos la conexion
                if(tries==maxtries){
                    System.out.println("Maximo numero de intentos alcanzado");
                    System.out.println("Cerrando conexion...");
                    break;
                }
            }
            
        }
        //Cerramos el socket
        socket.close();
    }
}
