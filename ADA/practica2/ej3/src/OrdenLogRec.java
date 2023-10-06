public class OrdenLogRec extends Metodo{
    public OrdenLogRec(){
        super(Orden.LOGN, Orden.LOGN);
    }
    @Override
    public int codigo(int n) {
        if(n<=1){
            return 1;
        }
        int res = codigo(n/3);
        for(int i = 0; i<n; i++){
            res++;
        }
        return res;
    }
    
}
