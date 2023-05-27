package es.uma.rysd.entities;

// Clase obtenida cuando se consulta la informacin de un personaje

public class Person {
	public String name;
	public String birth_year;
	public String eye_color;
	public String gender;
	public String hair_color;
	public String height;
	public String mass;
	public String skin_color;
	public String homeworld;
	public String[] films;
	public String[] species;
	public String[] starships;
	public String[] vehicles;
	
	// Todos los atributos anteriores se obtenan directamente del objeto devuelto por la consulta
	// Los siguientes se deben rellenar si se considera necesario consultando las URL devueltas en la consulta
	// en los respectivos campos anteriores.
	public World homeplanet = null;
	public Movie[] movies = null;
	public Specie[] especies = null;
	public SpaceShip[] naves = null;
	public Vehicle[] vehiculos = null;
	
	public String toString(){
		String text = name + " ("+ gender +") nacio en el a�o " + birth_year;
		if(homeplanet != null ) text+= " en "+homeplanet;
		text += "\nPesa: " + mass + " Kg y mide: " + height + " cm\n";
		if(movies != null){
			text += "Aparece en:\n";
			for(Movie f: movies){
				text += "- "+f+"\n";
			}
		}
		return text;		
	}
}
