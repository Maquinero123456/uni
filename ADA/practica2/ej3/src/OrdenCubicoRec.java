public class OrdenCubicoRec extends Metodo{
    public OrdenCubicoRec(){
        super(Orden.N3, Orden.N3);
    }
    @Override
    public int codigo(int n) {
        if(n==1){
            return 1;
        }
        int res = codigo(n/2);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                for(int h = 0; h<n; h++){
                    res++;
                }
            }
        }
        return res;
    }
    
}
