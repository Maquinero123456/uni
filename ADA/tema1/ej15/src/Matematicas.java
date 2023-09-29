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

    public static int mcd(int m, int n){
        if(n==0){
            return m;
        }
        return mcd(n, m%n);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(mcd(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}
