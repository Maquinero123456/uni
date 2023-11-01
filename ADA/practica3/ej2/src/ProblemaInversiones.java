public class ProblemaInversiones {
	
	public static int numInversiones(int[] v) {
		return numInv(v,0,v.length-1);
	}
	
	private static int numInv(int[] a, int prim, int ult) {
		int res = 0;
		if (prim < ult){
			res+=numInv(a,prim,(prim+ult)/2);
			res+=numInv(a,(prim+ult)/2+1,ult);
			res+=mezclar(a,prim,(prim+ult)/2,ult);
		}
		return res;
	}
	
	public static int mezclar(int[] a,int inf,int medio,int sup){
		int res = 0;
		int i = inf; int j = medio+1;
		int[] b = new int[sup-inf+1];
		int k = 0;
		while (i<=medio && j <=sup){
			if (a[i]<=a[j]){
				b[k] = a[i];
				i++;
			}else{
				res += (medio - i + 1);
				b[k] = a[j];
				j++;
			} 
			k++;
		}
	
		while (i<=medio){
			b[k] = a[i];
			i++; 
			k++;
		}
		while (j<=sup){
			b[k] = a[j];
			j++; 
			k++;
		}
		k=0;
		for (int f=inf; f<= sup; f++){
			a[f] = b[k];
			k++;
		}
		return res;
	}
}
