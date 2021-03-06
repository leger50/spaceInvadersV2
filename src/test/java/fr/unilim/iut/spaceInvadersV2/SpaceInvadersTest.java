package fr.unilim.iut.spaceInvadersV2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceInvadersV2.jeu.Constantes;
import fr.unilim.iut.spaceInvadersV2.jeu.Dimension;
import fr.unilim.iut.spaceInvadersV2.jeu.Position;
import fr.unilim.iut.spaceInvadersV2.jeu.SpaceInvaders;
import fr.unilim.iut.spaceInvadersV2.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceInvadersV2.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceInvadersV2.utils.MissileException;

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
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9), 1);
		
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
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1), 1);
			fail("Position trop en haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
			
	}
	
	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 1);
		
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
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
			
	}
	
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
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
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

	       spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
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
	       "..........VVV..\n" + 
	       "..........VVV..\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	  }
	
	@Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

       spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
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
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
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
	    "....VVV........\n" + 
	    "....VVV........\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }
	
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {
		
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
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
	
	@Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
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
	
	@Test
    public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

	  spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
	  spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(3,2),2);

      assertEquals("" + 
      "...............\n" + 
      "...............\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      ".......MMM.....\n" + 
      ".......MMM.....\n" + 
      ".....VVVVVVV...\n" + 
      ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
   }
	
	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
	   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	   spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(7,9),1);
	}
	
	@Test
	public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

	   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
	   spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(3,2),2);

	   spaceInvaders.deplacerMissilesDuVaisseau();
	   
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       ".......MMM.....\n" + 
       ".......MMM.....\n" + 
       "...............\n" + 
       "...............\n" + 
       ".....VVVVVVV...\n" + 
       ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
   }
	
	@Test
    public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

	   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	   
	   spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(3,2),1);
	   for (int i = 1; i <=6 ; i++) {
		   spaceInvaders.deplacerMissilesDuVaisseau();
	   }
	   
	   spaceInvaders.deplacerMissilesDuVaisseau();
	   
       assertEquals("" +
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" +
       "...............\n" +
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       ".....VVVVVVV...\n" + 
       ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
   }
	
	@Test
	public void test_TirerPlusieursMissilesSansChevauchement() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(5, 9), 2);
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 4);
		spaceInvaders.deplacerMissilesDuVaisseau();
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 4);
		
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"......M........\n" + 
				"......M........\n" + 
				"...............\n" + 
				"...............\n" + 
				"......M........\n" + 
				"......M........\n" + 
				".....VVV.......\n" + 
				".....VVV.......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_TirerPlusieursMissilesAvecChevauchement() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(5, 9), 2);
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1);
		spaceInvaders.deplacerMissilesDuVaisseau();
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1);
		
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"......M........\n" + 
				"......M........\n" + 
				"...............\n" + 
				".....VVV.......\n" + 
				".....VVV.......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_ScoreA0_DebutPartie() {
		assertEquals(0, this.spaceInvaders.obtenirScore());
	}
	
	@Test
    public void test_LimiteDeTirDepuisVaisseau() {
	  int limiteMissilesVaisseau = Constantes.MISSILE_VAISSEAU_LIMITE;
	  
	  spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(5,9), 2);
	  spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1,2),2);
	  
	  for (int i = 0; i < limiteMissilesVaisseau +1; i++) {
		  spaceInvaders.deplacerVaisseauVersLaDroite();
		  
		  if(spaceInvaders.autoriserTirDepuisVaisseau()) {
			  spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1,2),2);
		  }
	  }
	  
      assertEquals(limiteMissilesVaisseau, this.spaceInvaders.recupererListeMissilesDuVaisseau().size());
    }
	
	@Test
	public void test_PartieTerminee_SiMissileEnvahisseurToucheVaisseau() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(15, 2), new Position(0,8), 1);
		
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 1);
		spaceInvaders.tirerUnMissileDepuisLesEnvahisseurs(new Dimension(2, 2), 1, 100);
		
		for (int i = 0; i < 5; i++) {
			spaceInvaders.deplacerMissilesDesEnvahisseurs();
		}
		
		assertTrue(spaceInvaders.etreFini());
	}
	
	@Test
	public void test_ScoreAugmente_SiEnvahisseurTouche() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(5, 9), 1);
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 2);
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1);
		for (int i = 0; i < 5; i++) {
			spaceInvaders.deplacerMissilesDuVaisseau();
			spaceInvaders.verifierCollisionsMissileEtEnvahisseur();
		}
		
		assertEquals(Constantes.POINTS_PAR_ENVAHISSEUR, this.spaceInvaders.obtenirScore());
	}
}
