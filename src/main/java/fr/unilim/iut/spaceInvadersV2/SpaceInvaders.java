package fr.unilim.iut.spaceInvadersV2;

public class SpaceInvaders {

	private int longueur;
    private int hauteur;

    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
   }
    
    @Override
    public String toString() {
    	StringBuilder espaceDeJeu = new StringBuilder();
        
        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.longueur; j++) {
            	espaceDeJeu.append(".");
            }
           
            espaceDeJeu.append("\n");
        }
        
        return espaceDeJeu.toString();
    }
}
