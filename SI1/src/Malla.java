import java.util.Random;
public class Malla {
	private long seed;
	private int nf, nc, no;
	private int xi, yi, xf, yf;
	
	public Malla(long seed, int nf, int nc, int no) {
		this.seed=seed;
		this.nf=nf;
		this.nc=nc;
		this.no=no;
	}
	
	public boolean[][] generarMatriz(){
		Random r1= new Random(seed);
		boolean[][] problema= new boolean[nf][nc];
		problema=anadirObstaculo(problema, r1);
		problema=anadirIF(problema, r1);
		return problema;
	}
	
	private boolean[][] anadirObstaculo(boolean[][] problema, Random r1){
		int ra, co=0;
		while(co<no) {
			for(int i=0; i<nf; i++) {
				for(int j=0; j<nc; j++) {
					ra= r1.nextInt(20);
					if(ra==5 && co<this.no && problema[i][j]!=true) {
						problema[i][j]=true;
						co++;
					}else if(problema[i][j]!=true){
						problema[i][j]=false;
					}
				}
			}
		}
		
		return problema;
	}
	
	private boolean[][] anadirIF(boolean[][] problema, Random r1){
		int ra;
		boolean ini=false, fin=false;
		while(!ini && !fin) {
			for(int i=0; i<nf; i++) {
				for(int j=0; j<nc; j++) {
					ra= r1.nextInt(20);
					if(problema[i][j]!=true&& xf!=i&& yf!=j&& !ini&& ra==2) {
						xi=i;
						yi=j;
						ini=true;
					}
					if(problema[i][j]!=true&& xi!=i&& yi!=j&& !fin&& ra==10) {
						xf=i;
						yf=i;
						fin=true;
					}
				}
			}
		}
		return problema;
	}
	
	public void ver(boolean[][] problema) {
		char[][] vista=new char[nf][nc];
		for(int i= 0; i<nf; i++) {
			for(int j= 0; j<nc; j++) {
				if(problema[i][j]) {
					vista[i][j]='x';
				}else {
					vista[i][j]='o';
				}
			}
		}
		vista[xi][yi]='f';
		vista[xf][yf]='i';
		
		
		for(int i=0; i<nf; i++) {
			System.out.print("\n");
			for(int j=0; j<nc; j++) {
				System.out.print(vista[i][j]);
				System.out.print(" ");
			}
		}
	}
	
	public int getNf() {
		return nf;
	}

	public int getNc() {
		return nc;
	}

	public int getXi() {
		return xi;
	}

	public int getYi() {
		return yi;
	}

	public int getXf() {
		return xf;
	}

	public int getYf() {
		return yf;
	}

}
