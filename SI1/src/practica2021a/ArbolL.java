package practica2021a;

import java.util.LinkedList;

public class ArbolL<E extends Estado> extends Arbol<Estado>{
	
	LinkedList<Nodo<EstadoMalla>> arb;
	
	public ArbolL() {
		arb= new LinkedList<>();
	}
	
	@Override
	public void put(Nodo nodo) {
		arb.add(nodo);
	}

	@Override
	public boolean containsKey(Estado estado) {
		boolean comp= false;
		for(int i=0; i<arb.size(); i++) {
			if(arb.get(i).getEstado().equals(estado)) {
				comp=true;
			}
		}
		return comp;
	}

	@Override
	public Nodo<EstadoMalla> get(Estado estado) {
		Nodo<EstadoMalla> a=null;
		for(int i=0; i<arb.size(); i++) {
			if(arb.get(i).getEstado().equals(estado)) {
				a= arb.get(i);
			}
		}
		return a;
	}

	@Override
	public void ver() {
		System.out.println(arb.toString());
		
	}

}
