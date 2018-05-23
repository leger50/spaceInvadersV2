package fr.unilim.iut.spaceInvadersV2.jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fr.unilim.iut.spaceInvadersV2.moteurJeu.Commande;
import fr.unilim.iut.spaceInvadersV2.moteurJeu.Jeu;
import fr.unilim.iut.spaceInvadersV2.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceInvadersV2.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceInvadersV2.utils.MissileException;

public class SpaceInvaders implements Jeu {

	private int longueur;
	private int hauteur;

	private Vaisseau vaisseau;
	
	private List<Missile> listeMissilesVaisseau;
	private List<Missile> listeMissilesEnvahisseurs;
	
	private List<Envahisseur> listeEnvahisseurs;
	
	private int score;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		
		this.listeMissilesVaisseau = new ArrayList<>();
		this.listeMissilesEnvahisseurs = new ArrayList<>();
		
		this.listeEnvahisseurs = new ArrayList<>();
		
		this.score = 0;
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!this.estDansEspaceJeu(x, y)) {
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");
		}

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!this.estDansEspaceJeu(x + longueurVaisseau - 1, y)) {
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		}
		if (!this.estDansEspaceJeu(x, y - hauteurVaisseau + 1)) {
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");
		}

		this.vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void deplacerVaisseauVersLaGauche() {
		if (this.vaisseau.abscisseLaPlusAGauche() > 0) {
			this.vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);

			if (!this.estDansEspaceJeu(this.vaisseau.abscisseLaPlusAGauche(), this.vaisseau.ordonneeLaPlusBasse())) {
				this.vaisseau.positionner(0, this.vaisseau.ordonneeLaPlusBasse());
			}
		}
	}

	public void deplacerVaisseauVersLaDroite() {

		if (this.vaisseau.abscisseLaPlusADroite() < (this.longueur - 1)) {
			this.vaisseau.deplacerHorizontalementVers(Direction.DROITE);

			if (!this.estDansEspaceJeu(this.vaisseau.abscisseLaPlusADroite(), this.vaisseau.ordonneeLaPlusBasse())) {
				this.vaisseau.positionner(this.longueur - this.vaisseau.longueur(), this.vaisseau.ordonneeLaPlusBasse());
			}
		}
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && this.vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return this.vaisseau != null;
	}

	public Vaisseau recupererLeVaisseau() {
		return this.vaisseau;
	}
	
	public boolean autoriserTirDepuisVaisseau() {
		return this.listeMissilesVaisseau.size() < Constantes.MISSILE_VAISSEAU_LIMITE;
	}

	public void tirerUnMissileDepuisVaisseau(Dimension dimension, int vitesse) {

		if ((this.vaisseau.hauteur() + dimension.hauteur()) > this.hauteur) {
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		}

		Missile missileTire = this.vaisseau.tirerUnMissile(dimension, vitesse);
		
		if(!this.aUnMissileDuVaisseauQuiOccupeLEspace(missileTire)) {
			this.listeMissilesVaisseau.add(missileTire);
		}
	}
	
	private boolean aUnMissileDuVaisseauQuiOccupeLEspace(Missile missileTire) {
		boolean missileDejaPresent = false;
		int i = 0;
		
		while(i<this.listeMissilesVaisseau.size() && !missileDejaPresent) {
			if(Collision.detecterCollision(missileTire, this.listeMissilesVaisseau.get(i))) {
				missileDejaPresent = true;
			}
			
			i++;
		}
		return missileDejaPresent;
	}
	
	public void deplacerMissilesDuVaisseau() {
		
		for(int i=0; i<this.listeMissilesVaisseau.size(); i++) {
			this.listeMissilesVaisseau.get(i).deplacerVerticalementVers(Direction.HAUT_ECRAN);
			
			if(!this.estDansEspaceJeu(this.listeMissilesVaisseau.get(i).abscisseLaPlusAGauche(), this.listeMissilesVaisseau.get(i).ordonneeLaPlusHaute())){
				this.listeMissilesVaisseau.remove(i);
			}
		}
		
	}

	public boolean aDesMissilesDuVaisseau() {
		return !this.listeMissilesVaisseau.isEmpty();
	}

	public List<Missile> recupererListeMissilesDuVaisseau() {
		return this.listeMissilesVaisseau;
	}
	
	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!this.estDansEspaceJeu(x, y)) {
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");
		}

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if (!this.estDansEspaceJeu(x + longueurEnvahisseur - 1, y)) {
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		}
		if (!this.estDansEspaceJeu(x, y - hauteurEnvahisseur + 1)) {
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");
		}

		Envahisseur envahisseurAPlacer = new Envahisseur(dimension, position, vitesse);
		
		if(!this.aUnEnvahisseurQuiOccupeLEspace(envahisseurAPlacer)) {
			this.listeEnvahisseurs.add(envahisseurAPlacer);
		}
		
	}
	
	public void placerLigneEnvahisseurs(Dimension dimensionEnvahisseur, int vitesseEnvahisseur, int ordonneePosition, int espaceEntreEnvahisseurs) {
		
		int abscisseEnvahisseur = dimensionEnvahisseur.longueur() + espaceEntreEnvahisseurs;
		
		for(int i=0; i<(this.longueur -dimensionEnvahisseur.longueur()); i+=abscisseEnvahisseur) {
			this.positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, new Position(i, ordonneePosition), vitesseEnvahisseur);
		}
	}
	
	private boolean aUnEnvahisseurQuiOccupeLEspace(Envahisseur envahisseurAPlacer) {
		boolean envahisseurDejaPresent = false;
		int i = 0;
		
		while(i<this.listeEnvahisseurs.size() && !envahisseurDejaPresent) {
			if(Collision.detecterCollision(envahisseurAPlacer, this.listeEnvahisseurs.get(i))) {
				envahisseurDejaPresent = true;
			}
			
			i++;
		}
		return envahisseurDejaPresent;
	}
	
	public void deplacerEnvahisseur() {
		boolean horsZone = false;
		
		for (Envahisseur envahisseur : this.listeEnvahisseurs) {
			
			envahisseur.deplacerHorizontalementVers(envahisseur.direction());
			
			if(envahisseur.abscisseLaPlusADroite() > (this.longueur -1) || envahisseur.abscisseLaPlusAGauche() < 0) {
				horsZone = true;
			}
		}
		
		if(horsZone) {
			this.changerDirectionEnvahisseur();
		}
	}

	private void changerDirectionEnvahisseur() {
		
		for (Envahisseur envahisseur : this.listeEnvahisseurs) {
			envahisseur.modifierDirection();
			envahisseur.deplacerHorizontalementVers(envahisseur.direction());
		}
	}
	
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		
		for (Envahisseur envahisseur : this.listeEnvahisseurs) {
			if(envahisseur.occupeLaPosition(x, y)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean aDesEnvahisseurs() {
		return !this.listeEnvahisseurs.isEmpty();
	}

	public List<Envahisseur> recupererListeEnvahisseurs() {
		return this.listeEnvahisseurs;
	}
	
	public boolean autoriserTirDepuisLesEnvahisseurs() {
		return this.listeMissilesEnvahisseurs.size() < Constantes.MISSILE_ENVAHISSEUR_LIMITE;
	}
	
	public void tirerUnMissileDepuisLesEnvahisseurs(Dimension dimension, int vitesse) {
		
		if(this.aDesEnvahisseurs() && this.lesEnvahisseursTirent()) {
			
			int choixEnvahisseur = choisirUnEnvahisseur();
			this.tirerUnMissileDepuisUnEnvahisseur(this.listeEnvahisseurs.get(choixEnvahisseur), dimension, vitesse);
		}
	}
	
	private boolean lesEnvahisseursTirent() {
		Random nbAleatoire = new Random();
		
		return nbAleatoire.nextInt(100) < Constantes.CHANCE_TIR_ENVAHISSEUR;
	}

	private int choisirUnEnvahisseur() {
		Random nbAleatoire = new Random();
		return nbAleatoire.nextInt(this.listeEnvahisseurs.size());
	}
	
	public void tirerUnMissileDepuisUnEnvahisseur(Envahisseur envahisseur, Dimension dimension, int vitesse) {

		Missile missileTire = envahisseur.tirerUnMissile(dimension, vitesse);
		
		if(!this.aUnMissileDesEnvahisseursQuiOccupeLEspace(missileTire)) {
			this.listeMissilesEnvahisseurs.add(missileTire);
		}
	}
	
	private boolean aUnMissileDesEnvahisseursQuiOccupeLEspace(Missile missileTire) {
		boolean missileDejaPresent = false;
		int i = 0;
		
		while(i<this.listeMissilesEnvahisseurs.size() && !missileDejaPresent) {
			if(Collision.detecterCollision(missileTire, this.listeMissilesEnvahisseurs.get(i))) {
				missileDejaPresent = true;
			}
			
			i++;
		}
		return missileDejaPresent;
	}
	
	public void deplacerMissilesDesEnvahisseurs() {
		
		for(int i=0; i<this.listeMissilesEnvahisseurs.size(); i++) {
			this.listeMissilesEnvahisseurs.get(i).deplacerVerticalementVers(Direction.BAS_ECRAN);
			
			if(!this.estDansEspaceJeu(this.listeMissilesEnvahisseurs.get(i).abscisseLaPlusAGauche(), this.listeMissilesEnvahisseurs.get(i).ordonneeLaPlusBasse())){
				this.listeMissilesEnvahisseurs.remove(i);
			}
		}
		
	}

	public boolean aDesMissilesDesEnvahisseurs() {
		return !this.listeMissilesEnvahisseurs.isEmpty();
	}

	public List<Missile> recupererListeMissilesDesEnvahisseurs() {
		return this.listeMissilesEnvahisseurs;
	}
	
	public void augmenterScore(int pointsGagnes) {
		if(pointsGagnes <= 0) {
			throw new IllegalArgumentException("Points attendus doivent être supérieurs à 0");
		}
		
		this.score += pointsGagnes;
	}
	
	public int obtenirScore() {
		return this.score;
	}
	
	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur));
	}
	
	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		
		for (Missile missile : this.listeMissilesVaisseau) {
			if(missile.occupeLaPosition(x, y)) {
				return true;
			}
		}
		
		for (Missile missile : this.listeMissilesEnvahisseurs) {
			if(missile.occupeLaPosition(x, y)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean collisionsMissileEnvahisseurEtVaisseau() {
		
		for (Iterator<Missile> iteratorMissiles = this.listeMissilesEnvahisseurs.iterator(); iteratorMissiles.hasNext();) {
		    Missile missile = iteratorMissiles.next();
		    
		    if(Collision.detecterCollision(missile, this.vaisseau)) {
		    	return true;
		    }
		}
		
		return false;
	}
	
	public void verifierCollisionsMissileEtEnvahisseur() {
		
		for (Iterator<Missile> iteratorMissiles = this.listeMissilesVaisseau.iterator(); iteratorMissiles.hasNext();) {
		    Missile missile = iteratorMissiles.next();
		    
		    for (Iterator<Envahisseur> iteratorEnvahisseurs = this.listeEnvahisseurs.iterator(); iteratorEnvahisseurs.hasNext();) {
		    	Envahisseur envahisseur = iteratorEnvahisseurs.next();
		    	
		    	if(Collision.detecterCollision(missile, envahisseur)) {
					iteratorMissiles.remove();
					iteratorEnvahisseurs.remove();
					
					this.augmenterScore(Constantes.POINTS_PAR_ENVAHISSEUR);
				}
		    }
		}
	}

	public void initialiserJeu() {
		this.positionnerUnNouveauVaisseau(new Dimension(Constantes.VAISSEAU_LONGUEUR, Constantes.VAISSEAU_HAUTEUR),
				new Position(this.longueur / 2, this.hauteur - 1), Constantes.VAISSEAU_VITESSE);
		
		this.placerLigneEnvahisseurs(new Dimension(Constantes.ENVAHISSEUR_LONGUEUR, Constantes.ENVAHISSEUR_HAUTEUR), 
				Constantes.ENVAHISSEUR_VITESSE, Constantes.ENVAHISSEUR_HAUTEUR-1, Constantes.ENVAHISSEUR_ESPACE_HORIZONTALE);
	}

	private void deplacerVaisseau(Commande commandeUser) {
		if (commandeUser.gauche) {
			this.deplacerVaisseauVersLaGauche();

		} else if (commandeUser.droite) {
			this.deplacerVaisseauVersLaDroite();
		}
	}

	@Override
	public void evoluer(Commande commandeUser) {
		
		this.deplacerVaisseau(commandeUser);

		if (commandeUser.tir && this.autoriserTirDepuisVaisseau()) {
			this.tirerUnMissileDepuisVaisseau(
					new Dimension(Constantes.MISSILE_VAISSEAU_LONGUEUR, Constantes.MISSILE_VAISSEAU_HAUTEUR),
					Constantes.MISSILE_VAISSEAU_VITESSE);
			
		}
		
		if(this.aDesMissilesDuVaisseau()) {
			this.deplacerMissilesDuVaisseau();
		}
		
		if(this.aDesMissilesDesEnvahisseurs()) {
			this.deplacerMissilesDesEnvahisseurs();
		}
		
		if(this.aDesEnvahisseurs()) {
			this.deplacerEnvahisseur();
		}
		
		if(this.autoriserTirDepuisLesEnvahisseurs()) {
			this.tirerUnMissileDepuisLesEnvahisseurs(
					new Dimension(Constantes.MISSILE_ENVAHISSEUR_LONGUEUR, Constantes.MISSILE_ENVAHISSEUR_HAUTEUR), 
					Constantes.MISSILE_ENVAHISSEUR_VITESSE);
		}
		
		this.verifierCollisionsMissileEtEnvahisseur();

	}

	@Override
	public boolean etreFini() {
		return this.collisionsMissileEnvahisseurEtVaisseau();
	}
	
	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();

		for (int y = 0; y < this.hauteur; y++) {
			for (int x = 0; x < this.longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}

			espaceDeJeu.append(Constantes.MARQUE_FIN_LIGNE);
		}

		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;

		if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
			marque = Constantes.MARQUE_VAISSEAU;
		} else if (this.aUnMissileQuiOccupeLaPosition(x, y)) {
			marque = Constantes.MARQUE_MISSILE;
		} else if(this.aUnEnvahisseurQuiOccupeLaPosition(x, y)) {
			marque = Constantes.MARQUE_ENVAHISSEUR;
		} else {
			marque = Constantes.MARQUE_VIDE;
		}

		return marque;
	}
}
