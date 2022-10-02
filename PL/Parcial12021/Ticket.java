import java.io.FileReader;
import java.io.IOException;

public class Ticket {
    protected static int numItemsDistintos = 0;
    protected static int numItems = 0;
    protected static float totalCompra = 0;
    
    public static void addItemsDistintos(int x) {
    	numItemsDistintos += x;
    } 
       
    public static void addItems(int x) {
    	numItems += x;
    }
    
    public static void addCompra(float precio) {
        totalCompra += precio;	
    }
    
    private static void printEstadisticas() {
    	System.out.println("Items distintos: " + numItemsDistintos);
    	System.out.println("Numero de items: " + numItems);
    	System.out.printf("Total de compra: %.2f", totalCompra);
    }
    
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                int yytoken = 1;
                while((yytoken=lex.yylex()) != -1){

                }
                lex.sout();
	    } catch (IOException e) {
	    }
        }
    }

}
