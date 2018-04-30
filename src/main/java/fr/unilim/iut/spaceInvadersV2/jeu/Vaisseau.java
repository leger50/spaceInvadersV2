package fr.unilim.iut.spaceInvadersV2.jeu;

public class Vaisseau {

	private int x;
	private int y;

	private int longueur;
	private int hauteur;

	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this.x = x;
		this.y = y;

		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}

	public void positionner(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public int abscisseLaPlusAGauche() {
		return this.x;
	}

	public int abscisseLaPlusADroite() {
		return this.x + this.longueur - 1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (this.abscisseLaPlusAGauche() <= x) && (x <= this.abscisseLaPlusADroite());
	}

	private int ordonneeLaPlusHaute() {
		return this.y - this.hauteur + 1;
	}

	private int ordonneeLaPlusBasse() {
		return this.y;
	}

	private boolean estOrdonneeCouverte(int y) {
		return (this.ordonneeLaPlusHaute() <= y) && (y <= this.ordonneeLaPlusBasse());
	}

	public boolean occupeLaPosition(int x, int y) {
		return (this.estAbscisseCouverte(x) && this.estOrdonneeCouverte(y));
	}

	public void seDeplacerVersLaDroite() {
		this.x++;
	}

	public void seDeplacerVersLaGauche() {
		this.x--;
	}

}
