import java.util.List;

public class OperacionesListasEnteros {

	public static void rotarLista(List<Integer> listaIzq, List<Integer> listaDer, int e) {
		for(int i = 0;i<e%(listaIzq.size()+listaDer.size()); i++){
			int aux = listaIzq.get(0);
			for(int j = 0; j<listaIzq.size()+listaDer.size()-1; j++){
				if(j>listaIzq.size()-1){
					listaDer.set(j-listaIzq.size(), listaDer.get(j-listaIzq.size()+1));
				}else if(j<listaIzq.size()-1){
					listaIzq.set(j, listaIzq.get(j+1));
				}else{
					listaIzq.set(listaIzq.size()-1, listaDer.get(0));
				}
			}
			listaDer.set(listaDer.size()-1, aux);
		}
	}
}
