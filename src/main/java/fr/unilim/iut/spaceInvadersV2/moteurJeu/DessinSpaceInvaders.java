package fr.unilim.iut.spaceInvadersV2.moteurJeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceInvadersV2.jeu.Constantes;
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

		this.dessinerUnFond(image);
		
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
		
		this.dessinerUnScore(this.spaceInvaders.obtenirScore(), image);
		
	}
	
	private void dessinerUnFond(BufferedImage image) {
		Graphics2D dessinFond = (Graphics2D) image.getGraphics();

		dessinFond.setColor(Color.black);
		dessinFond.fillRect(0, 0, Constantes.ESPACEJEU_LONGUEUR, Constantes.ESPACEJEU_HAUTEUR + Constantes.ESPACESCORE_HAUTEUR);

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
	
	private void dessinerUnScore(int score, BufferedImage image) {
		Graphics2D dessinScore = (Graphics2D) image.getGraphics();		
		
		dessinScore.drawLine(0, Constantes.ESPACEJEU_HAUTEUR, Constantes.ESPACEJEU_LONGUEUR, Constantes.ESPACEJEU_HAUTEUR);
		
		dessinScore.setColor(Color.green);
		dessinScore.setFont(new Font("Snap ITC", Font.BOLD, 20));
		dessinScore.drawString("Score : " + score, Constantes.SCORE_AFFICHAGE_X, Constantes.SCORE_AFFICHAGE_Y);

	}

}
