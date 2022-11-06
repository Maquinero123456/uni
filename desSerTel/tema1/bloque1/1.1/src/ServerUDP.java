import java.net.*;

public class ServerUDP extends Thread{
    private byte[] datos;
    private InetAddress address;
    private int port;

    //Construcor de la hebra, donde le pasamos un DatagramPacket y le asignamos los datos y direccion
    public ServerUDP(DatagramPacket D){
        this.datos = D.getData();
        this.address = D.getAddress();
        this.port = D.getPort();
    }
    //Inicio de la hebra
    public void run() {
        try {
            //Creamos un datagramSocket y un datagramPacket al que le asignamos los datos
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(datos, datos.length, address, port);
            //Enviamos los datos y cerramos el socket
            ds.send(dp);
            System.out.println("Cerrando peticion");
            ds.close();
            // FIN DE LA HEBRA
        } catch(Exception e) {

        }
    }
}
