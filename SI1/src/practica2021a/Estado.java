package practica2021a;

import java.util.ArrayList;
import java.util.List;

/**
 * @author L.Mandow 
 * @date 2021-03-11
 * Interfaz para clases que representan espacios de estados
 */

public interface Estado {
	
	/**
	 * @return lista con los estados sucesores inmediatos del estado actual en el espacio de estados
	 */
	public List<? extends Estado> calculaSucesores();

	/**
	 * @param e2
	 * @return coste del arco que conecta el estado actual con el estado e2
	 */
	public int coste(Estado e2);

	/**
	 * @return estimación heurística del estado actual al objetivo
	 */
	public int h(Estado objetivo);
	
	/**
	 * Muestra el estado de forma legible por pantalla
	 */
	public void ver();
	
	/**
	 * Los métodos equals y hashcode son necesarios para poder utilizar los estados
	 * como clave en un HashMap
	 */
	public boolean equals(Object obj);	
	public int hashCode();
}
