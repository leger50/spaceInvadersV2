package fr.unilim.iut.spaceInvadersV2.moteurJeu;

import fr.unilim.iut.spaceInvadersV2.jeu.Constantes;
import fr.unilim.iut.spaceInvadersV2.jeu.SpaceInvaders;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// creation du jeu particulier et de son afficheur
		SpaceInvaders jeuSpaceInvaders = new SpaceInvaders(Constantes.ESPACEJEU_LONGUEUR, Constantes.ESPACEJEU_HAUTEUR);
		jeuSpaceInvaders.initialiserJeu();
		
		DessinSpaceInvaders dessinSpaceInvaders = new DessinSpaceInvaders(jeuSpaceInvaders);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeuSpaceInvaders, dessinSpaceInvaders);
		moteur.lancerJeu(Constantes.ESPACEJEU_LONGUEUR, Constantes.ESPACEJEU_HAUTEUR);
	}
}
