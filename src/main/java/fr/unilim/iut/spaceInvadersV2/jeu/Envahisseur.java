package fr.unilim.iut.spaceInvadersV2.jeu;

import fr.unilim.iut.spaceInvadersV2.utils.MissileException;

public class Envahisseur extends Sprite{

	private Direction direction;
	
	public Envahisseur(Dimension dimension, Position origine, int vitesse) {
		super(dimension, origine, vitesse);
		this.direction = Direction.DROITE;
	}
	
	public Missile tirerUnMissile(Dimension dimension, int vitesse) {
		
		if(dimension.longueur() > this.longueur()) {
			throw new MissileException("la longueur du missile est supérieure à celle de l'envahisseur.");
		}
		
		Position positionOrigineMissile = this.calculerLaPositionDeTirDuMissile(dimension);

		return new Missile(dimension, positionOrigineMissile, vitesse);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimension) {
		
		int abscisseMilieuEnvahisseur = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuEnvahisseur - (dimension.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() + dimension.hauteur();
		
		return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
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
