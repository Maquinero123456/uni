import java.util.List;

public class OperacionesListasEnteros {

	public static void rotarLista(List<Integer> lista, int e) {
		if(e>lista.size()){
			e += e%lista.size();
		}
		for(int i = 0;i<=e%lista.size(); i++){
			if(e>0){
				int aux = lista.get(lista.size()-1);
				for(int j = lista.size()-1; j>0; j--){
					lista.set(j, lista.get(j-1));
				}
				lista.set(0, aux);
			}else{
				int aux = lista.get(0);
				for(int j = 0; j<lista.size(); j++){
					lista.set(j, lista.get(j+1));
				}
				lista.set(lista.size()-1, aux);
			}
		}
	}
	
}
