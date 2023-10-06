public class DiaDeLaSemana {

    static String elegirDia(int num){
        switch(num){
            case 2:
                return "martes";
            case 3:
                return "miercoles";
            case 4:
                return "jueves";
            case 5:
                return "viernes";
            case 6:
                return "sabado";
            case 7:
                return "domingo";
            default:
                return "lunes";
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(elegirDia(Integer.parseInt(args[0])));
    }
}
