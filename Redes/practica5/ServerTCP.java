import java.io.*;
import java.net.*;
import java.util.Scanner;

class ServerTCP {
    public static String extract_text(String s){
        String res = "";
        int salto = Character.getNumericValue(s.charAt(0));
        for(int i = 1; i < s.length(); i += salto + 1)
            res += s.charAt(i);
        return res;
    }

    public static void main(String[] args)
    {
        // DATOS DEL SERVIDOR
        //* FIJO: Si se lee de línea de comando debe comentarse
        int port = 12345; // puerto del servidor
        //* VARIABLE: Si se lee de línea de comando debe descomentarse
        // int port = Integer.parseInt(args[0]);

        // SOCKETS
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Pasivo (recepción de peticiones)
        Socket client = null;       // Activo (atención al cliente)

        // FLUJOS PARA EL ENVÍO Y RECEPCIÓN
        BufferedReader in = null;
        PrintWriter out = null;

        //* COMPLETAR: Crear e inicalizar el socket del servidor (socket pasivo)


        while (true) // Bucle de recepción de conexiones entrantes
        {
            //* COMPLETAR: Esperar conexiones entrantes
            try {
                client = server.accept();
                System.out.println("Conexion de IP: "+client.getInetAddress().toString()+" PORT: "+client.getPort());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //* COMPLETAR: Una vez aceptada una conexion, inicializar flujos de entrada/salida del socket conectado
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            boolean salir = false;
            while(!salir) // Inicio bucle del servicio de un cliente
            {   
                //* COMPLETAR: Recibir texto en line enviado por el cliente a través del flujo de entrada del socket conectado
                String line = null;
                try {
                    line = in.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Cliente: " +line);
                //* COMPLETAR: Comprueba si es fin de conexion - SUSTITUIR POR LA CADENA DE FIN enunciado
                if (line.compareTo("FINISH") != 0){
                    line = extract_text(line);

                    //* COMPLETAR: Enviar texto al cliente a traves del flujo de salida del socket conectado
                    out.println(line);
                    out.flush();
                } else { // El cliente quiere cerrar conexión
                    out.println("OK");
                    out.flush();
                    salir = true;
                }
            } // fin del servicio
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //* COMPLETAR: Cerrar flujos y socket
            System.out.println("Cerrada conexion con cliente");
        } // fin del bucle
    } // fin del metodo
}
