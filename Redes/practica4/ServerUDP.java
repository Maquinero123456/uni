import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

/**
 *
 * @author <su nombre aquí>
 */
public class ServerUDP {
    public static String extraerTexto(String texto) {
        String resultado = "";
        int salto = Character.getNumericValue(texto.charAt(0));
    
        for (int i = 1; i < texto.length(); i += salto + 1) {
            resultado += texto.charAt(i);
        }
    
        return resultado;
    }

    public static void main(String[] args)
    {
        // DATOS DEL SERVIDOR
        //* FIJO: Si se lee de línea de comando debe comentarse
        int port = 54322; // puerto del servidor
        //* VARIABLE: Si se lee de línea de comando debe descomentarse
        //int port = Integer.parseInt(args[0]); // puerto del servidor

        // SOCKET
        DatagramSocket server = null;

        //* COMPLETAR Crear e inicalizar el socket del servidor
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Funcion PRINCIPAL del servidor
        while (true)
        {
            //* COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta de máximo 400 bytes
            DatagramPacket recibir = new DatagramPacket(new byte[400], 400);
            //* COMPLETAR: Recibir datagrama
            try {
                server.receive(recibir);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //* COMPLETAR: Obtener texto recibido
            String line = new String(recibir.getData(), 0, recibir.getData().length);
            System.out.println(line);
            //* COMPLETAR: Mostrar por pantalla la direccion socket (IP y puerto) del cliente y su texto
            System.out.println("IP: "+recibir.getSocketAddress().toString()+" Puerto: "+recibir.getPort());
            // Capitalizamos la linea
            line = extraerTexto(line);

            //* COMPLETAR: crear datagrama de respuesta
            DatagramPacket enviar = new DatagramPacket(line.getBytes(), line.getBytes().length, recibir.getAddress(),recibir.getPort());
            //* COMPLETAR: Enviar datagrama de respuesta

            try {
                server.send(enviar);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } // Fin del bucle del servicio
    }

}
