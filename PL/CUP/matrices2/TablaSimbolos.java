import java.util.HashMap;

public class TablaSimbolos {
	
	public final static String ERROR_NOEXISTE = "ERROR6: No se ha encontrado este indentificador en la tabla de simbolos";
	public final static String ERROR_DUPLICADA = "ERROR9: Variable duplicada en la tabla de simbolos";
	
	private static HashMap<String,double[][]> tablaMatrices = new HashMap<String,double[][]>();
	private static HashMap<String,double[]> tablaVectores = new HashMap<String,double[]>();
	
	public static double[][] buscar(String ident) {
		return tablaMatrices.get(ident);
	}
	
	public static void insertar(String ident, double[][] matriz) {
		if (check(ident)) {
			tablaMatrices.put(ident, matriz);
		}
	}

	public static double[] buscarVector(String ident) {
		return tablaVectores.get(ident);
	}
	
	public static void insertarVector(String ident, double[] matriz) {
		if (check(ident)) {
			tablaVectores.put(ident, matriz);
		}
	}

	public static boolean check(String ident) {
		if (buscar(ident)!=null || buscarVector(ident)!=null) {
			System.out.println(ERROR_DUPLICADA);
			System.exit(0);
		}
		return true;
	}
}