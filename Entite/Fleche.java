package Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.*;

public class Fleche implements Entite{
    public int x, y;
    private Hitbox hitbox;

    private Entite entite;
    private BufferedImage fleche_droite, fleche_gauche, fleche_haut, fleche_bas, image;
    private boolean estPresent;
    private int vitesse = 6;
    private Direction direction;
    private Jeu jeu;

    public Fleche(Entite e, Jeu jeu) {
        this.hitbox = new Hitbox(this,-25, 60, 50, -13); 
        this.jeu = jeu;
        this.entite = e;
        direction = e.getDirection();
        this.estPresent = true;
        getImage();
        switch (entite.getDirection()) {
            case DROITE :
                this.x = entite.getX() + 32;
                this.y = entite.getY();
                image = fleche_droite;
                break;
            case GAUCHE : 
                this.x = entite.getX() - 32;
                this.y = entite.getY();
                image = fleche_gauche;
                break;
            case HAUT : 
                this.x = entite.getX();
                this.y = entite.getY() - 32;
                image = fleche_haut;
                break;
            case BAS : 
                this.x = entite.getX();
                this.y = entite.getY() + 32;
                image = fleche_bas;
                break;
        }
        //afficher flèche
    }
    
    public void setVitesse(int x) {
        this.vitesse = x;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    public int getVitesse() { return this.vitesse; }

    public int getX() { return this.x; }
    
    public int getY() { return this.y; }

	public Hitbox getHitbox() { return this.hitbox; }

    public boolean estPresent() { return estPresent; }


    public void miseAJour() {
        if (this.estPresent) {
            if (!jeu.collisionVerificateur.verifierCollision(this)){
                switch (direction) {
                    case DROITE :
                        this.x += this.vitesse;
                        break;
                    case GAUCHE : 
                        this.x -= this.vitesse;
                        break;
                    case HAUT : 
                        this.y -= this.vitesse;
                        break;
                    case BAS : 
                        this.y += this.vitesse;
                        break;
                }
            } else {
                this.estPresent = false;
            } 
        }   
    }
    public void afficher(Graphics2D g) {
        hitbox.afficher(g);
        g.drawImage(this.image, this.x, this.y, Jeu.tailleCaseReelle,Jeu.tailleCaseReelle, null);

    }
    
    private void getImage() {
        try {
            fleche_gauche = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/flèche_gauche.png"));
            fleche_bas = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/flèche_bas.png"));
            fleche_droite = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/flèche_droite.png"));
            fleche_haut = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/flèche_haut.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}