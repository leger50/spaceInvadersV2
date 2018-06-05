package fr.unilim.iut.spaceInvadersV2.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Horde {

	private Dimension dimensionEnvahisseur;
	private int vitesseEnvahisseur;

	private List<Envahisseur> listeEnvahisseurs;

	private int nombreEnvahisseursParLigne;
	private int nombreLignes;
	private int espaceHorizontalEnvahisseur;
	private int espaceVerticalEnvahisseur;

	public Horde(Dimension dimensionEnvahisseur, int vitesseEnvahisseur, int espaceHorizontalEnvahisseur,
			int espaceVerticalEnvahisseur, int nombreEnvahisseursParLigne, int nombreDeLignes) {

		this.dimensionEnvahisseur = dimensionEnvahisseur;
		this.vitesseEnvahisseur = vitesseEnvahisseur;

		this.listeEnvahisseurs = new ArrayList<>();

		this.nombreEnvahisseursParLigne = nombreEnvahisseursParLigne;
		this.nombreLignes = nombreDeLignes;
		this.espaceHorizontalEnvahisseur = espaceHorizontalEnvahisseur;
		this.espaceVerticalEnvahisseur = espaceVerticalEnvahisseur;

	}

	public void positionnerUneHordeEnvahisseurs() {

		int positionOrdonnee = this.dimensionEnvahisseur.hauteur() - 1;

		for (int i = 0; i < this.nombreLignes; i++) {
			this.positionnerUneLigneEnvahisseurs(positionOrdonnee);

			positionOrdonnee += this.dimensionEnvahisseur.hauteur() + this.espaceVerticalEnvahisseur;
		}
	}
	
	public void positionnerUneLigneEnvahisseurs(int ordonneePosition) {

		int abscisseEnvahisseur = 0;

		for (int i = 0; i < this.nombreEnvahisseursParLigne; i++) {
			this.positionnerUnEnvahisseur(new Position(abscisseEnvahisseur, ordonneePosition));
			
			abscisseEnvahisseur += this.dimensionEnvahisseur.longueur() + this.espaceHorizontalEnvahisseur;
		}
	}
	
	public void positionnerUnEnvahisseur(Position position) {

		Envahisseur envahisseurAPlacer = new Envahisseur(this.dimensionEnvahisseur, position, this.vitesseEnvahisseur);
		this.listeEnvahisseurs.add(envahisseurAPlacer);
	}
	
	public void changerDirectionHorizontaleEnvahisseur() {

		for (Envahisseur envahisseur : this.listeEnvahisseurs) {
			envahisseur.modifierDirection();
			envahisseur.deplacerHorizontalementVers(envahisseur.direction());
		}
	}
	
	public boolean lesEnvahisseursTirent(int pourcentageChanceTir) {
		Random nbAleatoire = new Random();

		return nbAleatoire.nextInt(100) < pourcentageChanceTir;
	}
	
	public Envahisseur choisirUnEnvahisseur() {
		Random nbAleatoire = new Random();
		
		return this.listeEnvahisseurs.get(nbAleatoire.nextInt(this.listeEnvahisseurs.size()));
	}
	
	public List<Envahisseur> recupererLesEnvahisseurs(){
		return this.listeEnvahisseurs;
	}
	
	public boolean aDesEnvahisseurs() {
		return !this.listeEnvahisseurs.isEmpty();
	}
	
}
