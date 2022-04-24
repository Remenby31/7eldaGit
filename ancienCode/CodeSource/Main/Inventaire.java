package Main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entite.Objet;

public class Inventaire {
		
		public Jeu jeu;
		public int nbColonne, nbLigne;
		public int tailleInv = nbColonne * nbLigne;
		public int hauteur, largeur;
		public Objet[] listeObjet;
		public Objet[] tousLesObjets;
		
		
		public Inventaire(int nbCol, int nbLigne, int largeur, int hauteur) {
			this.hauteur = hauteur;
			this.largeur = largeur;
			this.nbColonne = nbCol;
			this.nbLigne = nbLigne;
			this.listeObjet = new Objet[12];
			this.tousLesObjets = new Objet[30];
			this.getTexture();
			this.initInventaire();
		}
		
		public void initInventaire() {
			
			BufferedImage image = this.tousLesObjets[0].image;
			for (int k = 0; k < 12; k++) {
				this.listeObjet[k] = new Objet();
				this.listeObjet[k].dansInv = true;
				this.listeObjet[k].image = image;
				this.listeObjet[k].ind = 0;
			}
		}
		
		public void getTexture() {
			try {
				this.tousLesObjets[1] = new Objet();
				this.tousLesObjets[1].dansInv = false;
				this.tousLesObjets[1].ind = 1;
				this.tousLesObjets[1].image = ImageIO.read(getClass().getResourceAsStream("/Ressources/epee.png"));
				
				this.tousLesObjets[0] = new Objet();
				this.tousLesObjets[0].dansInv = false;
				this.tousLesObjets[0].ind = 0;
				this.tousLesObjets[0].image = ImageIO.read(getClass().getResourceAsStream("/Ressources/vide.png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void ajouterObjet(Objet objet) {
			try {
				int nbObjet = this.getNbObjetInv();
				if (nbObjet < 12) {
					this.tousLesObjets[objet.ind].dansInv = true;
					
					this.listeObjet[nbObjet].dansInv = true;
					this.listeObjet[nbObjet].image = objet.image;
					this.listeObjet[nbObjet].ind = objet.ind;
					
				} else {
					throw new InventairePleinException();
				}
			} catch (InventairePleinException e) {
				System.out.println("Inventaire plein");
			}
			
		}
		
		public void retirerObjet(Objet objet) {
			try {
				int nbObjet = this.getNbObjetInv();
				if (nbObjet < 1) {
					this.tousLesObjets[objet.ind].dansInv = false;
					
					this.listeObjet[nbObjet].dansInv = true;
					this.listeObjet[nbObjet].image = objet.image;
					this.listeObjet[nbObjet].ind = objet.ind;
					
				}
			}
			 finally {

			 }
		}
		
		public int getNbObjetInv() {
			int k = 0;
			int compt = 0;
			while (k < 12 && this.listeObjet[k].ind != 0) {
				compt++;
				k++;
			}
			return compt;
		}
}
