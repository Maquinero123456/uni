public class OrdenNLogIter extends Metodo{
    public OrdenNLogIter(){
        super(Orden.NLOGN, Orden.NLOGN);
    }

    @Override
    public int codigo(int n) {
        int res = 0;
        for(int i = n; i>0; i--){
            while(n>1){
                n/=2;
                res++;
            }
        }
        return res;
    }
}
