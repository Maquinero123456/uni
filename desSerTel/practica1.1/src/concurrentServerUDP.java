import java.io.IOException;
import java.net.*;

public class concurrentServerUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = null;
        try {
            InetAddress localAddr = InetAddress.getByName("localhost");
            int wellKnownPort = 3000; // Puerto conocido del servidor
            ds = new DatagramSocket(wellKnownPort, localAddr);
            byte[] buffer = new byte[2048];
            DatagramPacket datagram = new DatagramPacket(buffer,
            buffer.length);
            while (true) {
                ds.receive(datagram);
                System.out.println("Nueva peticion de servicio");
                // Inicio de una hebra para la peticion actual
                (new ServerUDP(datagram)).start();
            }
        } catch (IOException e) {
            System.err.println("Error E/S en: " + e.getMessage());
        } finally {
            if (ds != null)
            ds.close();
        }
    }
}
