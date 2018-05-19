package fr.unilim.iut.spaceInvadersV2.jeu;

public class Missile extends Sprite{

	private Direction directionVerticale;
	
	public Missile(Dimension dimension, Position origine, int vitesse, Direction directionVerticale) {
		super(dimension, origine, vitesse);
		this.directionVerticale = directionVerticale;
	}
	
	public Direction directionVerticale() {
		return this.directionVerticale;
	}

}
