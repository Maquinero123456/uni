package Utilidades;

public class ArrayUtilities {
	
	public final static char CR  = (char) 0x0D;
	public final static char LF  = (char) 0x0A; 

	public final static String CRLF  = "" + CR + LF;     // "" forces conversion to string

	
	/**
	 * Reallocates an array with a new size, and copies the contents
	 * of the old array to the new array.
	 * @param oldArray  the old array, to be reallocated.
	 * @param newSize   the new array size.
	 * @return          A new array with the same contents.
	 */
	 // method copy content from old Array to newly created array.
	public static Object resizeArray (Object oldArray, int newSize) {
	    int oldSize = java.lang.reflect.Array.getLength(oldArray);
	    @SuppressWarnings("rawtypes")
		Class elementType = oldArray.getClass().getComponentType();
	    Object newArray = java.lang.reflect.Array.newInstance(elementType,newSize);
	    int preserveLength = Math.min(oldSize,newSize);
	    if (preserveLength > 0)
	       System.arraycopy (oldArray,0,newArray,0,preserveLength);
	    return newArray;
	 }
	
	public static double MaximunValue(double[] darr){
		double value = darr[0];
		for(int i = 1, x = darr.length; i<x; i++){
			if(darr[i] > value)
				value = darr[i];
		}
		
		return value;
	}
	
	public static double MinimunValue(double[] darr){
		double value = darr[0];
		for(int i = 1, x = darr.length; i<x; i++){
			if(darr[i] < value)
				value = darr[i];
		}
		
		return value;
	}
}
