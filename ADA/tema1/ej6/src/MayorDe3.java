public class MayorDe3{
    public static int mayor(int a, int b, int c){
        return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }
    public static void main(String[] args) throws Exception {
        System.out.println(mayor(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2])));
    }
}
