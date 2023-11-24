import java.util.ArrayList;
import java.util.List;

public class Antenas {
	
	private Integer[] puntosKm; //Puntos kilometricos de las urbanizaciones ordenados crecientemente.
	private int cobertura;
		
	public Antenas(Integer[] urbanizaciones, int c) {
		puntosKm = urbanizaciones;
		cobertura=c;
		
	}
	
	public List<Integer> situarAntenas(List<Integer> elegidas){
		List<Integer> res = new ArrayList<>();
		res.add(puntosKm[0]+cobertura);
		int numAntena = 0;
		int urbElegida = 0;
		for(int i=0; i<puntosKm.length; i++){
			if((elegidas.size()>0 && puntosKm[i]==puntosKm[elegidas.get(urbElegida)]) || (res.get(numAntena)-cobertura)>puntosKm[i] || (res.get(numAntena)+cobertura)<puntosKm[i] ){
				if(res.indexOf(puntosKm[i])!=-1){
					urbElegida++;
					if(urbElegida>elegidas.size()-1){
						urbElegida=0;
					}
				}else if(elegidas.size()>0 &&puntosKm[i]==puntosKm[elegidas.get(urbElegida)]){
					res.add(puntosKm[elegidas.get(urbElegida)]);
					urbElegida++;
					if(urbElegida>elegidas.size()-1){
						urbElegida=0;
					}
					numAntena++;
				}else{
					res.add(puntosKm[i]+cobertura);
					numAntena++;
				}
				
			}
		}
		return res;
	}
	
	
}
