package practica2021a;

import java.util.PriorityQueue;

public class PriorityQueueAbiertos<E extends Estado> extends Abiertos<Estado>{
	
	PriorityQueue<Nodo<Estado>> nodos;
	
	public PriorityQueueAbiertos() {
		nodos= new PriorityQueue<Nodo<Estado>>();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodos.isEmpty();
	}

	@Override
	public void offer(int f, Estado e) {
		Nodo<Estado> a= new Nodo<Estado>(e, f, nodos.peek());
		nodos.offer(a);
		
	}

	@Override
	public Estado poll() {
		Estado a= nodos.poll().getEstado();
		return a;
	}

	@Override
	public void remove(Estado e) {
		for(Nodo<Estado> a: nodos) {
			if(a.getEstado()==e) {
				nodos.remove(a);
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
