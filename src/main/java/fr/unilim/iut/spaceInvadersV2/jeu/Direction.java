package fr.unilim.iut.spaceInvadersV2.jeu;

public enum Direction {

	HAUT(1), 
	BAS(-1), 
	GAUCHE(-1), 
	DROITE(1),

	HAUT_ECRAN(-1), 
	BAS_ECRAN(1);

	private int valeur;

	private Direction(int valeur) {
		this.valeur = valeur;
	}

	public int valeur() {
		return this.valeur;
	}

}
