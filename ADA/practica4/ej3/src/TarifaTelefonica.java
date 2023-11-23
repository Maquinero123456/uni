import java.util.Arrays;

public class TarifaTelefonica {

	private int tarifaPlana, permanencia, tarifaMegas;
	private int[] estimacion;
	private int[] pago;// Pago mínimo a realizar durante los meses de i...n

	public TarifaTelefonica(int tp, int p, int tm, int[] est) {
		tarifaPlana = tp;
		permanencia = p;
		tarifaMegas = tm;
		estimacion = est;
		pago = null;
	}

	public int resolverBottomUp() {
        int n = estimacion.length;
        pago = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int costoPlan = tarifaPlana * permanencia;
            int costoMegas = tarifaMegas * estimacion[i];

            if (i + permanencia < n) {
                if(pago[i+permanencia] + costoPlan < pago[i+1] + costoMegas) {
                    pago[i] = pago[i+permanencia] + costoPlan;
                } else {
                    pago[i] = pago[i+1] + costoMegas;
                }
            } else {
                if(i == n-1) {
                    pago[i] = costoMegas;
                } else if (n-i == permanencia) {
                    if(costoPlan < pago[i+1] + costoMegas) {
                        pago[i] = costoPlan;
                    } else {
                        pago[i] = pago[i+1] + costoMegas;
                    }
                } else {
                    pago[i] = pago[i+1] + costoMegas;
                }
            }
        }

        return pago[0];
    }

	public int[] reconstruirSol() {
        if (pago == null) {
            throw new RuntimeException("Se debe resolver el problema primero");
        }

        int n = pago.length;
        int[] sol = new int[n];

        for (int i = 0; i < n; i++) {
            if (i + permanencia < n && pago[i] != pago[i+1] + estimacion[i] * tarifaMegas) {
                int k = 0;
                while (k < permanencia) {
                    sol[i] = 1; // 1 indica que se utiliza tarifa plana
                    k++; i++;
                }
                i--;
            } else if (n-i == permanencia && pago[i] == tarifaPlana * permanencia) {
                int k = 0;
                while(k < permanencia) {
                    sol[i] = 1;
                    k++; i++;
                }
                i--;
            } else {
                sol[i] = 0; // 0 indica que se utiliza tarifa por MB
            }
        }

        return sol;
    }

	public void imprimeVectorSolucion() {
		System.out.println(Arrays.toString(pago));
	}

}