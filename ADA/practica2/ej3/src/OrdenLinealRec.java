public class OrdenLinealRec extends Metodo{
    public OrdenLinealRec(){
        super(Orden.N, Orden.N);
    }

    @Override
    public int codigo(int n) {
        if(n==1){
            return 1;
        }
        return n*codigo(n-1);
    }
}
