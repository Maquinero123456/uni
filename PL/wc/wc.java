import java.io.FileReader;
import java.io.IOException;

public class wc {
    protected static int EOLN=0;
    protected static int word=0;
    protected static int letter=0;

    
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                Yytoken yytoken = null;
		while (  (yytoken = lex.yylex()) != null  ) {
                    if (yytoken.getToken() == Yytoken.word)  {
                       word++;
                       letter+=yytoken.getLetras();
                    } else if (yytoken.getToken() == Yytoken.EOLN){
                       EOLN++;
                       letter++;
                    } else if (yytoken.getToken() == Yytoken.caracter){
                        letter++;
                     }
                }
	    } catch (IOException e) {
	    }
        }
        System.out.println(EOLN+" "+word+" "+letter+" "+arg[0]);
    }
    
}
