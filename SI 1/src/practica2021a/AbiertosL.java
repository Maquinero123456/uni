package practica2021a;

import java.util.LinkedList;

public class AbiertosL<E extends Estado> extends Abiertos<Estado>{
	
	LinkedList<Nodo<Estado>> nodos;
	
	public AbiertosL() {
		nodos= new LinkedList<>();
	}

	@Override
	public boolean isEmpty() {
		
		return nodos.isEmpty();
	}

	@Override
	public void offer(int f, Estado e) {
		Nodo<Estado> a= new Nodo<Estado>(e, f, nodos.peekLast());
		nodos.add(a);
	}

	@Override
	public Estado poll() {
		Estado a= nodos.peek().getEstado();
		nodos.poll();
		return a;
	}

	@Override
	public void remove(Estado e) {
		for(int i=0; i<nodos.size(); i++) {
			if(nodos.get(i).getEstado()==e) {
				nodos.remove(i);
			}
		}
	}

	@Override
	public int size() {
		return nodos.size();
	}

	@Override
	public void ver() {
		System.out.println(nodos.toString());
	}
	
	

}
