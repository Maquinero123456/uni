package practica2021a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @authos L. Mandow
 * @date   11/marzo/2021
 * Agente resolutor de problemas usando A*.
 */

public class AgenteA{ 
	
	/**
	 * Resuelve un problema dado el estado inicial y el objetivo.
	 * 
	 */
	public List<Estado> resuelve(Estado salida, Estado objetivo){
		
		Arbol<Estado> arbol = new ArbolL<Estado>();
		Abiertos<Estado> abiertos = new AbiertosL<Estado>();

		Estado e = salida;
		Nodo n = new Nodo(e, 0, null);
		arbol.put(n);
		abiertos.offer(e.h(objetivo), e);
		
		while (!abiertos.isEmpty()){
			e = abiertos.poll();
			n = arbol.get(e);
			
			if(e.equals(objetivo)){     //si es el objetivo, terminamos
				return recuperaSolucion(n, arbol);
			} else { //generamos los sucesores no repetidos y los a�adimos al �rbol y a abiertos
				int ge = n.getG();
				for(Estado e2: e.calculaSucesores()){
					int nuevoCoste = ge + e.coste(e2);
					
					if (!arbol.containsKey(e2)){	//nuevon nodo: simplemente a�adimos e2 al �rboly a abiertos
						arbol.put(new Nodo(e2, nuevoCoste, n));
						abiertos.offer(nuevoCoste + e2.h(objetivo),  e2);
					} else {
						Nodo n2 = arbol.get(e2);
						int antiguoCoste = n2.getG();
						if (nuevoCoste < antiguoCoste) { // mejor camino: redirigimos el winero de e2 y actualizamos abiertos
							n2.setPadre(n);
							n2.setG(nuevoCoste);
							
							abiertos.remove(e2);
							abiertos.offer(nuevoCoste + e2.h(objetivo), e2);
						}
					}
				
				}//for e2
			}//if-else
		}//while
		
		return null;		 //la b�squeda termina con fracaso
	}
	
	ArrayList<Estado> recuperaSolucion(Nodo n, Arbol<Estado> arbol){
		ArrayList<Estado> solucion = new ArrayList<Estado>();
		
		Nodo n2 = n;
		
		while(n2  != null){
			solucion.add(n2.getEstado());
			n2 = n2.getPadre();
		}//while
		
		return solucion;
	}

	public char[][] matrizSolucion(List<String[]> solucion, char[][] problema){
		for(String[] e: solucion){
			problema[Integer.parseInt(e[0])][Integer.parseInt(e[1])]='+';
		}
		return problema;
	}
}
