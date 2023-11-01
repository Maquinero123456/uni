import java.lang.reflect.Array;
import java.util.Arrays;

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
				b[k] = a[j];
				res++;
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

	public static void main(String[] args) {
		System.out.println("Expected: 6");
		int[] a = {1, 8, 8, 7, -6};
		System.out.println(numInversiones(a));
		System.out.println(Arrays.toString(a));
		System.out.println("Expected: 1");
		int[] b = {5, -7};
		System.out.println(numInversiones(b));
		System.out.println(Arrays.toString(b));
		System.out.println("Expected: 1");
		int[] c = {1,2,3,4,5,7,6};
		System.out.println(numInversiones(c));
		System.out.println(Arrays.toString(c));
	}
}
