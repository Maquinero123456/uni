public class OrdenLogIter extends Metodo{
    public OrdenLogIter(){
        super(Orden.LOGN, Orden.LOGN);
    }

    @Override
    public int codigo(int n) {
        int res = 0;
        while(n>1){
            res++;
            n/=2;
        }
        return res;
    }
}
