package practica2021a;

public abstract class Arbol<E extends Estado> {
	
	public abstract void put(Nodo nodo);
	
	public abstract boolean containsKey(E estado);
	
	public abstract Nodo get(E estado);
	
	public abstract void ver();
}
