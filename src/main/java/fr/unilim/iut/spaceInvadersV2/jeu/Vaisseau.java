package fr.unilim.iut.spaceInvadersV2.jeu;

import fr.unilim.iut.spaceInvadersV2.utils.MissileException;

public class Vaisseau extends Sprite {

	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}

	public Missile tirerUnMissile(Dimension dimension, int vitesse) {
		
		if(dimension.longueur() > this.longueur()) {
			throw new MissileException("la longueur du missile est supérieure à celle du vaisseau.");
		}
		
		Position positionOrigineMissile = this.calculerLaPositionDeTirDuMissile(dimension);

		return new Missile(dimension, positionOrigineMissile, vitesse);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimension) {
		
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimension.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		
		return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
	}
}
