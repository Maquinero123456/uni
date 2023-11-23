import java.util.HashSet;
import java.util.Set;

public class Cobertura {

	private Grafo grafo;

	public Cobertura(Grafo g) {
		grafo = g;
	}

	public Set<Integer> getConjuntoCobertura() {
		Set<Integer> res = new HashSet<>();
		Grafo aux = new Grafo(grafo);
		Set<Integer> nodosAmirar=grafo.nodosConectados();
		int maxNodos = aux.nodos().size();
		while(aux.numAristas()>0){
			int max=0;
			for(Integer i : nodosAmirar){
				if(grafo.grado(i)>grafo.grado(max)){
					max=i;
				}
			}
			nodosAmirar = aux.nodosConectados();
			nodosAmirar.remove(max);
			for(int i = 0; i<maxNodos; i++){
				aux.removeArista(max, i);
			}
			res.add(max);
		}
		
		
		return res;
	}

}
