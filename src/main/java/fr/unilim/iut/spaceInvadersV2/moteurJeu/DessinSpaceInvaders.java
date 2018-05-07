package fr.unilim.iut.spaceInvadersV2.moteurJeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
			this.dessinerUnVaisseau(this.spaceInvaders.getVaisseau(), image);
		}
		
	}
	
	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage image) {
		Graphics2D dessinVaisseau = (Graphics2D) image.getGraphics();

		dessinVaisseau.setColor(Color.gray);
		dessinVaisseau.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(),
				vaisseau.longueur(), vaisseau.hauteur());

	}

}
