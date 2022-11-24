import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Matrices {
	
	public final static String ERROR_FILAS   = "ERROR1: Todas las filas de la matriz deben tener la misma dimension";
	public final static String ERROR_INVERSA = "ERROR2: La operacion inversa() requiere una matriz cuadrada";
	public final static String ERROR_ADJUNTA = "ERROR3: La operacion adjunta() requiere una matriz cuadrada";
	public final static String ERROR_PROD    = "ERROR4: La operacion producto() requiere coincidencia de filas y columnas";
	public final static String ERROR_SUMA    = "ERROR5: La operacion suma() requiere matrices de iguales dimensiones";
	public final static String ERROR_FILAS_INSUF = "ERROR7: se quieren usar mas filas de las disponibles";
    public final static String ERROR_COLUM_INSUF = "ERROR8: se quieren usar mas columnas de las disponibles";
	
    /* ********************************************* */
    /*   OPERACIONES CON MATRICES                    */
    /* ********************************************* */    

    /** Matriz adjunta de otra matriz
     * @param matriz
     * @return
     */
    public static double[][] adjunta(double [][] matriz){
        return transpuesta(cofactores(matriz));
    }

    /**
     * Matriz de cofactores
     * @param matriz
     * @return
     */
    public static double[][] cofactores(double[][] matriz){
        double[][] nm=new double[matriz.length][matriz.length];
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz.length;j++) {
                double[][] det=new double[matriz.length-1][matriz.length-1];
                double detValor;
                for(int k=0;k<matriz.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<matriz.length;l++) {
                            if(l!=j){
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }

    /**
     * Matriz transpuesta
     * @param matriz
     * @return
     */
    public static double[][] transpuesta(double [][] matriz){
        double[][]salida=new double[matriz[0].length][matriz.length];
        for(int i=0; i<matriz[0].length; i++){
            for(int j=0; j<matriz.length; j++)
                salida[i][j]=matriz[j][i];
        }
        return salida;
    }

    /**
     * Determinante de una matriz
     * @param matriz
     * @return
     */
    public static double determinante(double[][] matriz){
        double det;
        if(matriz.length==2){
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
        double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                            nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    /** 
	 * Devuelve la inversa de una matriz
	 * @param matriz
	 * @return
	 */
    public static double[][] inversa(double[][] matriz) {
        double det=1/determinante(matriz);
        double[][] nmatriz=adjunta(matriz);
        nmatriz = producto(det,nmatriz);
        return nmatriz;
    }

    /* ********************************************* */
    /*   CONCATENACION                               */
    /* ********************************************* */    
   
    /**
     * Concatena un escalar y un vector
     * @param n
     * @param matriz
     */
    public static double[] concatena(double n, double[] vector) {
    	double[] salida = new double[vector.length+1];
    	salida[0] = n;
        for(int i=0;i<vector.length;i++) {
            salida[i+1] = vector[i];
        }
        return salida;			
    }
    /**
     * Concatena un vector y un escalar
     * @param n
     * @param matriz
     */
    public static double[] concatena(double[] vector, double n) {
    	double[] salida = new double[vector.length+1];
        for(int i=0;i<vector.length;i++) {
            salida[i] = vector[i];
        }
    	salida[vector.length] = n;
        return salida;			
    }
    /**
     * Concatena dos vectores
     * @param n
     * @param matriz
     */
    public static double[] concatena(double[] vector1, double[] vector2) {
    	double[] salida = new double[vector1.length+vector2.length];
        for(int i=0;i<vector1.length;i++) {
            salida[i] = vector1[i];
        }
        for(int i=0;i<vector2.length;i++) {
            salida[i+vector1.length] = vector2[i];
        }
        return salida;			
    }

    /**
     * Concatena un vector columna al final de la matriz
     * concatena ( {{1,2},{3,4}} , {5,6} ) = {{1,2,5},{3,4,6}}
     * @param matriz
     * @param vector
     */
    public static double[][] concatena(double[][] matriz, double[] vector) {
    	double[][]  salida = new double[matriz.length][matriz[0].length+1];
        for(int i=0;i<matriz.length;i++) {
        	for(int j=0;j<matriz[0].length; j++) {
        		salida[i][j] = matriz[i][j];
        	}
        	salida[i][matriz[0].length] = vector[i];
        }
        return salida;			
    }

    /**
     * Concatena una columna al final de la matriz, con el valor constante n
     * Se usa para poner 0's al final de una matriz, por ejemplo
     * concatena ( {{1,2},{3,4}} , 0 ) = {{1,2,0},{3,4,0}}
     * @param n
     * @param matriz
     */
    public static double[][] concatena(double[][] matriz, double n) {
    	double[][]  salida = new double[matriz.length][matriz[0].length+1];
        for(int i=0;i<matriz.length;i++) {
        	for(int j=0;j<matriz[0].length; j++) {
        		salida[i][j] = matriz[i][j];
        	}
        	salida[i][matriz[0].length] = n;
        }
        return salida;			
    }
    
    // Igual a la anterior
    public static ArrayList<ArrayList<Double>> concatena(ArrayList<ArrayList<Double>> arrayList, Double n) {
    	  for(int i=0; i<arrayList.size(); i++) {
    		  arrayList.get(i).add(n);
    	  }
    	  return arrayList;
    }
    
    
    /* ********************************************* */
    /*   COMPLETAR                                   */
    /* ********************************************* */    

    /**
     * Completa una matriz con ceros hasta alcanzar la dimension n1 x n2
     * completar ( {{1,2,3},{4,5},{6}} , 4, 3 ) = {{1,2,3,0},{4,5,0},{6,0,0},{0,0,0}}
     * @param n
     * @param matriz
     */
    public static double[][] completar(double[][] matriz, int n1, int n2) {
    	double[][] salida = new double[n1][n2];
    	int i;
    	for(i=0;i<Math.min(n1, matriz.length); i++) {
    		int j;
    		for(j=0; j<Math.min(n2, matriz[0].length); j++) {
        		salida[i][j] = matriz[i][j];
    		}
    		for(j=n2; j<n2; j++) {
        		salida[i][j] = 0;
    		}
    	}
		for(i=n1; i<n1; i++) {
    		for(int j=0; j<n2; j++) {
        		salida[i][j] = 0;
    		}
		}
		return salida;
    }

    // Igual al anterior
    public static ArrayList<ArrayList<Double>> concatena(ArrayList<ArrayList<Double>> arrayList, int n1, int n2) {
    	double[][] m = toArray(arrayList);
    	m = completar(m, n1,n2);
  	    return toArrayList(m);
  }

   
    /* ********************************************* */
    /*   PRODUCTO                                    */
    /* ********************************************* */    

    /**
     * Producto de un escalar por un vector
     * @param n
     * @param matriz
     */
    public static double[] producto(double n, double[] vector) {
    	double[] salida = new double[vector.length];
        for(int i=0;i<vector.length;i++) {
            salida[i] = n * vector[i];
        }
        return salida;			
    }

    /**
     * Producto de un escalar por una matriz
     * @param n
     * @param matriz
     */
    public static double[][] producto(double n, double[][] matriz) {
    	double[][]  salida = new double[matriz.length][matriz[0].length];
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz[0].length;j++)
                salida[i][j] = n*matriz[i][j];
        return salida;
    }

    /**
     * Producto de dos vectores
     * @param matriz1
     * @param matriz2
     */
    public static double[][] producto(double[] vector1, double[] vector2) {
    	return producto(toMatriz(vector1), transpuesta(toMatriz(vector2)));
    }


    /**
     * Producto de un vector por una matriz
     * @param vector
     * @param matriz
     */
    public static double[][] producto(double[] vector, double[][] matriz) {
    	return producto(toMatriz(vector), matriz);
    }

    /**
     * Producto de una matriz por un vector
     * @param matriz
     * @param vector
     */
    public static double[][] producto(double[][] matriz, double[] vector) {
    	return producto(matriz, transpuesta(toMatriz(vector)));
    }

    /**
     * Producto de dos matrices
     * @param matriz1
     * @param matriz2
     */
    public static double[][] producto(double[][] matriz1, double[][] matriz2) {
    	double[][] salida = new double[matriz1.length][matriz2[0].length];
        for(int i=0;i<matriz1.length;i++)
            for(int j=0;j<matriz2[0].length;j++) {
            	salida[i][j] = 0;
        		for(int k=0;k<matriz1[0].length; k++) 
        			salida[i][j] += matriz1[i][k] * matriz2[k][j];
            }
        return salida;			
    }

    
    /* ********************************************* */
    /*   SUMA                                        */
    /* ********************************************* */    
    
    /**
     * Suma de dos una constante a un vector
     * @param n
     * @param vector2
     */
    public static double[] suma(double n, double[] vector) {
    	double[] salida = new double[vector.length];
        for(int i=0;i<vector.length;i++) {
            salida[i] = n + vector[i];
        }
        return salida;			
    }

    /**
     * Suma de un escalar por una matriz
     * @param n
     * @param matriz
     */
    public static double[][] suma(double n, double[][] matriz) {
    	double[][] salida = new double[matriz.length][matriz[0].length];
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz[0].length;j++)
                salida[i][j] = n+matriz[i][j];
        return salida;
    }

    /**
     * Suma de dos vectores
     * @param vector1
     * @param vector2
     */
    public static double[] suma(double[] vector1, double[] vector2) {
    	double[] salida = new double[vector1.length];
        for(int i=0;i<vector1.length;i++) {
            salida[i] = vector1[i] + vector2[i];
        }
        return salida;			
    }
    
    /**
     * Suma de dos matrices
     * @param matriz1
     * @param matriz2
     */
    public static double[][] suma(double[][] matriz1, double[][] matriz2) {
    	double[][] salida = new double[matriz1.length][matriz1[0].length];
        for(int i=0;i<matriz1.length;i++)
            for(int j=0;j<matriz2[0].length;j++) 
            	salida[i][j] = matriz1[i][j] + matriz2[i][j];
        return salida;			
    }
    
    /* ********************************************* */
    /*   PRINT                                       */
    /* ********************************************* */    
    
    /**
     * Imprime un escalar
     * @param n
     */
    public static void print(double n) {
    	System.out.println(String.format("%7.2f", n));
    }
    
    /**
     * Imprime un vector
     * @param vector
     */
    public static void print(double[] vector) {
        for(int j=0; j<vector.length; j++)
            System.out.print(String.format("%7.2f",vector[j]));
        System.out.println();
    }
    
    /**
     * Imprime una matriz
     * @param matriz
     */
    public static void print(double[][] matriz) {
        for(int i=0; i<matriz.length; i++) {
            for(int j=0; j<matriz[0].length; j++)
                System.out.print(String.format("%7.2f",matriz[i][j]));
            System.out.println();
        }
    }

    /**
     * Imprime un vector o una matriz
     * @param matriz
     */
    public static void print(ArrayList arrayList) {
    	if (arrayList!=null && !arrayList.isEmpty()) {
        	Object x = arrayList.get(0);
        	if (arrayList.get(0) instanceof Double) {
            	print(toVector(arrayList));
        	} else {
            	print(toArray(arrayList));
        	}
    	}
    }


    /* ********************************************* */
    /*   AUXILIARES                                  */
    /* ********************************************* */    
    
    
    /**
     * Convierte un vector en una matriz de 1 x n
     * @param vector
     */
    public static double[][] toMatriz(double[] vector) {
	    double[][] matriz = new double[1][vector.length];
	    for(int i=0; i<vector.length; i++) {
	        matriz[0][i] = vector[i];
	    }
	    return matriz;
    }

    /**
     * Dimension de un vector
     * @param vector
     */
    public static int dim(double[] vector) {
    	return vector.length;
    }
    public static int filas(double[] vector) {
    	return vector.length;
    }
    public static int columnas(double[] vector) {
    	return vector.length;
    }

    /**
     * Numero de filas de una matriz
     * @param matriz
     */
    public static int filas(double[][] matriz) {
    	return matriz.length;
    }

    /**
     * Numero de columnas de una matriz
     * @param matriz
     */
    public static int columnas(double[][] matriz) {
    	return matriz[0].length;
    }

    /**
     * Convierte un ArrayList<ArrayList<Double>> en una matriz de tipo double[][]
     * @param matriz
     */
    public static double[][] toArray(ArrayList<ArrayList<Double>> arrayList) {
    	int nFilas = arrayList.size();
    	int maxColumnas = 0;
    	for(int i=0; i<nFilas; i++) {
    		ArrayList<Double> fila = arrayList.get(i);
        	maxColumnas = Math.max(maxColumnas,fila.size());
    	}
    	double[][] salida = new double[nFilas][maxColumnas];
    	for(int i=0; i<nFilas; i++) {
    		ArrayList<Double> fila = arrayList.get(i);
        	int nColumnas = fila.size();
        	for(int j=0; j<nColumnas; j++) {
    			salida[i][j] = fila.get(j);
    		}
    	}
    	return salida;
    }
    
    /**
     * Convierte un ArrayList<Double> en una vector de tipo double[]
     * @param arrayList
     */
    public static double[] toVector(ArrayList<Double> arrayList) {
    	int n = arrayList.size();
    	double[] salida = new double[n];
		for(int j=0; j<n; j++) {
			salida[j] = arrayList.get(j);
		}
    	return salida;
    }
    
    /**
     * Convierte un un vector de tipo double[] en un ArrayList<Double>
     * @param vector
     */
    public static ArrayList<Double> toArrayList(double[] vector) {
    	ArrayList<Double> arrayList = new ArrayList<Double>();
    	for(int i=0; i<vector.length; i++) {
    		arrayList.add(vector[i]);
    	}
    	return arrayList;
    }

    /**
     * Convierte un una matriz de tipo double[][] en un ArrayList<ArrayList<Double>>
     * @param matriz
     */
    public static ArrayList<ArrayList<Double>> toArrayList(double[][] matriz) {
    	ArrayList<ArrayList<Double>> arrayList = new ArrayList();
    	int nFilas = filas(matriz);
    	int nColumnas = columnas(matriz);
    	for(int i=0; i<nFilas; i++) {
    		ArrayList<Double> fila = new ArrayList();
    		for(int j=0; j<nColumnas; j++) {
    			fila.add(matriz[i][j]);
    		}
    		arrayList.add(fila);
    	}
    	return arrayList;
    }
    
    
    /**
     * Devuelve una submatriz de n1 x n2 (esquina superior izquierda)
     * @param matriz
     */
    public static double[][] subMatriz(double[][] matriz, int n1, int n2) {
    	double[][] salida = new double[n1][n2];
    	for(int i=0; i<n1; i++) {
    		for(int j=0; j<n2; j++) {
    			salida[i][j] = matriz[i][j];
    		}
    	}
    	return salida;
    }
    
    /**
     * Devuelve un subvector con los n primeros elementos
     * @param matriz
     */
    public static double[] subVector(double[] vector, int n) {
    	double[] salida = new double[n];
    	for(int i=0; i<n; i++) {
			salida[i] = vector[i];
    	}
    	return salida;
    }

    /* ********************************************* */
    /*   MAIN                                        */
    /* ********************************************* */    

    public static boolean DEBUG = false;
    
    public static void main(String argv[]) {    
        try {
          parser p = null; 	
          int i=0;
          while (i<argv.length) {
              if ("+v".equals(argv[i])) {
            	  DEBUG = true;
              } else  {
                  p = new parser(new Yylex(new FileReader(argv[i])));
              } 
              i++;
          }
          if (p!=null) {
              p.parse();   
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    
}