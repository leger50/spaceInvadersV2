package fr.unilim.iut.spaceInvadersV2.moteurJeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceInvadersV2.jeu.Envahisseur;
import fr.unilim.iut.spaceInvadersV2.jeu.Missile;
import fr.unilim.iut.spaceInvadersV2.jeu.SpaceInvaders;
import fr.unilim.iut.spaceInvadersV2.jeu.Vaisseau;

public class DessinSpaceInvaders implements DessinJeu{

	/**
	 * lien vers le jeu a afficher
	 */
	private SpaceInvaders spaceInvaders;
	
	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
	}
	
	@Override
	public void dessiner(BufferedImage image) {

		if(this.spaceInvaders.aUnVaisseau()) {
			this.dessinerUnVaisseau(this.spaceInvaders.recupererLeVaisseau(), image);
		}
		
		if(this.spaceInvaders.aDesMissiles()) {
			
			for (final Missile missile : this.spaceInvaders.recupererListeMissiles()) {
				this.dessinerUnMissile(missile, image);
			}
		}
		
		if(this.spaceInvaders.aDesEnvahisseurs()) {
			
			for (final Envahisseur envahisseur : this.spaceInvaders.recupererListeEnvahisseurs()) {
				this.dessinerUnEnvahisseur(envahisseur, image);
			}
		}
		
	}
	
	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage image) {
		Graphics2D dessinVaisseau = (Graphics2D) image.getGraphics();

		dessinVaisseau.setColor(Color.gray);
		dessinVaisseau.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute(),
				vaisseau.longueur(), vaisseau.hauteur());

	}
	
	private void dessinerUnMissile(Missile missile, BufferedImage image) {
		Graphics2D dessinMissile = (Graphics2D) image.getGraphics();

		dessinMissile.setColor(Color.blue);
		dessinMissile.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusHaute(),
				missile.longueur(), missile.hauteur());

	}
	
	private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage image) {
		Graphics2D dessinVaisseau = (Graphics2D) image.getGraphics();

		dessinVaisseau.setColor(Color.red);
		dessinVaisseau.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute(),
				envahisseur.longueur(), envahisseur.hauteur());

	}

}
