package extra;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;

public class packet {
    //Creamos datagrama para el RRQ simplementa pasando el nombre del archivo y la direccion del servidor
    public static DatagramPacket createRRQ(String filename, InetAddress serverAddress, int port){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(new byte[]{0,1}, 0, 2);
        outputStream.write(filename.getBytes(), 0, filename.getBytes().length);
        outputStream.write(new byte[]{0,0,0,0}, 0, 4);
        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }
    //Creamos datagrama para el WRQ simplementa pasando el nombre del archivo y la direccion del servidor
    public static DatagramPacket createWRQ(String filename, InetAddress serverAddress, int port){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(new byte[]{0,2}, 0, 2);
        outputStream.write(filename.getBytes(), 0, filename.getBytes().length);
        outputStream.write(new byte[]{0,0,0,0}, 0, 4);
        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }
    //Creamos datagrama con los datos del archivo, el bloque y la direccion del servidor o cliente
    public static DatagramPacket dataPacket(byte[] datos, int bloque, InetAddress serverAddress, int port) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(new byte[]{0,3}, 0, 2);
        outputStream.write((byte)((bloque>>>8)&0xFF));
        outputStream.write((byte)(bloque&0xFF));
        outputStream.write(datos, 0, datos.length);

        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }
    //Sacamos numero de ACK
    public static int compACK(byte[] ack){
        return Integer.parseInt(Integer.toString(ack[2])+Integer.toString(ack[3]));
    }
    //Sacamos modo(WRQ O RRQ)
    public static int getMode(byte[] mensaje){
        return mensaje[1];
    }
    //Sacamos bloque
    public static int getBloque(byte[] mensaje){
        return Integer.parseInt(String.valueOf(mensaje[2])+String.valueOf(mensaje[3]));
    }
    //Creamos ACK pasandole el bloque y la direccion del servidor o cliente
    public static DatagramPacket ACK(int bloque, InetAddress serverAddress, int port) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(new byte[]{0,4}, 0, 2);
        outputStream.write((byte)((bloque>>>8)&0xFF));
        outputStream.write((byte)(bloque&0xFF));
        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }
    //Sacamos la longitud de los datos del archivo enviados
    //Copiamos los ultimos 512bytes a un array, y contamos hasta que haya 0 seguidos
    public static int dataLenght(byte[] data){
        data = Arrays.copyOfRange(data, 4, data.length);
        int ceros = 0;
        for(byte e : data){
            if(e==0){
                ceros++;
            }else if(e!=0){
                ceros=0;
            }
        }

        return Arrays.copyOfRange(data, 0, data.length-ceros).length;
    }
    //Sacamos nombre de archivo desde un datagrama
    public static String getNameFile(byte[] data){
        data = Arrays.copyOfRange(data, 2, data.length);
        int ceros = 0;
        for(byte e : data){
            if(e==0){
                ceros++;
            }else if(e!=0){
                ceros=0;
            }
        }

        return new String(Arrays.copyOfRange(data, 0, data.length-ceros));
    }
}
