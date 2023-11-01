public class ArraysConRepeticiones {

	public static int encuentraElem(int [] v) {
		return encuentraElem(v,0,v.length-1);
	}
	
	private static int encuentraElem(int [] v, int izq, int der) {
		int mitad = (izq+der)/2;
		if(izq==der){
			return v[izq];
		}
		if(v[mitad]==v[mitad+1] || v[mitad]==v[mitad-1]){
			return v[mitad];
		}
		int res = -1;
		if(v[mitad]==mitad){
			res = encuentraElem(v, mitad+1, der);
		}else{
			res = encuentraElem(v, izq, mitad-1);
		}
		return res;
	}
	
}
