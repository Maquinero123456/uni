import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Map.Entry;

public class VariablesTabla{

    private HashMap<String, TreeSet<Integer>> tabla;

    public VariablesTabla(){
        tabla = new HashMap<>();
    }

    public boolean contiene(String key){
        return tabla.containsKey(key);
    }

    public boolean contieneBloque(String key, int bloque){
        if(tabla.containsKey(key)){
            return tabla.get(key).contains(bloque);
        }
        return false;
    }

    public boolean introduceVariable(String key, int bloque){
        if(contieneBloque(key, bloque)){
            return false;
        }

        if(tabla.containsKey(key)){
            tabla.get(key).add(bloque);
        }else{
            TreeSet<Integer> aux = new TreeSet<>();
            aux.add(bloque);
            tabla.put(key, aux);
        }

        return true;
    }

    public String getMayorBloque(String key){
        return key+"_"+tabla.get(key).last();
    }

    public void deleteBloque(int bloque){
        
        for(Iterator<Entry<String, TreeSet<Integer>>> iterator = tabla.entrySet().iterator();iterator.hasNext();){
            Entry<String, TreeSet<Integer>> e = iterator.next();
            if(e.getValue().contains(bloque)){
                e.getValue().remove(bloque);
            }
            
            if(e.getValue().isEmpty()){
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        String aux = "";

        for(String e: tabla.keySet()){
            aux+=e+": <";
            
            for(int a : tabla.get(e)){
                aux+=a+", ";
            }
            aux = aux.substring(0, aux.length()-2);
            aux+=">\n";
        }

        return aux;
    }
}