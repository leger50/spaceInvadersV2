package fr.unilim.iut.spaceInvadersV2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceInvadersV2.jeu.Dimension;
import fr.unilim.iut.spaceInvadersV2.jeu.Position;
import fr.unilim.iut.spaceInvadersV2.jeu.SpaceInvaders;
import fr.unilim.iut.spaceInvadersV2.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceInvadersV2.utils.HorsEspaceJeuException;

public class SpaceInvadersTest {
	
	private SpaceInvaders spaceInvaders;
	
	@Before
    public void initialisation() {
	    this.spaceInvaders = new SpaceInvaders(15, 10);
    }
	
	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
	    
	    assertEquals("" + 
	    "...............\n" + 
	    "...............\n" +
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9));
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......V.......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9));
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9));
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10));
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1));
			fail("Position trop en haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
			
	}
	
	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9));
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......VVV.....\n" + 
		".......VVV.....\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9));
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1));
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
			
	}
	
	@Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9));
		spaceInvaders.deplacerVaisseauVersLaDroite();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"........VVV....\n" + 
		"........VVV....\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9));
		spaceInvaders.deplacerVaisseauVersLaDroite();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"............VVV\n" + 
		"............VVV\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9));
		spaceInvaders.deplacerVaisseauVersLaGauche();
	
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"......VVV......\n" + 
		"......VVV......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }
	
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9));
		spaceInvaders.deplacerVaisseauVersLaGauche();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"VVV............\n" + 
		"VVV............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
}
