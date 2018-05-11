package fr.unilim.iut.spaceInvadersV2.jeu;

public class Collision {

	public static boolean detecterCollision(Sprite sprite1, Sprite sprite2) {

		return (presenceCoinSprite2DansSprite1(sprite1, sprite2) || presenceCoinSprite2DansSprite1(sprite2, sprite1));
	}
	
	private static boolean presenceCoinSuperieurGaucheSprite2DansSprite1(Sprite sprite1, Sprite sprite2){
		return (sprite2.abscisseLaPlusAGauche() >= sprite1.abscisseLaPlusAGauche() && sprite2.abscisseLaPlusAGauche() <= sprite1.abscisseLaPlusADroite())
		&& (sprite2.ordonneeLaPlusHaute() >= sprite1.ordonneeLaPlusHaute() && sprite2.ordonneeLaPlusHaute() <= sprite1.ordonneeLaPlusBasse());
	}
	 
	private static boolean presenceCoinSuperieurDroiteSprite2DansSprite1(Sprite sprite1, Sprite sprite2){
		return (sprite2.abscisseLaPlusADroite() >= sprite1.abscisseLaPlusAGauche() && sprite2.abscisseLaPlusADroite() <= sprite1.abscisseLaPlusADroite())
		&& (sprite2.ordonneeLaPlusHaute() >= sprite1.ordonneeLaPlusHaute() && sprite2.ordonneeLaPlusHaute() <= sprite1.ordonneeLaPlusBasse());
	}
	
	private static boolean presenceCoinInferieurGaucheSprite2DansSprite1(Sprite sprite1, Sprite sprite2){
		return (sprite2.abscisseLaPlusAGauche() >= sprite1.abscisseLaPlusAGauche() && sprite2.abscisseLaPlusAGauche() <= sprite1.abscisseLaPlusADroite())
				&& (sprite2.ordonneeLaPlusBasse() >= sprite1.ordonneeLaPlusHaute() && sprite2.ordonneeLaPlusBasse() <= sprite1.ordonneeLaPlusBasse());
	}
	
	private static boolean presenceCoinInferieurDroiteSprite2DansSprite1(Sprite sprite1, Sprite sprite2){
		return (sprite2.abscisseLaPlusADroite() >= sprite1.abscisseLaPlusAGauche() && sprite2.abscisseLaPlusADroite() <= sprite1.abscisseLaPlusADroite())
				&& (sprite2.ordonneeLaPlusBasse() >= sprite1.ordonneeLaPlusHaute() && sprite2.ordonneeLaPlusBasse() <= sprite1.ordonneeLaPlusBasse());
	}
	
	private static boolean presenceCoinSprite2DansSprite1(Sprite sprite1, Sprite sprite2) {
		return (presenceCoinSuperieurGaucheSprite2DansSprite1(sprite1, sprite2) || presenceCoinSuperieurDroiteSprite2DansSprite1(sprite1, sprite2)
				|| presenceCoinInferieurGaucheSprite2DansSprite1(sprite1, sprite2) || presenceCoinInferieurDroiteSprite2DansSprite1(sprite1, sprite2));
		
	}

}
