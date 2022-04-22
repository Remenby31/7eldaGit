package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Entite.Joueur;

public class GUI {
	public Jeu jeu;
	Font a40;
    public Joueur joueur;
    public Inventaire inv;
	
	public GUI(Jeu jeu, Joueur joueur) {
		this.jeu = jeu;
		this.joueur = joueur;
		a40 = new Font("Arial", Font.ITALIC, 20);
		this.inv = joueur.inv;
	}
	
	public void afficher(Graphics2D g) {
		g.setFont(a40);
		g.setColor(Color.YELLOW);
		g.drawString("NICOBAHARAYE", joueur.getX()  - 37, joueur.getY());
	}
	
	
	public void afficherInventaire(Graphics2D g, Inventaire inv) {
		
		int posX = 10;
		int posY = 10;
		int hauteur = jeu.tailleCaseReelle * 5 + 15;
		int largeur = jeu.tailleCaseReelle * 4;
		afficherFenetre(posX, posY, hauteur, largeur, g);
		
		int posXCase = 27;
		int posYCase = 25;
		int compt = 0;
		
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < 3; i++) {
				afficherCase(posXCase, posYCase, jeu.tailleCaseReelle, jeu.tailleCaseReelle, g);
				g.drawImage(inv.listeObjet[compt].image, posXCase, posYCase, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null);
				posXCase += jeu.tailleCaseReelle + 15;
				compt++;
			}
			posXCase = 27;
			posYCase += jeu.tailleCaseReelle + 15;
		}
	}
	
	public void afficherFenetre(int x, int y, int largeur, int hauteur, Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, hauteur, largeur, 20, 20);
		g.setColor(Color.GRAY);
		g.fillRoundRect(x + 5, y + 5, hauteur - 10, largeur - 10, 20, 20);
	}
	
	public void  afficherCase(int x, int y, int largeur, int hauteur, Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, hauteur, largeur, 20, 20);
		g.setColor(Color.GRAY);
		g.fillRoundRect(x + 5, y + 5, hauteur - 10, largeur - 10, 20, 20);
	}
	
}
