public class Bicicleta {
    private int frenos;
    private int velocidad;

    public Bicicleta(int frenos){
        this.frenos = frenos;
        this.velocidad = 0;
    }

    public void frenar(){
        velocidad-=frenos;
    }

    public void acelerar(int v){
        velocidad+=v;
    }

    public int getFrenos() {
        return frenos;
    }

    public void setFrenos(int frenos) {
        this.frenos = frenos;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "{" +
            " frenos='" + getFrenos() + "'" +
            ", velocidad='" + getVelocidad() + "'" +
            "}";
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
