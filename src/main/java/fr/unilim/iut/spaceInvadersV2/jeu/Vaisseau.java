package fr.unilim.iut.spaceInvadersV2.jeu;

public class Vaisseau {
	
	private int x;
	private int y;
	
	public Vaisseau(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int abscisse() {
		return this.x;
	}
	
	public boolean occupeLaPosition(int x, int y) {
		return (this.x==x) && (this.y==y);
	}

	public void seDeplacerVersLaDroite() {
		this.x++;
		
	}

}
