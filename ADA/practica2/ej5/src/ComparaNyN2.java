public class ComparaNyN2 {
    public static void main(String[] args) {
        int[] tam = {1, 5, 10, 50, 100, 500, 1000};
        OrdenCuadradoIter cuadrado = new OrdenCuadradoIter();
        System.out.println("Tiempo Cuadrado Iter: "+Complejidad.medirTiempos(cuadrado, tam));
    }
}
