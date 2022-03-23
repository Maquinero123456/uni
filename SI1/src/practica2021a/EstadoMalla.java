package practica2021a;
import java.util.ArrayList;

import java.util.List;

public class EstadoMalla implements Estado {
	
	int fil;   // la fila
	int col;   // la columna
	
	static boolean[][] malla;
	
	static int nFil;
	static int nCol;
	
	static int filFinal;   //fila de la posicion final
	static int colFinal;   //columna de la posicion final
	
	/**
	 * Constructor inicial, da valor a las variables estaticas.
	 */
	public EstadoMalla(int fil, int col, boolean[][] malla, int ff, int cf){
		this.fil = fil;
		this.col = col;
		
		this.malla = malla;
		nFil = malla.length;
		nCol = malla[0].length;
		
		filFinal = ff;
		colFinal = cf;
	}

	
	public EstadoMalla(int f, int c){
		fil = f;
		col = c;
	}

	@Override
	public List<? extends Estado> calculaSucesores() {
		List<EstadoMalla> sucesores= new ArrayList<EstadoMalla>();
		
		int[] vf = {1, 0,-1, 0};
		int[] vc = {0, 1, 0,-1};
		int vfc=0, vcc=0;
		
		for(int i=0; i<4; i++) {
			vfc= vf[i]+fil;
			vcc= vc[i]+col;
			if(((vfc >= 0) && (vfc < nFil) &&  (vcc >= 0) && (vcc < nCol)) && !malla[vfc][vcc]){
				sucesores.add(new EstadoMalla(vfc, vcc));
			}
		}
		
		return sucesores;
	}

	@Override
	public int coste(Estado e2) {
		return 1;
	}

	@Override
	public int h(Estado objetivo) {	
		EstadoMalla a= (EstadoMalla) objetivo;
		return Math.abs(fil - a.fil) + Math.abs(col - a.col);
	}
	
	@Override
	public String toString() {
		return "("+fil+", "+col+")";
	}
	
	@Override
	public void ver() {
		toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + fil;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoMalla other = (EstadoMalla) obj;
		if (col != other.col)
			return false;
		if (fil != other.fil)
			return false;
		return true;
	}


		
}
