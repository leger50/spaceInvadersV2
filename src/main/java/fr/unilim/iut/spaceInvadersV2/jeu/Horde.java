package fr.unilim.iut.spaceInvadersV2.jeu;

import java.util.ArrayList;
import java.util.List;

public class Horde {

	private int nombreLignes;
	private List<Envahisseur> listeEnvahisseurs;
	
	public Horde() {
		this.listeEnvahisseurs = new ArrayList<>();
		this.nombreLignes = 3;
	}
}
