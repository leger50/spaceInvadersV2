package fr.unilim.iut.spaceInvadersV2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceInvadersV2.jeu.Constantes;
import fr.unilim.iut.spaceInvadersV2.jeu.Dimension;
import fr.unilim.iut.spaceInvadersV2.jeu.Envahisseur;
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
	public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(7,1), 1);
		
		assertEquals("" + 
		".......EE......\n" + 
		".......EE......\n" +
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
	public void test_UnNouveauEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {
		
		try {
			spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(15,9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(-1,9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(14,10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(14,-1), 1);
			fail("Position trop en haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
			
	}
	
	@Test
	public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
			
	}
	
	@Test
	public void test_EnvahisseurDeplacement_MemeDirection() {
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(7,1), 1);
		
		for (int i = 0; i < 3; i++) {
			spaceInvaders.deplacerEnvahisseurs();
		}
		
		assertEquals("" + 
		"..........EE...\n" + 
		"..........EE...\n" +
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
	public void test_PlacerPlusieursEnvahisseurSansChevauchement() {
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(2, 2), 1);
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(5, 2), 1);
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(8, 2), 1);
		
		assertEquals("" + 
				"...............\n" + 
				"..EE.EE.EE.....\n" +
				"..EE.EE.EE.....\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_PlacerPlusieursEnvahisseurAvecChevauchement() {
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(2, 2), 1);
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(5, 2), 1);
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(6, 2), 1);
		
		assertEquals("" + 
				"...............\n" + 
				"..EE.EE........\n" +
				"..EE.EE........\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_PlacerUneLigneDEnvahisseur_PositionCorrecte() {
		spaceInvaders.placerLigneEnvahisseurs(new Dimension(2, 2), 1, 1, 2);
		
		assertEquals("" + 
				"EE..EE..EE..EE.\n" + 
				"EE..EE..EE..EE.\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test(expected = DebordementEspaceJeuException.class)
	public void test_PlacerUneLigneDEnvahisseur_PositionIncorrecte() {
		spaceInvaders.placerLigneEnvahisseurs(new Dimension(2, 2), 1, 0, 2);
	}
	
	@Test
	public void test_PlacerUneLigneDEnvahisseur_DeplacementDansLaMemeDirection() {
		spaceInvaders.placerLigneEnvahisseurs(new Dimension(2, 2), 1, 1, 3);
		
		for (int i = 0; i < 2; i++) {
			spaceInvaders.deplacerEnvahisseurs();
		}		
		assertEquals("" + 
				"..EE...EE...EE.\n" + 
				"..EE...EE...EE.\n" +
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
	public void test_DisparitionMissileVaisseauEtEnvahisseurs_SiCollision() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(5, 9), 1);
		spaceInvaders.placerLigneEnvahisseurs(new Dimension(2, 2), 1, 1, 3);
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1);
		for (int i = 0; i < 5; i++) {
			spaceInvaders.deplacerMissilesDuVaisseau();
			spaceInvaders.verifierCollisionsMissileEtEnvahisseur();
		}

		assertEquals("" + 
				"EE........EE...\n" + 
				"EE........EE...\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".....VVV.......\n" + 
				".....VVV.......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_ScoreA0_DebutPartie() {
		assertEquals(0, this.spaceInvaders.obtenirScore());
	}
	
	@Test
	public void test_ScoreAugmente_SiEnvahisseurTouche() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(5, 9), 1);
		spaceInvaders.placerLigneEnvahisseurs(new Dimension(2, 2), 1, 1, 3);
		
		spaceInvaders.tirerUnMissileDepuisVaisseau(new Dimension(1, 2), 1);
		for (int i = 0; i < 5; i++) {
			spaceInvaders.deplacerMissilesDuVaisseau();
			spaceInvaders.verifierCollisionsMissileEtEnvahisseur();
		}
		
		assertEquals(Constantes.POINTS_PAR_ENVAHISSEUR, this.spaceInvaders.obtenirScore());
	}
	
	@Test
    public void test_MissileBienTireDepuisEnvahisseur_EnvahisseurLongueurImpaireMissileLongueurImpaire() {
	  
	  spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 1), 1);
	  
	  Envahisseur envahisseur = spaceInvaders.recupererListeEnvahisseurs().get(0);
	  spaceInvaders.tirerUnMissileDepuisUnEnvahisseur(envahisseur, new Dimension(1, 2), 1);

      assertEquals("" + 
      ".......EEE.....\n" + 
      ".......EEE.....\n" +
      "........M......\n" + 
      "........M......\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
   }
	
   @Test
   public void test_MissileAvanceAutomatiquement_ApresTirDepuisLEnvahisseur() {

	  spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 1), 1);
		  
	  Envahisseur envahisseur = spaceInvaders.recupererListeEnvahisseurs().get(0);
	  spaceInvaders.tirerUnMissileDepuisUnEnvahisseur(envahisseur, new Dimension(1, 2), 1);
	  
	  spaceInvaders.deplacerMissilesDesEnvahisseurs();

      assertEquals("" + 
      ".......EEE.....\n" + 
      ".......EEE.....\n" +
      "...............\n" + 
      "........M......\n" + 
      "........M......\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
   }
	
	@Test
    public void test_MissileEnvahisseurDisparait_QuandIlCommenceASortirDeEspaceJeu() {

	   spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 1), 1);
		  
	   Envahisseur envahisseur = spaceInvaders.recupererListeEnvahisseurs().get(0);
	   spaceInvaders.tirerUnMissileDepuisUnEnvahisseur(envahisseur, new Dimension(1, 2), 1);
	  	   
	   for (int i = 0; i < 7; i++) {
		   spaceInvaders.deplacerMissilesDesEnvahisseurs();
	   }
	   
	   
       assertEquals("" +
       ".......EEE.....\n" + 
       ".......EEE.....\n" +
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
	public void test_PartieTerminee_SiMissileEnvahisseurToucheVaisseau() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(5, 9), 1);
		spaceInvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(6, 1), 1);
		
		Envahisseur envahisseur = spaceInvaders.recupererListeEnvahisseurs().get(0);
		spaceInvaders.tirerUnMissileDepuisUnEnvahisseur(envahisseur, new Dimension(1, 2), 1);
		
		for (int i = 0; i < 5; i++) {
			spaceInvaders.deplacerMissilesDesEnvahisseurs();
		}

		assertTrue(spaceInvaders.etreFini());
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
	public void test_placerUneHordeEnvahisseur() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 2);
		
		assertEquals("" + 
				"EE.EE.EE.EE.EE.\n" + 
				"EE.EE.EE.EE.EE.\n" +
				"...............\n" + 
				"EE.EE.EE.EE.EE.\n" + 
				"EE.EE.EE.EE.EE.\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test(expected = HorsEspaceJeuException.class)
	public void test_placerUneHordeEnvahisseur_PasAssezDePlace() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 4);
	}
	
	@Test
	public void test_PlacerUneHordeDEnvahisseur_DeplacementDansLaMemeDirection() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 3, 1, 2);
		
		for (int i = 0; i < 2; i++) {
			spaceInvaders.deplacerEnvahisseurs();
		}		
		assertEquals("" + 
				"..EE...EE...EE.\n" + 
				"..EE...EE...EE.\n" +
				"...............\n" + 
				"..EE...EE...EE.\n" + 
				"..EE...EE...EE.\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_PlacerUneHordeDEnvahisseur_DeplacementChangementDirection_VersLaGaucheEtVersLeBas() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 1), 1, 3, 1, 2);
		
		for (int i = 0; i < 6; i++) {
			spaceInvaders.deplacerEnvahisseurs();
		}		
		assertEquals("" + 
				"...............\n" + 
				".EE...EE...EE..\n" +
				"...............\n" + 
				".EE...EE...EE..\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_PlacerUneHordeDEnvahisseur_DeplacementChangementDirection_VersLaDroiteEtVersLeBas() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 1), 1, 3, 1, 2);
		
		for (int i = 0; i < 9; i++) {
			spaceInvaders.deplacerEnvahisseurs();
		}		
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				".EE...EE...EE..\n" + 
				"...............\n" + 
				".EE...EE...EE..\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	
}
