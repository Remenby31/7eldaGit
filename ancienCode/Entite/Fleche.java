package Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.*;

public class Fleche implements Entite{
    public int x,y;
    private Entite entite;
    private BufferedImage flèche_droite, flèche_gauche, flèche_haut, flèche_bas, image;
    private boolean estPresent;
    private int vitesse = 6;
    private Direction direction;
    private Jeu jeu;

    public Fleche(Entite e, Jeu jeu) {
        this.jeu = jeu;
        this.entite = e;
        direction = e.getDirection();
        this.estPresent = true;
        getImage();
        switch (entite.getDirection()) {
            case DROITE :
                this.x = entite.getX() + 32;
                this.y = entite.getY();
                image = flèche_droite;
                break;
            case GAUCHE : 
                this.x = entite.getX() - 32;
                this.y = entite.getY();
                image = flèche_gauche;
                break;
            case HAUT : 
                this.x = entite.getX();
                this.y = entite.getY() - 32;
                image = flèche_haut;
                break;
            case BAS : 
                this.x = entite.getX();
                this.y = entite.getY() + 32;
                image = flèche_bas;
                break;
        }
        //afficher flèche
    }
    
    public void setVitesse(int x) {
        this.vitesse = x;
    }


    @Override
    public boolean estPresent() {
        return this.estPresent;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getVitesse() {
        return this.vitesse;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

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
        g.drawImage(this.image, this.x, this.y, jeu.tailleCaseReelle,jeu.tailleCaseReelle, null);

    }
    
    private void getImage() {
        try {
            flèche_gauche = ImageIO.read(getClass().getResourceAsStream("/ressources/flèche_gauche.png"));
            flèche_bas = ImageIO.read(getClass().getResourceAsStream("/ressources/flèche_bas.png"));
            flèche_droite = ImageIO.read(getClass().getResourceAsStream("/ressources/flèche_droite.png"));
            flèche_haut = ImageIO.read(getClass().getResourceAsStream("/ressources/flèche_haut.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}