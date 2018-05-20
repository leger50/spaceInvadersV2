package fr.unilim.iut.spaceInvadersV2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.unilim.iut.spaceInvadersV2.jeu.Collision;
import fr.unilim.iut.spaceInvadersV2.jeu.Dimension;
import fr.unilim.iut.spaceInvadersV2.jeu.Envahisseur;
import fr.unilim.iut.spaceInvadersV2.jeu.Missile;
import fr.unilim.iut.spaceInvadersV2.jeu.Position;
import fr.unilim.iut.spaceInvadersV2.jeu.Vaisseau;

public class CollisionTest {
	
	@Test
	public void test_Collision_MissileEtEnvahisseur_MemePosition() {
		Missile missile = new Missile(new Dimension(1, 1), new Position(4, 3), 1);
		Envahisseur envahisseur = new Envahisseur(new Dimension(1, 1), new Position(4, 3), 1);
		
		assertTrue(Collision.detecterCollision(missile, envahisseur));
	}
	
	@Test
	public void test_PasDeCollision_MissileEtEnvahisseur() {
		Missile missile = new Missile(new Dimension(1, 1), new Position(4, 3), 1);
		Envahisseur envahisseur = new Envahisseur(new Dimension(1, 1), new Position(4, 4), 1);
		
		assertFalse(Collision.detecterCollision(missile, envahisseur));
	}
	
	@Test
	public void test_Collision_MissileEtEnvahisseur() {
		Missile missile = new Missile(new Dimension(1, 2), new Position(4, 3), 1);
		Envahisseur envahisseur = new Envahisseur(new Dimension(4, 4), new Position(4, 2), 1);
		
		assertTrue(Collision.detecterCollision(missile, envahisseur));
	}
	
	@Test
	public void test_Collision_EnvahisseurEtMissile() {
		Missile missile = new Missile(new Dimension(1, 2), new Position(4, 3), 1);
		Envahisseur envahisseur = new Envahisseur(new Dimension(4, 4), new Position(4, 2), 1);
		
		assertTrue(Collision.detecterCollision(envahisseur, missile));
	}
	
	@Test
	public void test_PasDeCollision_EnvahisseurEtMissile() {
		Missile missile = new Missile(new Dimension(1, 2), new Position(4, 4), 1);
		Envahisseur envahisseur = new Envahisseur(new Dimension(4, 4), new Position(4, 2), 1);
		
		assertFalse(Collision.detecterCollision(envahisseur, missile));
	}
	
	@Test
	public void test_CollisionNord_MissileEtVaisseau() {
		Missile missile = new Missile(new Dimension(1, 3), new Position(4, 8), 1);
		Vaisseau vaisseau = new Vaisseau(new Dimension(3,2), new Position(3, 9), 1);
		
		assertTrue(Collision.detecterCollision(missile, vaisseau));
	}
	
	@Test
	public void test_PasDeCollisionNord_MissileEtVaisseau() {
		Missile missile = new Missile(new Dimension(1, 3), new Position(4, 7), 1);
		Vaisseau vaisseau = new Vaisseau(new Dimension(3,2), new Position(3, 9), 1);
		
		assertFalse(Collision.detecterCollision(missile, vaisseau));
	}
	
	@Test
	public void test_CollisionOuest_MissileEtVaisseau() {
		Missile missile = new Missile(new Dimension(1, 1), new Position(2, 8), 1);
		Vaisseau vaisseau = new Vaisseau(new Dimension(3,3), new Position(0, 9), 1);
		
		assertTrue(Collision.detecterCollision(missile, vaisseau));
	}
	
	@Test
	public void test_CollisionEst_MissileEtVaisseau() {
		Missile missile = new Missile(new Dimension(1, 1), new Position(7, 8), 1);
		Vaisseau vaisseau = new Vaisseau(new Dimension(3,3), new Position(7, 9), 1);
		
		assertTrue(Collision.detecterCollision(missile, vaisseau));
	}
	
	@Test
	public void test_PasCollisionEst_MissileEtVaisseau() {
		Missile missile = new Missile(new Dimension(1, 1), new Position(6, 8), 1);
		Vaisseau vaisseau = new Vaisseau(new Dimension(3,3), new Position(7, 9), 1);
		
		assertFalse(Collision.detecterCollision(missile, vaisseau));
	}
}
