public class MainTablero {
	
	private static int[][] costes= {{16,6,5,18,2}, {6,9,3,7,2}, {6,3,1,2,11}, {17,18,1,15,13}, {18,4,4,10,11}, {10,1,1,11,7},{2,2,4,15,0}, {17,4,14,12,0}, {11,19,8,0,9}, {14,9,1,1,15}};

	public static void main(String[] args) {
		RecorridoTablero p=new RecorridoTablero(costes,9,2);
		int res =p.resolverMemo();
		System.out.println("La solución es " + res);
		Recorrido camino= p.reconstruirSol();	
		System.out.println(camino);
		p.imprimeMatrizSolucion();
		
	}

}
