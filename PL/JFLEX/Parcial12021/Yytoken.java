public class Yytoken {
    public final static int ITEM = 127;
    public final static int ITEMDIST = 128;
    public final static int PRECIO = 127;
    public final static int EOLN = 10;

    private int token;
    private int valorI;
    private int valorID;
    private float precio;

    public Yytoken(int token, int valorI, int valorID, float precio) {
         this.token = token;
         this.valorI = valorI;
         this.valorID = valorID;
         this.precio = precio;
    }

    public int getValorI(){
     return this.valorI;
    }

    public int getValorID(){
     return this.valorID;
    }

    public float getPrecio(){
     return this.precio;
    }

    public int getToken(){
     return this.token;
    }
     

    public String toString() {
         return "<"+token+","+precio+">";
    }
}
