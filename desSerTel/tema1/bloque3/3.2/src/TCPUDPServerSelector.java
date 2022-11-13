import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPUDPServerSelector {
    public static void main(String[] args) throws IOException {
        //Creo el selector y un buffer
        Selector selector = Selector.open();
        //Creo canal de TCP
        ServerSocketChannel tcp = ServerSocketChannel.open();
        tcp.socket().bind(new InetSocketAddress(9000));
        tcp.configureBlocking(false);
        SelectionKey serverKey = tcp.register(selector, SelectionKey.OP_ACCEPT);
        //Creo canal de UDP
        DatagramChannel udp = DatagramChannel.open();
        udp.socket().bind(new InetSocketAddress(9000));
        udp.configureBlocking(false);
        SelectionKey channelKey = udp.register(selector, SelectionKey.OP_READ, new ClientRecord());
        //Itero sobre todas las keys del selector
        for (;;) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for(Iterator<SelectionKey> i = keys.iterator(); i.hasNext();) {
                SelectionKey key = (SelectionKey) i.next();
                i.remove();
                //Si recibo una conexcion TCP entro aqui
                if(key == serverKey){
                    //Si la conexion es aceptada, le creo un canal de lectura al selector
                    if(key.isAcceptable()) {
                        SocketChannel client = tcp.accept();
                        client.configureBlocking(false);
                        System.out.println("Conexion TCP aceptada");
                        client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    //Si no la rechazo
                    }else {
                        System.out.println("Conexion TCP no aceptada");
                        if(!key.isReadable()){
                            continue;
                        } 
                    }
                //Si es una conexion UDP entro aqui
                }else if((key.isValid()) && (key==channelKey)){
                    //Leo el mensaje recibido
                    if(key.isReadable()){
                        DatagramChannel channel = (DatagramChannel) key.channel();
                        ClientRecord cr = (ClientRecord) key.attachment();
                        cr.buffer.clear();
                        cr.clientAddress = channel.receive(cr.buffer);
                        if(cr.clientAddress!=null){
                            key.interestOps(SelectionKey.OP_WRITE);
                        }
                    }
                    //Envio el mensaje de vuelta
                    if(key.isWritable()){
                        DatagramChannel channel = (DatagramChannel) key.channel();
                        ClientRecord cr = (ClientRecord) key.attachment();
                        cr.buffer.flip();
                        System.out.println("Mensaje UDP: "+new String(cr.buffer.array()));
                        int bs = channel.send(cr.buffer, cr.clientAddress);
                        if(bs!=0){
                            key.interestOps(SelectionKey.OP_READ);
                        }
                        cr.buffer.compact();
                    }
                //Entro aqui si recibo un mensaje TCP lo leo
                }else if(key.isReadable()){

                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer2 = (ByteBuffer) key.attachment();
                    buffer2.clear();
                    int bytesread = client.read(buffer2);
                    //Con esto cierro la conexion si el cliente se cierra
                    if(bytesread==-1){
                        key.cancel();
                        client.close();
                        System.out.println("Conexion TCP cerrada");
                        continue;
                    }
                    key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                //Aqui envio el mensaje TCP
                }else if(key.isValid() && key.isWritable()){
                    
                    ByteBuffer buf = (ByteBuffer) key.attachment();
                    buf.flip();
                    SocketChannel client = (SocketChannel) key.channel();
                    client.write(buf);
                    System.out.println("Mensaje TCP: "+new String(buf.array()).trim());
                    if(!buf.hasRemaining()) {
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    buf.compact();

                }

                
            }
        }
    }
}
