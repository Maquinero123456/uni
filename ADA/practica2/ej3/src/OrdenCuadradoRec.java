public class OrdenCuadradoRec extends Metodo{
    public OrdenCuadradoRec(){
        super(Orden.N2, Orden.N2);
    }
    @Override
    public int codigo(int n) {
        if(n==1){
            return 1;
        }
        int res = codigo(n/2);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                res++;
            }
        }
        return res;
    }
    
}
