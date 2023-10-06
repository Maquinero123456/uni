public class OrdenCubicoIter extends Metodo{
    public OrdenCubicoIter(){
        super(Orden.N3, Orden.N3);
    }

    @Override
    public int codigo(int n) {
        int res = 0;
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
