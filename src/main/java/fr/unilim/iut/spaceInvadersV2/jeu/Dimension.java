package fr.unilim.iut.spaceInvadersV2.jeu;

public class Dimension {
	
	private int longueur;
	private int hauteur;

	public Dimension(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public int longueur() {
		return this.longueur;
	}

	public int hauteur() {
		return this.hauteur;
	}
}
