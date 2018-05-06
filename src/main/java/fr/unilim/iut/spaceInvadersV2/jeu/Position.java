package fr.unilim.iut.spaceInvadersV2.jeu;

public class Position {

	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int abscisse() {
		return this.x;
	}

	public int ordonnee() {
		return this.y;
	}
}
