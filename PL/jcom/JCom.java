import java.io.FileReader;
import java.io.IOException;

public class JCom {
	// Uno
	public static int linea = 0;
	public static int lineaMultiple = 0;
	public static int dobleAsterisco = 0;
	
    public static void main(String arg[]) {
		Yylex lex = null;
		try {
			lex = new Yylex(new FileReader(arg[0]));
			lex.yylex();
		} catch (IOException e) {
		}
		if(lex!=null){
			System.out.println("//  "+linea);
			System.out.println("/*  "+lineaMultiple);
			System.out.println("/** "+dobleAsterisco);
		}
    }

}
