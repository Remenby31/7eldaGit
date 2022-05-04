package Main;

import Entite.Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;




public class Vie {
    /* Attribut */
    private BufferedImage Coeur_vide, Coeur_plein, Coeur_moitie;
    private double vie;
    private Entite e;
    
    /* Constructeur Vie */
    public Vie(Entite e) {
        this.e = e;
        this.vie = e.getCoeurMax();
        getImage();
    }

    /* Retourne la vie du joueur */
    public double getVie() {
        return this.vie;
    }

    public void degat(double degat) {
        if (this.vie - degat > 0) {
            this.vie -= degat;
        } else {
            this.vie = 0;
        }
    }

    /* Afficher les coeurs */
    public void afficher (Graphics2D g) {
        for (int i=1; i <= e.getCoeurMax(); i++) {
            if (i <= this.vie) {
                g.drawImage(Coeur_plein, (i - 1)*Jeu.tailleCaseDeBase,0, null);
            } else if ((int) Math.ceil(this.vie) != this.vie && i == (int) Math.ceil(this.vie)) {
                g.drawImage(Coeur_moitie, (i - 1)*Jeu.tailleCaseDeBase,0, null);
            } else {
                g.drawImage(Coeur_vide, (i - 1)*Jeu.tailleCaseDeBase,0, null);
            }; 
        }
    }

    private void getImage() {
        try {
        Coeur_plein = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/Coeur_plein.png"));
        Coeur_moitie = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/Coeur_moitie.png"));
        Coeur_vide = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/Coeur_vide.png"));
        } catch (IOException e) {
            System.out.println("Problème dans les fichiers reliée a la vie");
        }
    }

}
