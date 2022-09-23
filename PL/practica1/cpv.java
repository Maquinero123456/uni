import java.io.FileReader;
import java.io.IOException;

public class cpv {
    protected static int sumaA = 0;
    protected static int sumaB = 0;
    protected static int sumaC = 0;
    protected static int sumaD = 0;

    
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                Yytoken yytoken = null;
		while (  (yytoken = lex.yylex()) != null  ) {
                    if (yytoken.getToken() == Yytoken.A)  {
                       sumaA++;
                    } else if (yytoken.getToken() == Yytoken.B) {
                        sumaB++;
                    } else if (yytoken.getToken() == Yytoken.C) {
                        sumaC++;
                    } else if (yytoken.getToken() == Yytoken.D) {
                       sumaD++;
                    } else if (yytoken.getToken() == Yytoken.EOLN){

                    }
                }
	    } catch (IOException e) {
	    }
        }
        System.out.println("A "+Integer.toString(sumaA));
        System.out.println("B "+Integer.toString(sumaB));
        System.out.println("C "+Integer.toString(sumaC));
        System.out.println("D "+Integer.toString(sumaD));
    }
    
}
