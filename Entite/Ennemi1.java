package Entite;
import Main.*;

import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ennemi1 implements Entite {
	
	public int compteurImage = 0;
	public int numeroImage = 1;
	public Jeu jeu;
	public int pdv;
	public boolean vivant;
	private int x;
	private int y;
	public int vitesse;
	public int dir;
	public int lastDir = 1;
	public Random r;
	private BufferedImage Avant_marche1, Avant_static, Derriere_marche1, Derriere_static, Droite_marche, Droit_static, Gauche_static, Gauche_marche;
	private int update;
	
	public Ennemi1(Jeu jeu) {
		this.jeu = jeu;
		this.pdv = 3;
		this.vivant = true;
		this.x = 300;
		this.y = 300;
		this.vitesse = 2;
		this.r = new Random();
		this.dir = getRandomNumberInRange(1, 4);
		getImage();
	}
	public int getVitesse() {
        return this.vitesse;
    }

	public int getX() { return this.x; }
    
    public int getY() { return this.y; }

	private void getImage() {
		try {
			Avant_marche1 = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Avant_marche1.png"));
	        Avant_static = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Avant_static.png"));
	        Derriere_marche1 = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Derriere_marche1.png"));
	        Derriere_static = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Derriere_static.png"));
	        Droite_marche = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Droite_marche.png"));
	        Droit_static = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Droite_static.png"));
	        Gauche_static = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Gauche_static.png"));
	        Gauche_marche = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/blop_Gauche_marche.png"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
}

	@Override
	public boolean estPresent() {
		return vivant;
	}
	
	@Override
	public void afficher(Graphics2D g) {
		BufferedImage image = null;
		switch(dir) {
		case 1 :
			if (numeroImage == 1) {
				image = Droite_marche;
			} else if (numeroImage == 2) {
				image = Droit_static;
			} 
			break;
		case 2 :
			if (numeroImage == 1) {
				image = Avant_marche1;
			} else if (numeroImage == 2) {
				image = Avant_static;
			}
			break;
		case 3 :
			if (numeroImage == 1) {
				image = Gauche_marche;
			} else if (numeroImage == 2) {
				image = Gauche_static;					
			}
			break;
		case 4 :
			if (numeroImage == 1) {
				image = Derriere_marche1;
			} else if (numeroImage == 2) {
				image = Derriere_static;
			}
			break;
		case 0 : 
			switch(lastDir) {
			case 1 :
					image = Droit_static;
				break;
			case 2 :
					image = Avant_static;
				break;
			case 3 :
					image = Gauche_static;					
				break;
			case 4 :
					image = Derriere_static;
				break;
			}
		}
		g.drawImage(image, x, y, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null);
		
	}

	@Override
	public void miseAJour() {
		if (update >= 150) {
			update = 0;
			dir = getRandomNumberInRange(1,5);
			lastDir = dir;
		} else if (update >= 50) {
			dir = 0;
		}
		switch(dir) {
		case 1 :
			this.x += vitesse;
			update += 1;
			break;
		case 2 :
			this.y += vitesse;
			update += 1;
			break;
		case 3 :
			this.x -= vitesse;
			update += 1;
			break;
		case 4 :
			this.y -= vitesse;
			update += 1;
			break;
		default :
			update += 1;
		}
		
		compteurImage ++;
		
		if (compteurImage > 9) {
			if (numeroImage == 1 ) {
				numeroImage = 2;
			} else {
				numeroImage = 1;
			}
			compteurImage = 0;
		}	
	}
	
	private int getRandomNumberInRange(int min, int max) {
		return r.nextInt(max - min) + min;
	}
	public Direction getDirection() {
		switch (dir) {
			case 1 : return Direction.DROITE;
			case 2 : return Direction.BAS;
			case 3 : return Direction.GAUCHE;
			case 4 : return Direction.HAUT;
		}
		return Direction.BAS;
	}
	
}