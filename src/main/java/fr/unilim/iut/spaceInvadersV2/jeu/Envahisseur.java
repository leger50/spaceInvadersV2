package fr.unilim.iut.spaceInvadersV2.jeu;

public class Envahisseur extends Sprite{

	private Direction direction;
	
	public Envahisseur(Dimension dimension, Position origine, int vitesse) {
		super(dimension, origine, vitesse);
		this.direction = Direction.DROITE;
	}
	
	public void modifierDirection() {
		
		switch(this.direction) {
			case GAUCHE :
				this.direction = Direction.DROITE;
				break;
			
			case DROITE :
				this.direction = Direction.GAUCHE;
				break;
				
			default :
				break;
		}
	}
	
	public Direction direction() {
		return this.direction;
	}

}
