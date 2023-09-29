import java.util.Random;

public class SumaAleatoria {
    public static void main(String[] args) throws Exception {
        int sumandos = 0;
        Random aleatorio = new Random();
        Double suma = 0.0;
        if(args.length>0){
            sumandos = Integer.parseInt(args[0]);
        }else{
            sumandos = 20;
        }
        
        for(int i = 0; i<sumandos; i++){
            suma+=aleatorio.nextDouble();
        }
        System.out.println(suma);
    }
}

