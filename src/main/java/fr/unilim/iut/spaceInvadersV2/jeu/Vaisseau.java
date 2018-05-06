package fr.unilim.iut.spaceInvadersV2.jeu;

public class Vaisseau {

	private Position origine;
	private Dimension dimension;

	public Vaisseau(Dimension dimension, Position positionOrigine) {
	    this.dimension = dimension;
	    this.origine = positionOrigine;
    }
	
	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this(new Dimension(longueur, hauteur), new Position(x, y));
	}

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);

	}

	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}

	public int abscisseLaPlusADroite() {
		return this.origine.abscisse() + this.dimension.longueur() - 1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (this.abscisseLaPlusAGauche() <= x) && (x <= this.abscisseLaPlusADroite());
	}

	private int ordonneeLaPlusHaute() {
		return this.origine.ordonnee() - this.dimension.hauteur() + 1;
	}

	private int ordonneeLaPlusBasse() {
		return this.origine.ordonnee();
	}

	private boolean estOrdonneeCouverte(int y) {
		return (this.ordonneeLaPlusHaute() <= y) && (y <= this.ordonneeLaPlusBasse());
	}

	public boolean occupeLaPosition(int x, int y) {
		return (this.estAbscisseCouverte(x) && this.estOrdonneeCouverte(y));
	}

	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() +1);
	}

	public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse() -1);
	}

}
