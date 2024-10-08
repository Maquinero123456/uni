import java.util.ArrayList;
import java.util.List;

public class Laberinto {
	private int[][] laberinto;
	private Posicion entrada, salida;
	private int n; // número de filas y columnas

	public Laberinto(int[][] lab, Posicion ent, Posicion sal) {
		this.laberinto = lab;
		this.entrada = ent;
		this.salida = sal;
		this.n = lab.length;
	}

	public int getNumFilas() {
		return n;
	}

	public int getNumCols() {
		return n;
	}
	
	public int [][] getLaberinto(){
		return laberinto;
	}
	public Posicion getEntrada() {
		return entrada;
	}
	public Posicion getSalida() {
		return salida;
	}
	
	public List<Posicion> encontrarCamino() {
		List<Posicion> lista = new ArrayList<Posicion>();
		lista.add(entrada);
		return (encontrarCamino(lista)) ? lista : null;
	}

	/**
	 * Algoritmo de Vuelta Atrás para encontrar un camino que nos permita
	 * salir del laberinto
	 */
	private boolean encontrarCamino(List<Posicion> sol) {
		if(esCompleta(sol)){
			return true;
		}else{
			for(int i=1; i<5; i++){
				if(valida(siguiente(sol.get(sol.size()-1), i), sol)){
					sol.add(siguiente(sol.get(sol.size()-1), i));
					if(encontrarCamino(sol)){
						return true;
					}
					sol.remove(sol.size()-1);
				}
			}
			
		}
		return esCompleta(sol);
	}

	/**
	 * Comprueba si una solución es completa.
	 */
	private boolean esCompleta(List<Posicion> sol) {
		return sol.get(sol.size()-1).equals(salida);
	}

	/**
	 * Comprueba que la posición dada es una candidata válida para el siguiente paso
	 */
	private boolean valida(Posicion candidata, List<Posicion> sol) {
		return  estaEnLaberinto(candidata) && !sol.contains(candidata) && !esMuro(candidata);
	}

	/**
	 * Devuelve true si en la posición p hay un muro
	 */
	private boolean esMuro(Posicion p) {
		return laberinto[p.getX()][p.getY()]==-1;
	}

	/**
	 * Devuelve true si la posición dada está dentro del laberinto.
	 */
	private boolean estaEnLaberinto(Posicion pos) {
		return pos.getX()>=0 && pos.getX()<n && pos.getY()>=0 && pos.getY()<n;
	}

	/**
	 * Dada una posición cartesiana devuelve la siguiente posición en el sentido
	 * indicado. Precondición: actual != null
	 * 
	 * @param actual Posición de partida
	 * @param dir    Sentido en el que hay que desplazarse (1->Norte, 2->Sur,
	 *               3->Este, 4-> Oeste)
	 * @return La nueva posición.
	 */
	private Posicion siguiente(Posicion actual, int dir) {
		int x = actual.getX();
		int y = actual.getY();
		if (dir == 1) {
			x--;
		} else if (dir == 2) {
			x++;
		} else if (dir == 3) {
			y++;
		} else {
			y--;
		}
		return new Posicion(x, y);
	}

	/**
	 * Devuelve todos los caminos para salir del laberinto.
	 */
	public List<List<Posicion>> encontrarCaminos() {
		List<List<Posicion>> todosCaminos = new ArrayList<List<Posicion>>();
		List<Posicion> sol = new ArrayList<Posicion>();
		sol.add(entrada);
		encontrarCaminos(sol, todosCaminos);
		return todosCaminos;
	}

	/**
	 * Algoritmo de Vuelta Atrás para encontrar todas las soluciones
	 */
	private void encontrarCaminos(List<Posicion> sol, List<List<Posicion>> todas) {
		if(esCompleta(sol)){
			todas.add(new ArrayList<>(sol));
		}else{
			List<Posicion> posibles = new ArrayList<>();
			int i = 1;
			while(i<5){
				if(valida(siguiente(sol.get(sol.size()-1), i), sol)){
					posibles.add(siguiente(sol.get(sol.size()-1), i));
				}
				i++;
			}
			List<Posicion> aux = new ArrayList<>(sol);
			for(Posicion j:posibles){
				aux.add(j);
				encontrarCaminos(aux, todas);
				aux.remove(aux.size()-1);
			}
			
			
		}

	}

