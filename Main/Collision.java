package Main;

import Entite.*;
import java.awt.Point;

public class Collision {
	Jeu jeu;
	public boolean lancer_salle2 = false;
	public boolean lancer_ModeleMap = false;
	public boolean lancer_salle3 = false;
	public boolean lancer_ModeleMap2 = false;



	public Collision(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public Boolean verifierCollision(Entite joueur) {
		
		//Determination des coordonnes de chaque face du joueur
		int faceHaut = joueur.getY() + 40;
		int faceBas = joueur.getY() + Jeu.tailleCaseReelle;
		int faceGauche = joueur.getX() + 17;
		int faceDroit = joueur.getX() + 28 + 17;
		
		//Determination des colonnes et les lignes
		int faceHautLigne = faceHaut/Jeu.tailleCaseReelle;
		int faceBasLigne = faceBas/Jeu.tailleCaseReelle;
		int faceGaucheColonne = faceGauche/Jeu.tailleCaseReelle;
		int faceDroitColonne = faceDroit/Jeu.tailleCaseReelle;
		
		

		
		
		int coin1, coin2;
		if (joueur.getDirection() == Direction.HAUT) {
			faceHautLigne = (faceHaut - joueur.getVitesse())/Jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceHautLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceHautLigne];
			if(jeu.salle.getSalle().getMap()[coin1].collision_portec == true || jeu.salle.getSalle().getMap()[coin2].collision_portec == true) {
				lancer_salle2 = false;
				lancer_ModeleMap = false;
				lancer_ModeleMap2 = false;
				lancer_salle3 = true;
			
				
			}
			
			if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
		}
		else if(joueur.getDirection() == Direction.BAS) {
			faceBasLigne = (faceBas + joueur.getVitesse())/Jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceBasLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceBasLigne];
			if(jeu.salle.getSalle().getMap()[coin1].collision_porte == true || jeu.salle.getSalle().getMap()[coin2].collision_porte == true) {
				lancer_ModeleMap = false;
				lancer_salle3 = false;
				lancer_ModeleMap2 = false;
				lancer_salle2 = true;
				
				
			}
			
			
		if(jeu.salle.getSalle().getMap()[coin1].collision_porteb == true || jeu.salle.getSalle().getMap()[coin2].collision_porteb == true) {
			lancer_salle2 = false;
			lancer_salle3 = false;
			lancer_ModeleMap2 = false;
			lancer_ModeleMap = true;
	}
		if(jeu.salle.getSalle().getMap()[coin1].collision_ported == true || jeu.salle.getSalle().getMap()[coin2].collision_ported == true) {
			lancer_salle2 = false;
			lancer_ModeleMap = false;
			lancer_salle3 = false;
			lancer_ModeleMap2 = true;
	}

			if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
			
		}
		else if (joueur.getDirection() == Direction.DROITE) {
			faceDroitColonne = (faceDroit + joueur.getVitesse())/Jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceHautLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceBasLigne];
			if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
		}
		else if (joueur.getDirection() == Direction.GAUCHE) {
			faceGaucheColonne = (faceGauche - joueur.getVitesse())/Jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceHautLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceBasLigne];
			if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
		} 
		return false;
	}

	private static boolean PointDedans(Entite e, Point p) {
		/*
		if ((p.getX() > e.getX() - e.getHitbox().getHitBox_FaceGauche()) 
		&& (p.getX() < e.getX() + e.getHitbox().getHitBox_FaceDroite()) 
		&& (p.getY() < e.getY() + e.getHitbox().getHitBox_FaceHaut()) 
		&& (p.getY() > e.getY() - e.getHitbox().getHitBox_FaceBas())) 
		*/
		
		if ((p.getX() > e.getHitbox().getPointGauche().getX()) 
		&& (p.getX() < e.getHitbox().getPointDroit().getX()) 
		&& (p.getY() < e.getHitbox().getPointBas().getY()) 
		&& (p.getY() > e.getHitbox().getPointHaut().getY())) 

		{
			return true;
		}
		return false;
	}

	public static boolean collisionJoueur(Joueur joueur, Entite e) {
		/*
		Point droit = new Point(e.getX() + e.getHitbox().getHitBox_FaceDroite(), e.getY());
		Point gauche = new Point(e.getX() - e.getHitbox().getHitBox_FaceGauche(), e.getY());
		Point bas = new Point(e.getX(), e.getY() - e.getHitbox().getHitBox_FaceBas());
		Point haut = new Point(e.getX(), e.getY() + e.getHitbox().getHitBox_FaceHaut());
		*/

		if (PointDedans(joueur, e.getHitbox().getPointDroit()) || PointDedans(joueur, e.getHitbox().getPointGauche()) || PointDedans(joueur, e.getHitbox().getPointBas()) || PointDedans(joueur, e.getHitbox().getPointHaut()) ) {
			return true;
		}
		return false;
		
	}
}