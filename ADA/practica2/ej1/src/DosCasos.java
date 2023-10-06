public class DosCasos extends Metodo {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    public DosCasos(){
        super(Orden.CTE, Orden.N);
    }
    @Override
    public int codigo(int n) {
        if(n==0){
            return 0;
        }else{
            int i = 0;
            while(i<n){
                i++;
            }
        }
        return n;
    }
}
