public class OrdenConstanteIter extends Metodo {
    public OrdenConstanteIter(){
        super(Orden.CTE, Orden.CTE);
    }
    @Override
    public int codigo(int n) {
        return n;
    }
    
}
