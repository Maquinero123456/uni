package es.uma.rysd.entities;

//Clase obtenida cuando se consulta por una nave

public class SpaceShip {
	public String name;
	public String model;
	public String starship_class;
	public String cost_in_credits;
	public String length;
	public String[] pilots;


	@Override
	public String toString() {
		return "{" +
			" name='" +  name + "'" +
			", model='" +  model + "'" +
			", starship_class='" +  starship_class + "'" +
			", cost_in_credits='" +  cost_in_credits + "'" +
			", length='" +  length + "'" +
			", pilots='" +  pilots + "'" +
			"}";
	}
	
}
