package Main;

import Entite.*;

public class Collision {
	Jeu jeu;
	
	public Collision(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public Boolean verifierCollision(Entite joueur) {
		
		//Determination des coordonnes de chaque face du joueur
		int faceHaut = joueur.getY() + 40;
		int faceBas = joueur.getY() + jeu.tailleCaseReelle;
		int faceGauche = joueur.getX() + 17;
		int faceDroit = joueur.getX() + 28 + 17;
		
		//Determination des colonnes et les lignes
		int faceHautLigne = faceHaut/jeu.tailleCaseReelle;
		int faceBasLigne = faceBas/jeu.tailleCaseReelle;
		int faceGaucheColonne = faceGauche/jeu.tailleCaseReelle;
		int faceDroitColonne = faceDroit/jeu.tailleCaseReelle;
		
		
		int coin1, coin2;
		if (joueur.getDirection() == Direction.HAUT) {
			faceHautLigne = (faceHaut - joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.map.coordonneeMap[faceGaucheColonne][faceHautLigne];
			coin2 = jeu.map.coordonneeMap[faceDroitColonne][faceHautLigne];
			if (jeu.map.map[coin1].collision == true || jeu.map.map[coin2].collision == true) {
				return true;
			}
		}
		else if(joueur.getDirection() == Direction.BAS) {
			faceBasLigne = (faceBas + joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.map.coordonneeMap[faceGaucheColonne][faceBasLigne];
			coin2 = jeu.map.coordonneeMap[faceDroitColonne][faceBasLigne];
			if (jeu.map.map[coin1].collision == true || jeu.map.map[coin2].collision == true) {
				return true;
			}
		}
		else if (joueur.getDirection() == Direction.DROITE) {
			faceDroitColonne = (faceDroit + joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.map.coordonneeMap[faceDroitColonne][faceHautLigne];
			coin2 = jeu.map.coordonneeMap[faceDroitColonne][faceBasLigne];
			if (jeu.map.map[coin1].collision == true || jeu.map.map[coin2].collision == true) {
				return true;
			}
		}
		else if (joueur.getDirection() == Direction.GAUCHE) {
			faceGaucheColonne = (faceGauche - joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.map.coordonneeMap[faceGaucheColonne][faceHautLigne];
			coin2 = jeu.map.coordonneeMap[faceGaucheColonne][faceBasLigne];
			if (jeu.map.map[coin1].collision == true || jeu.map.map[coin2].collision == true) {
				return true;
			}
		} 
		return false;
	}
}