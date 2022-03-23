package practica2021a;

import java.util.HashMap;

public class ArbolHash<E extends Estado> extends Arbol<Estado> {
	
	HashMap<Estado, Nodo<Estado>> arbol;
	
	public ArbolHash() {
		arbol= new HashMap<>();
	}

	@Override
	public void put(Nodo nodo) {
		arbol.put(nodo.getEstado(), nodo);
	}

	@Override
	public boolean containsKey(Estado estado) {
		return arbol.containsKey(estado);
	}

	@Override
	public Nodo get(Estado estado) {
		Nodo a= arbol.get(estado);
		return a;
	}

	@Override
	public void ver() {
		System.out.println(arbol.toString());
	}

}
