import java.util.ArrayList;
import java.util.List;

public class Antenas {
	
	private Integer[] puntosKm; //Puntos kilometricos de las urbanizaciones ordenados crecientemente.
	private int cobertura;
		
	public Antenas(Integer[] urbanizaciones, int c) {
		puntosKm = urbanizaciones;
		cobertura=c;
		
	}
	
	public List<Integer> situarAntenas(){
		List<Integer> res = new ArrayList<>();
		res.add(puntosKm[0]+cobertura);
		int numAntena = 0;
		for(int i=0; i<puntosKm.length; i++){
			if((res.get(numAntena)-cobertura)>puntosKm[i] || (res.get(numAntena)+cobertura)<puntosKm[i]){
				res.add(puntosKm[i]+cobertura);
				numAntena++;
			}
		}
		return res;
	}
	
	
}
