import java.io.ByteArrayInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class ClientRecord {
    public ByteBuffer buffer = ByteBuffer.allocate(512);
    public SocketAddress clientAddress;
}
