import practica2021a.*;


public class main {

	public static void main(String[] args) {
		Malla a1 = new Malla(69, 5, 5, 3);
		Malla a2 = new Malla(42, 3, 10, 8);
		Malla a3 = new Malla(77, 10, 10, 20);
		Malla a4 = new Malla(18, 50, 20, 240);
		Malla a5 = new Malla(13, 70, 10, 140);
		Malla a6 = new Malla(12, 80, 20, 270);
		Malla a7 = new Malla(444, 90, 30, 300);
		Malla a8 = new Malla(666, 500, 10, 1000);
		Malla a9 = new Malla(989, 20, 80, 200);
		Malla a10 = new Malla(277, 300, 30, 1000);
		
		int i = 1;
		
		boolean[][] problema1= a1.generarMatriz();
		System.out.println("MALLA "+i);
		a1.ver(problema1);
		EstadoMalla problemon= new EstadoMalla(a1.getXi(), a1.getYi(), problema1, a1.getXf(), a1.getYf());
		EstadoMalla estadoFinal= new EstadoMalla(a1.getXf(), a1.getYf());
		AgenteA ag1 = new AgenteA();
		System.out.println("\n");
		long res1 = System.nanoTime();
		System.out.println(ag1.resuelve(problemon, estadoFinal));
		long res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema2 = a2.generarMatriz();
		a2.ver(problema2);
		problemon = new EstadoMalla(a2.getXi(), a2.getYi(), problema2, a2.getXf(), a2.getYf());
		estadoFinal= new EstadoMalla(a2.getXf(), a2.getYf());
		AgenteA ag2 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag2.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema3 = a3.generarMatriz();
		a3.ver(problema3);
		problemon = new EstadoMalla(a3.getXi(), a3.getYi(), problema3, a3.getXf(), a3.getYf());
		estadoFinal= new EstadoMalla(a3.getXf(), a3.getYf());
		AgenteA ag3 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag3.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema4 = a4.generarMatriz();
		a4.ver(problema4);
		problemon = new EstadoMalla(a4.getXi(), a4.getYi(), problema4, a4.getXf(), a4.getYf());
		estadoFinal= new EstadoMalla(a4.getXf(), a4.getYf());
		AgenteA ag4 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag4.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema5 = a5.generarMatriz();
		a5.ver(problema5);
		problemon = new EstadoMalla(a5.getXi(), a5.getYi(), problema5, a5.getXf(), a5.getYf());
		estadoFinal= new EstadoMalla(a5.getXf(), a5.getYf());
		AgenteA ag5 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag5.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema6 = a6.generarMatriz();
		a6.ver(problema6);
		problemon = new EstadoMalla(a6.getXi(), a6.getYi(), problema6, a6.getXf(), a6.getYf());
		estadoFinal= new EstadoMalla(a6.getXf(), a6.getYf());
		AgenteA ag6 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag6.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema7 = a7.generarMatriz();
		a7.ver(problema7);
		problemon = new EstadoMalla(a7.getXi(), a7.getYi(), problema7, a7.getXf(), a7.getYf());
		estadoFinal= new EstadoMalla(a7.getXf(), a7.getYf());
		AgenteA ag7 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag7.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema8 = a8.generarMatriz();
		a8.ver(problema8);
		problemon = new EstadoMalla(a8.getXi(), a8.getYi(), problema8, a8.getXf(), a8.getYf());
		estadoFinal= new EstadoMalla(a8.getXf(), a8.getYf());
		AgenteA ag8 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag5.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema9 = a9.generarMatriz();
		a9.ver(problema9);
		problemon = new EstadoMalla(a9.getXi(), a9.getYi(), problema9, a9.getXf(), a9.getYf());
		estadoFinal= new EstadoMalla(a9.getXf(), a9.getYf());
		AgenteA ag9 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag5.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		boolean[][] problema10 = a10.generarMatriz();
		a10.ver(problema10);
		problemon = new EstadoMalla(a10.getXi(), a10.getYi(), problema10, a10.getXf(), a10.getYf());
		estadoFinal= new EstadoMalla(a10.getXf(), a10.getYf());
		AgenteA ag10 = new AgenteA();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(ag10.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		
		//SEGUNDA IMPLEMENTACION
		
		System.out.println("\nMALLA "+i);
		a1.ver(problema1);
		problemon= new EstadoMalla(a1.getXi(), a1.getYi(), problema1, a1.getXf(), a1.getYf());
		estadoFinal= new EstadoMalla(a1.getXf(), a1.getYf());
		AgenteB agB1 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB1.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a2.ver(problema2);
		problemon= new EstadoMalla(a2.getXi(), a2.getYi(), problema2, a2.getXf(), a2.getYf());
		estadoFinal= new EstadoMalla(a2.getXf(), a2.getYf());
		AgenteB agB2 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB2.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a3.ver(problema3);
		problemon= new EstadoMalla(a3.getXi(), a3.getYi(), problema3, a3.getXf(), a3.getYf());
		estadoFinal= new EstadoMalla(a3.getXf(), a3.getYf());
		AgenteB agB3 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB3.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a4.ver(problema4);
		problemon= new EstadoMalla(a4.getXi(), a4.getYi(), problema4, a4.getXf(), a4.getYf());
		estadoFinal= new EstadoMalla(a4.getXf(), a4.getYf());
		AgenteB agB4 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB4.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a5.ver(problema5);
		problemon= new EstadoMalla(a5.getXi(), a5.getYi(), problema5, a5.getXf(), a5.getYf());
		estadoFinal= new EstadoMalla(a5.getXf(), a5.getYf());
		AgenteB agB5 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB5.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
	
		System.out.println("\nMALLA "+i);
		a6.ver(problema6);
		problemon= new EstadoMalla(a6.getXi(), a6.getYi(), problema6, a6.getXf(), a6.getYf());
		estadoFinal= new EstadoMalla(a6.getXf(), a6.getYf());
		AgenteB agB6 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB6.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a7.ver(problema7);
		problemon= new EstadoMalla(a7.getXi(), a7.getYi(), problema7, a7.getXf(), a7.getYf());
		estadoFinal= new EstadoMalla(a7.getXf(), a7.getYf());
		AgenteB agB7 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB7.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
	
		System.out.println("\nMALLA "+i);
		a8.ver(problema8);
		problemon= new EstadoMalla(a8.getXi(), a8.getYi(), problema8, a8.getXf(), a8.getYf());
		estadoFinal= new EstadoMalla(a8.getXf(), a8.getYf());
		AgenteB agB8 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB8.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a9.ver(problema9);
		problemon= new EstadoMalla(a9.getXi(), a9.getYi(), problema9, a9.getXf(), a9.getYf());
		estadoFinal= new EstadoMalla(a9.getXf(), a9.getYf());
		AgenteB agB9 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB9.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
		
		System.out.println("\nMALLA "+i);
		a10.ver(problema10);
		problemon= new EstadoMalla(a10.getXi(), a10.getYi(), problema10, a10.getXf(), a10.getYf());
		estadoFinal= new EstadoMalla(a10.getXf(), a10.getYf());
		AgenteB agB10 = new AgenteB();
		System.out.println("\n");
		res1 = System.nanoTime();
		System.out.println(agB10.resuelve(problemon, estadoFinal));
		res2 = System.nanoTime();
		System.out.println("Tiempo: "+(res2-res1));
		i++;
	
	}
}
