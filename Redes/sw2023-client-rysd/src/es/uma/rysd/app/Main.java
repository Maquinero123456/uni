package es.uma.rysd.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import es.uma.rysd.entities.Person;
import es.uma.rysd.entities.World;

public class Main {	
	private static Random rand; // para nmeros aleatorios
	private static Scanner sc; // para leer de teclado
	private final static String proxy = "proxy.lcc.uma.es";
	private final static String proxy_port = "3128";
	
    public static void main(String[] args) {
    	// Descomente las siguiente lneas si lo est probando en el laboratorio y accede a Internet usando el proxy
    	// System.setProperty("https.proxyHost",proxy); 
    	// System.setProperty("https.proxyPort",proxy_port);
        SWClient sw = new SWClient();
		System.out.println(sw.search("luke"));
        String response = null;
    	rand = new Random();
		System.out.print("> ");
		sc = new Scanner(System.in);
        do{
			whoBornIn1(sw);
	       	System.out.println("Desea otra ronda (s/n)?");
			System.out.print("> ");
	       	response = sc.nextLine();
	    }while(response.equals("s"));
        sc.close();
    }
    
    // Genera un nmero entre 0 y max-1 que no haya sido usado previamente (los usados vienen en l)
    public static Integer getRandomResource(int max, List<Integer> l){
    	if(max == l.size()) return null;

    	Integer p = rand.nextInt(max);
    	while(l.contains(p)){
    		p = (p+1)%max;
    	}
    	return p;
    }
    
    // Pregunta que obtiene dos recursos iguales (personajes en este caso) y los compara
    public static void tallest(SWClient sw){
    	// Obteniendo la cantidad de gente almacenada
    	int max_people = sw.getNumberOfResources("people");
    	if(max_people == 0){
    		System.out.println("No se encontraron personas.");
    		return;
    	}
    	
    	System.out.println("Generando nueva pregunta...");
    	// Cogiendo dos personas al azar sin repetir
        List<Integer> used = new ArrayList<Integer>();
    	List<Person> people = new ArrayList<Person>();
    	int counter = 0;
    	while(counter < 2){
    		Integer p = getRandomResource(max_people, used);
    		Person person = sw.getPerson(sw.generateEndpoint("people", p));
    		if(person == null){
    			System.out.println("Hubo un error al encontrar el recurso " + p);
    		} else {
    			people.add(person);
    			counter++;
    		}
    		used.add(p);
    	}
    	
    	// Escribiendo la pregunta y leyendo la opcin
    	Integer n = null;
    	do{
    		System.out.println("Quin es ms alto? [0] "+ people.get(0).name + " o [1] " + people.get(1).name);
    		try{
    			n = Integer.parseInt(sc.nextLine());
    		}catch(NumberFormatException ex){
    			n = -1;
    		}
    	}while(n != 0 && n != 1);
    	
    	// Mostrando la informacin de los personajes elegidos
    	for(Person p: people){
    		System.out.println(p.name + " mide " + p.height);
    	}
    	
    	// Resultado
    	if(Double.parseDouble(people.get(n).height) >= Double.parseDouble(people.get((n+1)%2).height)){
    		System.out.println("Enhorabuena!!! "+ success[rand.nextInt(success.length)]);
    	} else {
    		System.out.println("Fallaste :( " + error[rand.nextInt(error.length)]);
    	}
    }
    
    // Pregunta que relaciona varios recursos:
    // - Elige un recurso (planeta en este caso)
    // - Pregunta sobre algn recurso relacionado (persona que naci o fue crear ah)
    // - Busca ese recurso y comprueba si est relacionado con el primero (si la persona buscada tiene como planeta el original)
    public static void whoBornIn1(SWClient sw){
    	// Obteniendo la cantidad de planetas
    	int max_planet = sw.getNumberOfResources("planets");
    	if(max_planet == 0){
    		System.out.println("No se encontraron planetas.");
    		return;
    	}
    	
    	System.out.println("Generando nueva pregunta...");
    	// Obteniendo el planeta (que tenga personas)
        List<Integer> used = new ArrayList<Integer>();
        World world = null;
        do{
        	Integer p = getRandomResource(max_planet, used);
        	world = sw.getWorld(sw.generateEndpoint("planets", p));
    		if(world == null){
    			System.out.println("Hubo un error al encontrar el recurso " + p);
    		}
        	used.add(p);
        }while(world == null || world.residents == null || world.residents.length < 1);

        // Planteamos la pregunta
        String s = null;
   		System.out.println("Quin naci o fue creado en " + world.name + "?");
   		s = sc.nextLine();
   		// Buscamos la persona indicada
   		Person p = sw.search(s);
   		
   		// Validamos la respuesta y mostramos sus datos
   		if(p == null){
   			System.out.println("No hay nadie con ese nombre");
   		} else {
   			System.out.println(p.name + " naci en " + p.homeplanet.name);
   		}
   		
   		// Resultados
   		if(p != null && p.homeplanet.name.equals(world.name)){
    		System.out.println("Enhorabuena!!! "+ success[rand.nextInt(success.length)]);
    	} else {
    		System.out.println("Fallaste :( " + error[rand.nextInt(error.length)]);
    	}
    }
    
