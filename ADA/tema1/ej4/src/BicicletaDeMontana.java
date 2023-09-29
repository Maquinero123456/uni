public class BicicletaDeMontana extends Bicicleta{
    private int tam;

    public BicicletaDeMontana(int frenos, int tam) {
        super(frenos);
        this.tam = tam;
    }
    
    public void establecerTamano(int t){
        tam = t;
    }


    @Override
    public String toString() {
        return "{" +
        " frenos='" + getFrenos() + "'" +
        ", velocidad='" + getVelocidad() + "'" +
            ", tam='" + getTam() + "'" +
            "}";
    }

    private int getTam() {
        return tam;
    }

}
