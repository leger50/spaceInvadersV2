package fr.unilim.iut.spaceInvadersV2.jeu;

import fr.unilim.iut.spaceInvadersV2.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceInvadersV2.utils.HorsEspaceJeuException;

public class SpaceInvaders {

	private static final char MARQUE_FIN_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';

	private int longueur;
	private int hauteur;

	Vaisseau vaisseau;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position) {
		
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!this.estDansEspaceJeu(x, y)) {
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");
		}

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!this.estDansEspaceJeu(x + longueurVaisseau - 1, y)) {
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		}
		if (!this.estDansEspaceJeu(x, y - hauteurVaisseau + 1)) {
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");
		}

		this.vaisseau = new Vaisseau(longueurVaisseau, hauteurVaisseau);
		this.vaisseau.positionner(x, y);

	}

	public void deplacerVaisseauVersLaGauche() {
		if (this.vaisseau.abscisseLaPlusAGauche() > 0) {
			this.vaisseau.seDeplacerVersLaGauche();
		}
	}

	public void deplacerVaisseauVersLaDroite() {

		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			this.vaisseau.seDeplacerVersLaDroite();
		}
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();

		for (int y = 0; y < this.hauteur; y++) {
			for (int x = 0; x < this.longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}

			espaceDeJeu.append(MARQUE_FIN_LIGNE);
		}

		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;

		if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
			marque = MARQUE_VAISSEAU;
		} else {
			marque = MARQUE_VIDE;
		}

		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && this.vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return this.vaisseau != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur));
	}
}
