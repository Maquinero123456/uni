import java.io.FileReader;
import java.io.IOException;

public class Ticket {
    public static int numItemsDistintos = 0;
    public static int numItems = 0;
    public static float totalCompra = 0;
    
    private static void printEstadisticas() {
    	System.out.println("Items distintos: " + numItemsDistintos);
    	System.out.println("Numero de items: " + numItems);
    	System.out.printf("Total de compra: %.2f", totalCompra);
        System.out.print("\n");
    }
    
    
    public static void main(String arg[]) {
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                lex.yylex();
                printEstadisticas();
	    } catch (IOException e) {
	    }
        }
    }

}
