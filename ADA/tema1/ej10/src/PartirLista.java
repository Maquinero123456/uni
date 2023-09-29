import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartirLista {
    public static void main(String[] args) throws Exception {
        List<Integer> aPartir = new ArrayList<>();
        Random aleatorio= new Random();
        for(int i = 0; i<Integer.parseInt(args[0]); i++){
            aPartir.add(aleatorio.nextInt(0, 11));
        }
        System.out.println("Lista completa: "+aPartir);
        System.out.println("Parte izquierda: "+aPartir.subList(0, aPartir.size()/2)+
                           "\nParte Derecha: "+aPartir.subList(aPartir.size()/2, aPartir.size()));
    }
}
