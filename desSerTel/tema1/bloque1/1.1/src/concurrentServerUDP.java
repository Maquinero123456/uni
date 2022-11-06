import java.io.IOException;
import java.net.*;

public class concurrentServerUDP {
    public static void main(String[] args) throws IOException {
        //Creamos un datagram socket
        DatagramSocket ds = null;
        try {
            //Asignamos la direccion y el puerto al datagramSocket
            InetAddress localAddr = InetAddress.getByName("localhost");
            int wellKnownPort = 3000; // Puerto conocido del servidor
            ds = new DatagramSocket(wellKnownPort, localAddr);
            //Creamos un byte buffer y un DatagramPacket para recibir datos
            byte[] buffer = new byte[2048];
            DatagramPacket datagram = new DatagramPacket(buffer,
            buffer.length);
            //Creamos un bucle infinito para que no se cierre el servidor
            while (true) {
                //Recibimos la peticion
                ds.receive(datagram);
                System.out.println("Nueva peticion de servicio");
                // Inicio de una hebra para la peticion actual
                (new ServerUDP(datagram)).start();
            }
        } catch (IOException e) {
            System.err.println("Error E/S en: " + e.getMessage());
        //Cerramos el server si hay algun problema
        } finally {
            if (ds != null)
            ds.close();
        }
    }
}
