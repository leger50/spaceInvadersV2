package fr.unilim.iut.spaceInvadersV2;

public class SpaceInvaders {

	private static final char MARQUE_FIN_LIGNE = '\n';

	private static final char MARQUE_VIDE = '.';

	private static final char MARQUE_VAISSEAU = 'V';
	
	private int longueur;
    private int hauteur;
    
    Vaisseau vaisseau;

    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
   }
    
    public void positionnerUnNouveauVaisseau(int x, int y) {
        this.vaisseau = new Vaisseau(x,y);
	}
    
    public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
        
        for (int y = 0; y < this.hauteur; y++) {
            for (int x = 0; x < this.longueur; x++) {
            	espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
            }
           
            espaceDeJeu.append(MARQUE_FIN_LIGNE);
        }
        
        return espaceDeJeu.toString();
	}
    
    @Override
    public String toString() {
    	return recupererEspaceJeuDansChaineASCII();
    }

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		
		if(this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
			marque = MARQUE_VAISSEAU;
		}else {
			marque = MARQUE_VIDE;
		}
		
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && this.vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return this.vaisseau != null;
	}
}
