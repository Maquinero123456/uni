import java.util.ArrayList;
import java.util.List;

public class EncuentraComun {
    public static List<String> comunes(String[] a, String[] b){
        List<String> res = new ArrayList<>();
        for(int i = 0; i<a.length; i++){
            for(int j = 0; j<b.length; j++){
                if(a[i].compareTo(b[j])==0){res.add(a[i]);}
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        String[] a = new String[]{"hola", "adios", "lunes", "jueves"};
        String[] b = new String[]{"hola2", "adios", "lunes", "jueves2"};
        System.out.println(comunes(a, b).toString());
    }
}
