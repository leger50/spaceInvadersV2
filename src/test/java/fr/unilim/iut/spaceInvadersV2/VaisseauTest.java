package fr.unilim.iut.spaceInvadersV2;

import org.junit.Test;

import fr.unilim.iut.spaceInvadersV2.jeu.Dimension;
import fr.unilim.iut.spaceInvadersV2.jeu.Position;
import fr.unilim.iut.spaceInvadersV2.jeu.Vaisseau;
import fr.unilim.iut.spaceInvadersV2.utils.MissileException;

public class VaisseauTest {

	@Test(expected = MissileException.class)
	public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() throws Exception {
		
		Vaisseau vaisseau = new Vaisseau(new Dimension(5,2),new Position(5,9), 1);
		vaisseau.tirerUnMissile(new Dimension(7,2),1);
	}
}
