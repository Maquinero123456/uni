public class KesimoElemento {

	public static int buscarKesimo(int[] a, int k) {
		return buscarKesimo(a, k, 0, a.length - 1);
	}

	private static int buscarKesimo(int[] v, int k, int ini, int fin) {
		if (ini < fin){
			int p = partir(v,ini,fin, k);
			if(p==k){
				return v[p];
			}
			if(p<k){
				buscarKesimo(v,k,p+1, fin);
			}else{
				buscarKesimo(v,k,ini,p-1);
			}
		}
		return v[k];
	}

	private static int partir(int[] v, int inf, int sup, int k){
		int pivote = v[inf]; 
		int i = inf+1; 
		int j = sup;
		do {
			while((i<=j) && (v[i] <= pivote)){ i++; }
			while((i<=j) && (v[j] > pivote)){ j--; }
			if (i<j){ intercambia(v,i,j); }
		} while (i <= j);
		intercambia(v,inf,j);
		return j;
	}

	private static void intercambia(int []a, int i, int j){
		int aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}