	public List<Posicion> encontrarCaminoMasCortoVA() {
		List<Posicion> sol = new ArrayList<Posicion>();
		sol.add(entrada);
		return encontrarCaminoMasCortoVA(sol, null);
	}

	/**
	 * Algoritmo de Vuelta Atrás que devuelve la mejor solución encontrada
	 */
	private List<Posicion> encontrarCaminoMasCortoVA(List<Posicion> sol, List<Posicion> mejor) {
		if(esCompleta(sol)){
			if(mejor==null || (mejor.size()>sol.size() && sol!=null)){
				mejor = new ArrayList<>(sol);
			}
		}else{
			List<Posicion> posibles = new ArrayList<>();
			int i = 1;
			while(i<5){
				if(valida(siguiente(sol.get(sol.size()-1), i), sol)){
					posibles.add(siguiente(sol.get(sol.size()-1), i));
				}
				i++;
			}
			List<Posicion> aux = new ArrayList<>(sol);
			for(Posicion j:posibles){
				aux.add(j);
				mejor = encontrarCaminoMasCortoVA(aux, mejor);
				aux.remove(aux.size()-1);
			}
			
			
		}
		return mejor;
	}

	/**
	 * Devuelve la calidad de la solución indicada
	 */
	private int calidad(List<Posicion> sol) {
		return sol.size();
	}

	public List<Posicion> encontrarCaminoMasCortoBB() {
		ColaPrioridad c = new ColaPrioridad();// Creamos la estructura de datos

		List<Posicion> solInicial = new ArrayList<>();
		solInicial.add(entrada);
		Estado inicial = new Estado(solInicial, funcionCota(solInicial)); // Creamos el estado inicial

		List<Posicion> mejor = null;	//
		int cotaMejor = Integer.MAX_VALUE; // infinito

		c.insertar(inicial);

		while(!c.estaVacia()){
			Estado actual = c.extraer();
			if(actual.cota()<cotaMejor){
				if(esCompleta(actual.getCamino())){
					mejor = actual.getCamino();
					cotaMejor = actual.cota();
				}else{
					for(int i = 1; i<5; i++){
						Posicion candidata = siguiente(actual.getCamino().get(actual.getCamino().size()-1), i);
						if(valida(candidata, actual.getCamino())){
							List<Posicion> nuevoCamino = new ArrayList<>(actual.getCamino());
							nuevoCamino.add(candidata);
							if(funcionCota(nuevoCamino)<cotaMejor){
								c.insertar(new Estado(nuevoCamino, funcionCota(nuevoCamino)));
							}
						}
					}
				}	
			}
		}
		
		return mejor;
	}

	/**
	 *  Devuelve el valor de cota de la solución indicada. 
	 */
	private int funcionCota(List<Posicion> sol) {
		return calidad(sol)+(salida.getX()-sol.get(sol.size()-1).getX())+(salida.getY()-sol.get(sol.size()-1).getY());
	}

	/*public static void main(String[] args) {
		int Dim= 6;
		Posicion Entrada= new Posicion(0,0);
		Posicion Salida= new Posicion(3,2);
		int[][] Laberinto= {
					{0,-1,	0	,0,	-1,	-1},
					{0	,0	,0	,-1	,0	,-1},
					{-1,	0,	-1,	0,	0,	0},
					{0,	0,	0	,0,	-1,	0},
					{-1,	0,	-1	,0,	-1,	0},
					{0,	0,	0	,0,	0	,0}};
		Laberinto lab = new Laberinto(Laberinto, Entrada, Salida);
		System.out.println(lab.encontrarCaminoMasCortoVA());
	} */
}
