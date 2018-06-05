package fr.unilim.iut.spaceInvadersV2;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceInvadersV2.jeu.Dimension;
import fr.unilim.iut.spaceInvadersV2.jeu.SpaceInvaders;
import fr.unilim.iut.spaceInvadersV2.utils.DebordementEspaceJeuException;

public class HordeTest {

	private SpaceInvaders spaceInvaders;
	
	@Before
    public void initialisation() {
	    this.spaceInvaders = new SpaceInvaders(15, 10);
    }
	
	@Test
	public void test_placerUneHordeEnvahisseur() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 2);
		
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
	
	@Test(expected = DebordementEspaceJeuException.class)
	public void test_placerUneHordeEnvahisseur_PasAssezDePlaceEnHauteur() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 4);
	}
	
	@Test(expected = DebordementEspaceJeuException.class)
	public void test_placerUneHordeEnvahisseur_PasAssezDePlaceEnLongueur() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 6, 2);
	}
	
	@Test
	public void test_PlacerUneHordeDEnvahisseur_DeplacementDansLaMemeDirection() {
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 3, 1, 3, 2);
		
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
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 1), 1, 3, 1, 3, 2);
		
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
		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 1), 1, 3, 1, 3, 2);
		
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
	
	@Test
    public void test_MissileBienTireDepuisLesEnvahisseurs() {

	  spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 2);
	  spaceInvaders.tirerUnMissileDepuisLesEnvahisseurs(new Dimension(2, 2), 1, 100);
	  
	  assertEquals(1, spaceInvaders.recupererListeMissilesDesEnvahisseurs().size());
   }
	
   @Test
   public void test_MissileAvanceAutomatiquement_ApresTirDepuisLEnvahisseur() {

	  int ordoneeOrigineMissile;
	  int ordoneeDeplacementMissile;
	  
	  spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 2);
	  spaceInvaders.tirerUnMissileDepuisLesEnvahisseurs(new Dimension(2, 2), 1, 100);
	  
	  ordoneeOrigineMissile = spaceInvaders.recupererListeMissilesDesEnvahisseurs().get(0).ordonneeLaPlusBasse();
	  
	  spaceInvaders.deplacerMissilesDesEnvahisseurs();

	  ordoneeDeplacementMissile = spaceInvaders.recupererListeMissilesDesEnvahisseurs().get(0).ordonneeLaPlusBasse();
	  
	  assertEquals(ordoneeOrigineMissile+1, ordoneeDeplacementMissile);
   }
	
	@Test
    public void test_MissileEnvahisseurDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		spaceInvaders.placerHordeEnvahisseurs(new Dimension(2, 2), 1, 1, 1, 5, 2);
		spaceInvaders.tirerUnMissileDepuisLesEnvahisseurs(new Dimension(2, 2), 1, 100);
	  	   
	   for (int i = 0; i < 7; i++) {
		   spaceInvaders.deplacerMissilesDesEnvahisseurs();
	   }
	   
	   
       assertEquals(0, spaceInvaders.recupererListeMissilesDesEnvahisseurs().size());
   }
}
