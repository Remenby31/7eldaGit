package Entite;
import java.awt.Graphics2D;
import Main.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Joueur implements Entite {
    private int x,y;
    public ControleClavier mouvement;
    private Jeu jeu;
    public final int vitesse = 3;
    public final static int dynamisme = 10;
    private BufferedImage Avant_marche1, Avant_marche2, Avant_static, Derriere_marche1, Derriere_marche2, Derriere_static, Droite_marche, Droit_static, Gauche_static, Gauche_marche;
    private int changement_texture, compteur;
    private String deplacement;
    //added by mohamed
    public boolean collisionRencontree = false;
    public Inventaire inv;


    public Joueur(Jeu jeu, ControleClavier mouvement) {
        this.x = 420;
        this.y = 320 ;
        this.jeu = jeu;
        this.mouvement = mouvement;
        getImage();
        this.deplacement = "bas";
        this.changement_texture = 2;
        this.inv = new Inventaire(10, 10, jeu.tailleCaseReelle * 5 + 15, jeu.tailleCaseReelle * 4);

    }
    private void getImage() {
        try {
            Avant_marche1 = ImageIO.read(getClass().getResourceAsStream("/Ressources/Avant_marche1.png"));
            Avant_marche2 = ImageIO.read(getClass().getResourceAsStream("/Ressources/Avant_marche2.png"));
            Avant_static = ImageIO.read(getClass().getResourceAsStream("/Ressources/Avant_static.png"));
            Derriere_marche1 = ImageIO.read(getClass().getResourceAsStream("/Ressources/Derriere_marche1.png"));
            Derriere_marche2 = ImageIO.read(getClass().getResourceAsStream("/Ressources/Derriere_marche2.png"));
            Derriere_static = ImageIO.read(getClass().getResourceAsStream("/Ressources/Derriere_static.png"));
            Droite_marche = ImageIO.read(getClass().getResourceAsStream("/Ressources/Droite_marche.png"));
            Droit_static = ImageIO.read(getClass().getResourceAsStream("/Ressources/Droite_static.png"));
            Gauche_static = ImageIO.read(getClass().getResourceAsStream("/Ressources/Gauche_static.png"));
            Gauche_marche = ImageIO.read(getClass().getResourceAsStream("/Ressources/Gauche_marche.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int getX() { return this.x; }
    
    public int getY() { return this.y; }

    /**Update les coordonn�es
     * @param x Correspond au d�placement selon x
     * @param y Correspond au d�placement selon y
     */
    public void miseAJour() {
        if (mouvement.bas) {
            deplacement = "bas";
        }
        else if (mouvement.haut) {
            deplacement = "haut";
        }
        else if (mouvement.gauche) {
            deplacement = "gauche";
        }
        else if (mouvement.droite) {
            deplacement = "droite";
        } else { 
            switch (deplacement) {
            case "bas" :
                deplacement = "avant_static";
                break;
            case "haut" :
                deplacement = "derriere_static";
                break;
            case "droite" :
                deplacement = "droite_static";
                break;
            case "gauche" :
                deplacement = "gauche_static";
                break;
            }
        }   
        
        collisionRencontree = false;
        jeu.collisionVerificateur.verifierCollision(this);
        
        if (collisionRencontree == false){
            switch(deplacement){
            case "haut" :
                this.y -= vitesse;
                break;
            case "bas" :
                this.y += vitesse;
                break;
            case "gauche" :
                this.x -= vitesse;
                break;
            case "droite" :
                this.x += vitesse;
                break;
            }
        }
        compteur ++;
        if (compteur > dynamisme) {
            if (changement_texture == 1) {
                changement_texture = 2;
            } else if (changement_texture == 2) {
                changement_texture = 1;
            }
            compteur = 0;
        }

    }
    public void afficher(Graphics2D g) {
        BufferedImage image = null;
        // 0 -> statique
        switch (deplacement) {
        case "haut":
            if (changement_texture == 1) {
                image = Derriere_marche1;
            }
            if (changement_texture == 2) {
                image = Derriere_marche2;
            }
            break;
        case "derriere_static" : 
            image = Derriere_static;
            break;
        
        case "bas":
            if (changement_texture == 1) {
                image = Avant_marche1;
            }
            if (changement_texture == 2) {
                image = Avant_marche2;
            }
            break;
        case "avant_static" : 
            image = Avant_static;
            break;
        case "gauche" :
            if (changement_texture == 1) {
                image = Gauche_marche;
            }
            if (changement_texture == 2) {
                image = Gauche_static;
            }
            break;
        case "gauche_static" : 
            image = Gauche_static;
            break;
        case "droite" :
            if (changement_texture == 1) {
                image = Droite_marche;
            }
            if (changement_texture == 2) {
                image = Droit_static;
            }
            break;
        case "droite_static" : 
            image = Droit_static;
            break;
        }

        g.drawImage(image, this.x, this.y, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null);

    }
    
    @Override
    public boolean estPresent() {
        return false;
    }
    
    public void attaquer() {}

        
    

    

}
