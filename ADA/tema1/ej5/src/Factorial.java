public class Factorial {
    public static int fact(int n){
        int res = n;
        do{
            n--;
            res*=n;
        }while(n>1);
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(fact(Integer.parseInt(args[0])));
    }
}
