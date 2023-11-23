import java.util.Arrays;

public class MainTarifa {
	public static void main(String[] args) {
		int[] estimacion= {29, 22, 20, 11, 3, 26, 22, 5, 29, 29};
		
		TarifaTelefonica p=new TarifaTelefonica(8,2,2,estimacion);
		System.out.println("El pago acumulado es: " + p.resolverBottomUp());
		System.out.println("El plan que el estudiante seguirá es: ");
		System.out.println(Arrays.toString(p.reconstruirSol()));
		p.imprimeVectorSolucion();
	}

}
