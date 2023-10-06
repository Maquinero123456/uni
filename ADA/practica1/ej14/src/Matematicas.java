public class Matematicas {

    public static int fibonacci(int n){
        if(n==1){
            return 1;
        }else if(n==2){
            return 2;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int ackermann(int m, int n){
        if(m==0){
            return n+1;
        }else if(n==0){
            return ackermann(m-1, 1);
        }
        return ackermann(m-1, ackermann(m, n-1));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ackermann(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}
