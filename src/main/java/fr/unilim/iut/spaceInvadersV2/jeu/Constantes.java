package fr.unilim.iut.spaceInvadersV2.jeu;

public class Constantes {

	public static final int ESPACEJEU_LONGUEUR = 500;
	public static final int ESPACEJEU_HAUTEUR = 200;
	public static final int ESPACESCORE_HAUTEUR = 50;

	public static final int VAISSEAU_LONGUEUR = 50;
	public static final int VAISSEAU_HAUTEUR = 30;
	public static final int VAISSEAU_VITESSE = 15;
	
	public static final int MISSILE_VAISSEAU_LONGUEUR = 4;
	public static final int MISSILE_VAISSEAU_HAUTEUR = 10;
	public static final int MISSILE_VAISSEAU_VITESSE = 10;
	
	public static final int MISSILE_ENVAHISSEUR_LONGUEUR = 4;
	public static final int MISSILE_ENVAHISSEUR_HAUTEUR = 10;
	public static final int MISSILE_ENVAHISSEUR_VITESSE = 10;
	
	public static final int ENVAHISSEUR_LONGUEUR = 20;
	public static final int ENVAHISSEUR_HAUTEUR = 20;
	public static final int ENVAHISSEUR_VITESSE = 4;
	public static final int ENVAHISSEUR_ESPACE_HORIZONTALE = 10;
	
	public static final int CHANCE_TIR_ENVAHISSEUR = 20;
	
	public static final int POINTS_PAR_ENVAHISSEUR = 10;
	
	public static final int SCORE_AFFICHAGE_X = 0;
	public static final int SCORE_AFFICHAGE_Y = ESPACEJEU_HAUTEUR + (ESPACESCORE_HAUTEUR /2);
	
	public static final char MARQUE_FIN_LIGNE = '\n';
	public static final char MARQUE_VIDE = '.';
	public static final char MARQUE_VAISSEAU = 'V';
	public static final char MARQUE_MISSILE = 'M';
	public static final char MARQUE_ENVAHISSEUR = 'E';

}
