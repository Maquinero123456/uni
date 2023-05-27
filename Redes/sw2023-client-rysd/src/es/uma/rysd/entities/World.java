package es.uma.rysd.entities;

//Clase obtenida cuando se consulta la informacin de un planeta

public class World {
	public String name;
	public String diameter;
	public String rotation_period;
	public String orbital_period;
	public String gravity;
	public String population;
	public String climate;
	public String terrain;
	public String surface_water;
	public String[] residents;
	
	public String toString(){
		return name + " ("+terrain+" - "+climate+")";
	}
}
