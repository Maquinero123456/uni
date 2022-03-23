package practica2021a;

import java.util.List;

public abstract class Abiertos<E extends Estado> {

	public class NodoAB implements Comparable<NodoAB>

	{
		private int f;      //prioridad (estimación de coste)
		private E estado;   //estado


		public NodoAB(int f, E e) {
			this.f = f;
			this.estado = e;
		}

		public int getF()  {
			return this.f;
		}

		public E getEstado() {
			return this.estado;
		}

		/**
		 * Orden natural por valor creciente de f. 
		 * Este método es importante para el método offer de Abiertos.
		 */
		@Override
		public int compareTo(Abiertos<E>.NodoAB n) {
				return this.getF() - n.getF();	     //mejor cuanto menor f
		}

		/**
		 * Método generado automáticamente con Eclipse
		 * Dos nodos son iguales si comparten el mismo estado, independientemente
		 * de los otros valores.
		 * Este método es importante para el método remove de Abiertos.
		 */

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NodoAB other = (NodoAB) obj;
			if (estado == null) {
				if (other.estado != null)
					return false;
			} else if (!estado.equals(other.estado))
				return false;
			return true;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////


	/**
	 * @return true si la lista está vacía, false en otro caso.
	 */
	public abstract boolean isEmpty();

	/**
	 * Inserta el estado e en la lista con el valor f indicado.
	 */
	public abstract void offer(int f, E e);
	
	/**
	 * @return El primer estado de la lista, borrándolo de la misma.
	 */
	public abstract E poll();

	/**
	 * Elimina el estado e de la lista, independientemente de su valor de f
	 */
	public abstract void remove (E e);
		
	/**
	 * @return número de nodos en la lista Abiertos.
	 */
	public abstract int size();

	/**
	 * Muestra por pantalla de forma legible el contenido de la lista.
	 */
	public abstract void ver();

}


