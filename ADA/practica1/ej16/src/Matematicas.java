import java.util.List;

public class Matematicas {

    public static void mostrarLista(List<Integer> lista, int i){
        if(i>=lista.size() || i<0){
            System.out.println(-1);
        }
        if(i==lista.size()-1){
            System.out.print(lista.get(i));
        }else{
            mostrarLista(lista, i+1);
            System.out.print(lista.get(i));
        }
    }
}
