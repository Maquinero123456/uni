import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Map.Entry;

public class VariablesTabla{

    private HashMap<String, String> tabla;

    public VariablesTabla(){
        tabla = new HashMap<>();
    }

    public boolean contiene(String key){
        return tabla.containsKey(key);
    }

    public boolean introduceVariable(String key, String tipo){
        if(contiene(key)){
            return false;
        }

        tabla.put(key, tipo);

        return true;
    }

    public String getVariable(String key){
        return key;
    }

    public String getTipo(String key){
        return tabla.get(key);
    }

    @Override
    public String toString() {
        String aux = "";

        for(String e: tabla.keySet()){
            aux+=e+": <";
            
            aux+=tabla.get(e);
            aux+=">\n";
        }

        return aux;
    }
}