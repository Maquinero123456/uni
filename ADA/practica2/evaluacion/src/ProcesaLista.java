import java.util.ArrayList;
import java.util.List;

public class ProcesaLista extends Metodo{
	private List<Integer> lista;
	
	public ProcesaLista() {
		super();
		lista = null;
		
	}
	public void setLista(List<Integer> l) {
		lista = l;
	}
		
	public List<Integer> getLista() {
		return lista;
	}
	
	/**
	 * Procesamos todos los elementos de la lista lista.
	 * return El número de elementos procesados en realidad.
	 */
	public int codigo(int n) {
		procesaLista(lista);
		return n>lista.size()?n:lista.size();
	}
	
	private void procesaLista(List<Integer> lista) {
        List<Integer> aux = new ArrayList<>();
		int n = -1;
		for(int i = 0; i<lista.size(); i++){
			if(lista.get(i)%2==0 && lista.get(i)!=n){
				aux.add(lista.get(i));
				aux.add(lista.get(i));
			}else if(lista.get(i)!=n){
				aux.add(lista.get(i));
			}
			n = lista.get(i);
		}
		lista.clear();
		lista.addAll(aux);
	}


}
