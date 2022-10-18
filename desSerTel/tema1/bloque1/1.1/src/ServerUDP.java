import java.net.*;

public class ServerUDP extends Thread{
    private byte[] datos;
    private InetAddress address;
    private int port;


    public ServerUDP(DatagramPacket D){
        this.datos = D.getData();
        this.address = D.getAddress();
        this.port = D.getPort();
    }

    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(datos, datos.length, address, port);
            ds.send(dp);
            System.out.println("Cerrando peticion");
            ds.close();
        } catch(Exception e) {

        }
    }
}
