public class Matematicas {

    public static int fibonacci(int n){
        if(n==1){
            return 1;
        }else if(n==2){
            return 2;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
    public static void main(String[] args) throws Exception {
        System.out.println(fibonacci(Integer.parseInt(args[0])));
    }
}
