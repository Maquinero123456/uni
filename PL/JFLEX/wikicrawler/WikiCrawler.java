import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WikiCrawler {
    
	public static int nImg = 0;
	public static int nAudio = 0;
	public static int nVideo = 0;
	public static ArrayList<String> enlacesImagenes = new ArrayList<String>();
	public static ArrayList<String> enlacesVideo = new ArrayList<String>();
	public static ArrayList<String> enlacesDestacados = new ArrayList<String>();
	
	// modo checksum
	private final static String MODO_CHECKSUM = "-s";
	
	// opcion
	private final static String VER_NUMERO_DE_IMAGENES = "-ni";
	private final static String VER_NUMERO_DE_IMAGENES_AUDIO_VIDEO = "-nm";
	private final static String VER_ENLACES_DE_IMAGENES = "-ei";
	private final static String VER_ENLACES_DE_IMAGENES_AUDIO_VIDEO = "-ev";
	private final static String VER_ENLACES_DESTACADOS = "-ed";
	
	// Sirve para comprobar rapidamente si la solucion obtenida es correcta
	protected static int checksum(ArrayList<String> array) {
		int sum = 0;
		for(String enlace: array) {
			for(int i=0; i<enlace.length(); i++) {
				char ch = enlace.charAt(i);
				if (('a'<= ch && ch<='z') || ('A'<= ch && ch<='Z')) {
					sum += (int)ch;
				}
			}
		}
		return sum;
	}
	
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            Yylex lex = null;
            String opcion = VER_NUMERO_DE_IMAGENES_AUDIO_VIDEO; // opcion por defecto
            String modo = "";
            String nombreFichero = "";
            for(int i=0; i<arg.length; i++) {
            	if (arg[i].equals(VER_NUMERO_DE_IMAGENES)) {
            		opcion = arg[i];
            	} else if (arg[i].equals(VER_NUMERO_DE_IMAGENES_AUDIO_VIDEO)) {
            		opcion = arg[i];
            	} else if (arg[i].equals(VER_ENLACES_DE_IMAGENES)) {
            		opcion = arg[i];
            	} else if (arg[i].equals(VER_ENLACES_DE_IMAGENES_AUDIO_VIDEO)) {
            		opcion = arg[i];
            	} else if (arg[i].equals(VER_ENLACES_DESTACADOS)) {
            		opcion = arg[i];
            	} else if (arg[i].equals(MODO_CHECKSUM)) {
            		modo = arg[i];
            	} else { // si no es una opcion es el nombre de fichero
            		nombreFichero = arg[i];
            	}
            }
            try {
                lex = new Yylex(new FileReader(nombreFichero));
                lex.yylex() ;
		    } catch (IOException e) {
		    } 
            if (lex!=null) {
            	if (opcion.equals(VER_NUMERO_DE_IMAGENES)) {
                    System.out.println("\t"+nImg+"\t"+nombreFichero);
            	} else if (opcion.equals(VER_NUMERO_DE_IMAGENES_AUDIO_VIDEO)) {
                    System.out.println("\t"+nImg+"\t"+nAudio+"\t"+nVideo+"\t"+nombreFichero);
            	} else if (opcion.equals(VER_ENLACES_DE_IMAGENES)) {
            		if (modo.equals(MODO_CHECKSUM)) {
                		System.out.println(checksum(enlacesImagenes));
            		} else {
                		for(String link : enlacesImagenes) {
                			System.out.println(link);
                		}
            		}
            	} else if (opcion.equals(VER_ENLACES_DE_IMAGENES_AUDIO_VIDEO)) {
            		if (modo.equals(MODO_CHECKSUM)) {
                		System.out.println(checksum(enlacesVideo));
            		} else {
	            		for(String link : enlacesVideo) {
	            			System.out.println(link);
	            		}
            		}
            	} else if (opcion.equals(VER_ENLACES_DESTACADOS)) {
            		if (modo.equals(MODO_CHECKSUM)) {
                		System.out.println(checksum(enlacesDestacados));
            		} else {
	            		for(String link : enlacesDestacados) {
	            			System.out.println(link);
	            		}
            		}
             	}
            }
        }
    }

}
