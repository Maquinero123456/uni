import java.io.IOException;

public class prueba {
    public static void main(String[] args) throws IOException {
        String mensaje = "Hola";
        byte[] bytes = mensaje.getBytes();
        for (Byte e : bytes){
            System.out.println(e);
        }
        
    }
}
