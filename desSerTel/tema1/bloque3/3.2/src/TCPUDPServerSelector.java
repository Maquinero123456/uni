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
        Selector selector = Selector.open();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        //TPCP
        ServerSocketChannel tcp = ServerSocketChannel.open();
        tcp.socket().bind(new InetSocketAddress(9000));
        tcp.configureBlocking(false);
        SelectionKey serverKey = tcp.register(selector, SelectionKey.OP_ACCEPT);
        //UDP
        DatagramChannel udp = DatagramChannel.open();
        udp.socket().bind(new InetSocketAddress(9000));
        udp.configureBlocking(false);
        SelectionKey channelKey = udp.register(selector, SelectionKey.OP_READ, new ClientRecord());
        for (;;) {
            selector.select();
            Set keys = selector.selectedKeys();
            for(Iterator i = keys.iterator(); i.hasNext();) {
                SelectionKey key = (SelectionKey) i.next();
                i.remove();

                if(key == serverKey){
                    if(key.isAcceptable()) {
                        SocketChannel client = tcp.accept();
                        client.configureBlocking(false);

                        SelectionKey clientKey = client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }else {
                        if(!key.isReadable())
                            continue;

                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer2 = (ByteBuffer) key.attachment();
                        buffer2.clear();
                        int bytesread = client.read(buffer2);
                        if(bytesread==-1){
                            key.cancel();
                            client.close();
                            continue;
                        }
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }

                    if(key.isValid() && key.isWritable()){
                        ByteBuffer buf = (ByteBuffer) key.attachment();
                        buf.flip();
                        SocketChannel client = (SocketChannel) key.channel();
                        client.write(buf);
                        if(!buf.hasRemaining()) {
                            key.interestOps(SelectionKey.OP_READ);
                        }
                        buf.compact();
                    }
                }else if((key.isValid()) && (key==channelKey)){
                    if(key.isReadable()){
                        DatagramChannel channel = (DatagramChannel) key.channel();
                        ClientRecord cr = (ClientRecord) key.attachment();
                        cr.buffer.clear();
                        cr.clientAddress = channel.receive(cr.buffer);
                        if(cr.clientAddress!=null){
                            key.interestOps(SelectionKey.OP_WRITE);
                        }
                    }

                    if(key.isWritable()){
                        DatagramChannel channel = (DatagramChannel) key.channel();
                        ClientRecord cr = (ClientRecord) key.attachment();
                        cr.buffer.flip();
                        int bs = channel.send(cr.buffer, cr.clientAddress);
                        if(bs!=0){
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            }
        }
    }
}