    // Similar a la previa pero en vez de solicitar que escriba se le ofrecen alternativa para ello:
    // - Se elige una al azar de las disponibles en el recurso, persona del planeta (la correcta)
    // - Se buscar aleatoriamente otras 3 que no estn relacionados con el recurso (las incorrectas)
    // - Se inserta la correcta de forma aleatoria
    public static void whoBornIn2(SWClient sw){
    	
    	// Obteniendo la cantidad de planetas y personas
    	int max_people = sw.getNumberOfResources("people");
    	int max_planet = sw.getNumberOfResources("planets");
    	if(max_people == 0 || max_planet == 0){
    		System.out.println("No se encontraron personas o planetas.");
    		return;
    	}
    	
    	System.out.println("Generando nueva pregunta...");
    	// Obteniendo el planeta (con personas)
        List<Integer> used = new ArrayList<Integer>();
        World world = null;
        do{
        	Integer p = getRandomResource(max_planet, used);
        	world = sw.getWorld(sw.generateEndpoint("planets", p));
    		if(world == null){
    			System.out.println("Hubo un error al encontrar el recurso " + p);
    		}
        	used.add(p);
        }while(world == null || world.residents == null || world.residents.length < 1);
        used.clear();
        // Cogemos uno al azar como acierto
        String [] residents = world.residents;
        Person correct = sw.getPerson(residents[rand.nextInt(residents.length)]);
        // Metemos todas las personas del planeta como usados para que no se seleccionen
        for(String s: residents){
        	used.add(sw.getIDFromURL(s));
        }
        
        // Buscamos 3 ms
        List<Person> people = new ArrayList<Person>();
        int contador = 0;
    	while(contador < 3){
    		Integer p = getRandomResource(max_people, used);
    		Person person = sw.getPerson(sw.generateEndpoint("people", p));
    		if(person == null){
    			System.out.println("Hubo un error al encontrar el recurso " + p);
    		} else {
    			people.add(person);
    			contador++;
    		}
    		used.add(p);
    	}
    	// Metemos el correcto de forma aleatoria
    	int pos_correct = rand.nextInt(4);
    	people.add(pos_correct, correct);
    	
    	// Leemos la opcin
    	Integer n = null;
    	do{
    		System.out.print("Quin naci o fue fabricado en "+ world.name +"?");
    		for(int i = 0; i < 4; i++){
    			System.out.print(" ["+i+"] "+ people.get(i).name);
    		}
    		System.out.println();
    		try{
    			n = Integer.parseInt(sc.nextLine());
    		}catch(NumberFormatException ex){
    			n = -1;
    		}
    	}while(n < 0 || n > 3);
    	
    	// Se muestran los resultados    	
    	for(Person p: people){
    		System.out.println(p.name + " nacio en " + p.homeplanet.name);
    	}
    	
    	// Resultados
    	if(n == pos_correct){
    		System.out.println("Enhorabuena!!! "+ success[rand.nextInt(success.length)]);
    	} else {
    		System.out.println("Fallaste :( " + error[rand.nextInt(error.length)]);
    	}
    }     
  
	private static String [] success = {"This is the way",
			"Eres uno con la Fuerza. La Fuerza esta contigo",
			"Que la fuerza te acompane",
			"Nada ocurre por accidente",
			"Sin duda, maravillosa tu mente es",
			"Cuando te fuiste, no era mas que el aprendiz. Ahora eres el maestro",
			"La Fuerza te esta llamando, dejala entrar",
			"Tu cantidad de midiclorianos debe ser muy alta",
			"Una leccion aprendida es una leccion ganada",
			"Debes creer en ti mismo o nadie lo haro",
			"El camino a la sabiduria es sencillo para aquellos que no se dejan cegar por el ego" };
	private static String [] error = {"El mejor profesor, el fracaso es.",
			"El miedo es el camino hacia el Lado Oscuro. El miedo lleva a la ira, la ira lleva al odio, el odio lleva al sufrimiento. Percibo mucho miedo en ti",
			"Tu carencia de fe resulta molesta",
			"La capacidad de hablar no te hace inteligente",
			"Concontrate en el momento. Siente, no pienses, usa tu instinto",
			"No lo intentes. Hazlo, o no lo hagas, pero no lo intentes",
			"Paciencia, utiliza la Fuerza. Piensa",
			"Siento una perturbacion en la fuerza",
			"El lado oscurso se intensifica en ti",
			"El primer paso para corregir un error es la paciencia",
			"El exceso de confianza es el mas peligroso de los descuidos",
			"El camino de la ignorancia es guiado por el miedo" };

}
