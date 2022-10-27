package extra;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class packet {
    public static DatagramPacket createRRQ(String filename, InetAddress serverAddress, int port){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(Byte.valueOf("01"));
        outputStream.write(filename.getBytes(), 0, filename.getBytes().length);
        outputStream.write(Byte.valueOf("0"));
        outputStream.write(Byte.valueOf(Byte.valueOf("00")));
        outputStream.write(Byte.valueOf("0"));
        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }

    public static DatagramPacket createWRQ(String filename, InetAddress serverAddress, int port){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(Byte.valueOf("02"));
        System.out.println(outputStream.size());
        outputStream.write(filename.getBytes(), 0, filename.getBytes().length);
        System.out.println(outputStream.size());
        outputStream.write(Byte.valueOf("0"));
        System.out.println(outputStream.size());
        outputStream.write(Byte.valueOf(Byte.valueOf("00")));
        System.out.println(outputStream.size());
        outputStream.write(Byte.valueOf("0"));
        System.out.println(outputStream.toByteArray().length);
        for(Byte e : outputStream.toByteArray()){
            System.out.println(e);
        }
        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }

    public static DatagramPacket dataPacket(byte[] datos, int bloque, InetAddress serverAddress, int port) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(Byte.valueOf("03"));
        outputStream.write(ByteBuffer.allocate(2).putInt(bloque).array());
        outputStream.write(datos, 0, datos.length);

        return new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length, serverAddress, port);
    }

    public static int compACK(byte[] ack){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(Arrays.copyOfRange(ack,2,3), 0, 2);

        return Integer.parseInt(outputStream.toString());
    }

    public static int getMode(byte[] mensaje){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(Arrays.copyOfRange(mensaje,0,mensaje.length), 0, 2);
        System.out.println(outputStream.toString());
        return Integer.parseInt(outputStream.toString());
    }
}
