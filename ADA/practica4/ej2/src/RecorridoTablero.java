public class RecorridoTablero {
	private int[][] tablero; 
	private int[][] solucion;
	private int fila; //Fila de la casilla de inicio
	private int col;  //Columna de la casilla de inicio
	private int n;   
	private int m;

	public RecorridoTablero(int[][] t, int fila, int col) {
		tablero = t;
		n = tablero.length;
		m = tablero[0].length;
		this.fila = fila;
		this.col = col;
		solucion = null;
	}

	public int resolverMemo() {
		// Creamos la tabla auxiliar
		solucion = new int[n][m]; // -1 representará que la celda está vacía.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				solucion[i][j] = -1;
			}
		}
		// Rellenamos la tabla desde la casilla indicada
		return resuelve(fila, col);
	}

	private int resuelve(int i, int j) {
		if(solucion[i][j]!=-1){
			return solucion[i][j];
		}
		if(i==0){
			return solucion[i][j]=tablero[i][j];
		}
		int res = tablero[i][j];
		if(j==0){
			res+=maximo3(resuelve(i-1, j), resuelve(i-1, j+1), -1);
		}else if(j==m-1){
			res+=maximo3(resuelve(i-1, j-1), resuelve(i-1, j), -1);
		}else{
			res+=maximo3(resuelve(i-1, j-1), resuelve(i-1, j), resuelve(i-1, j+1));
		}
		return solucion[i][j]=res;
	}

	private int maximo3(int a, int b, int c) {
		int res = a;
		if (b > res) {
			res = b;
		}
		if (c > res) {
			res = c;
		}
		return res;
	}

	public Recorrido reconstruirSol() {
		if (solucion == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		Recorrido rec = new Recorrido();
		Integer sol=0, pos2=1,j = 0;

		while(j<m){
			if(solucion[n-1][j]!=-1){
				sol=solucion[n-1][j]-tablero[n-1][j];
				pos2=j;
				rec.add(n-1, j);
				j=m+1;
			}
			j++;
		}

		for(int i=n-2; i>-1; i--){
			if(pos2==0){
				j=0;
			}else if(pos2==m-1){
				j=pos2-1;
			}else{
				j=pos2-1;
			}

			while(j<m){
				if(solucion[i][j]==sol){
					pos2=j;
					sol=solucion[i][j]-tablero[i][j];
					rec.add(i, j);
					j=m+1;
				}
				j++;
			}
			
		}
		return rec;
	}

	public void imprimeMatrizSolucion() {
		for (int i = 0; i < solucion.length; i++) {
			for (int j = 0; j < solucion[i].length; j++) {
				System.out.print(solucion[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
}
