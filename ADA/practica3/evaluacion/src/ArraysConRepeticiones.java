public class ArraysConRepeticiones {

	public static Integer encuentraElem(int [] v) {
		return encuentraElem(v,0,v.length-1);
	}
	
	private static Integer encuentraElem(int [] v, int izq, int der) {
		int mitad = (izq+der)/2;
		if(mitad==0){
			return null;
		}
		if(v[mitad]==v[mitad+1] || v[mitad]==v[mitad-1]){
			int i = mitad-1;
			while(i>0){
				if(v[i]!=v[mitad]){
					return v[i];
				}
				i--;
			}
		}
		Integer res = null;
		if(v[mitad]==mitad){
			res = encuentraElem(v, mitad+1, der);
		}else{
			res = encuentraElem(v, izq, mitad-1);
		}
		return res;
	}
}
