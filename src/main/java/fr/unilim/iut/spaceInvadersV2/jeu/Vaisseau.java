package fr.unilim.iut.spaceInvadersV2.jeu;

public class Vaisseau {

	private Position origine;
	private Dimension dimension;
	
	private int vitesse;

	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		this.dimension = dimension;
		this.origine = positionOrigine;
		this.vitesse = vitesse;
	}
	
	public Vaisseau(Dimension dimension, Position positionOrigine) {
	    this(dimension,positionOrigine,1);
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

	public int ordonneeLaPlusBasse() {
		return this.origine.ordonnee() - this.dimension.hauteur() + 1;
	}

	public int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}

	private boolean estOrdonneeCouverte(int y) {
		return (this.ordonneeLaPlusBasse() <= y) && (y <= this.ordonneeLaPlusHaute());
	}

	public boolean occupeLaPosition(int x, int y) {
		return (this.estAbscisseCouverte(x) && this.estOrdonneeCouverte(y));
	}

	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() +vitesse);
	}

	public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse() -vitesse);
	}
	
	public int hauteur() {
		return this.dimension.hauteur();
	}
	
	public int longueur() {
		return this.dimension.longueur();
	}

}
