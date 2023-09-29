import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperacionesListasEnteros {

	public static void rotarLista(List<Integer> lista, int e) {
		for(int i = 0;i<e%lista.size(); i++){
			int aux = lista.get(0);
			for(int j = 0; j<lista.size()-1; j++){
				lista.set(j, lista.get(j+1));
			}
			lista.set(lista.size()-1, aux);
		}
	}
}
