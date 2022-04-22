package Main;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread;


import javax.swing.JFrame;
import javax.swing.JPanel;


public class NewMenuPrincipal {

    static int WIDTH = 500;
    static int HEIGHT = 800;

    
    /** Etat du menu */
    private enum STATE {MENU, JEU, AIDE};
    static STATE State = STATE.MENU;

    /** Sur quelle bouton se trouve le curseur */
    public enum BUTTON_ENTERED {NONE, JOUER, AIDE, QUITTER};
    public static BUTTON_ENTERED buttonEntered = BUTTON_ENTERED.NONE;

    /** Les rectangles des Bouttons */
    public Rectangle playButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 350, 140, 50);
    public Rectangle helpButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 450, 140, 50);
    public Rectangle quitButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 550, 140, 50);

    MouseInputMenu mouseListener = new MouseInputMenu();

    Jeu jeu = new Jeu();
    Graphics graph;
    private static JFrame frame = new JFrame("Menu 7elda");


    public void render() throws IOException, InterruptedException {

        frame.setLocation(20, 30);

        while (State == STATE.MENU) {
            Thread.sleep(10); // Limiter les FPS

            /** === Affichage === */
            frame.setContentPane(new JPanel() {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_principal.png"));

                public void paintComponent(Graphics g) {

                    g.drawImage(image, 0, 0, 500, 800, this);
                    Graphics2D g2d = (Graphics2D) g;

                    Font fnt1 = new Font("arial", Font.BOLD, 27); // Création d'une nouvelle police d'écriture pour le
                                                                  // menu
                    g.setFont(fnt1); // Appliquer la nouvelle police d'écriture

                    g.setColor((buttonEntered == BUTTON_ENTERED.JOUER) ? Color.RED : Color.WHITE);
                    g.drawString("Jouer", NewMenuPrincipal.WIDTH / 2 - 35, 380);
                    g2d.draw(playButton);

                    g.setColor((buttonEntered == BUTTON_ENTERED.AIDE) ? Color.RED : Color.WHITE);
                    g.drawString("Controle", NewMenuPrincipal.WIDTH / 2 - 55, 480);
                    g2d.draw(helpButton);

                    g.setColor((buttonEntered == BUTTON_ENTERED.QUITTER) ? Color.RED : Color.WHITE);
                    g.drawString("Quitter", NewMenuPrincipal.WIDTH / 2 - 45, 580);
                    g2d.draw(quitButton);

                    g2d.dispose();
                    super.paintComponent(g);
                    this.addMouseListener(mouseListener);
                }
                
            });

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(NewMenuPrincipal.WIDTH, NewMenuPrincipal.HEIGHT);
            frame.setVisible(true);

            /** === Check curseur sur bouttons === */
            double mx = 0;
            double my = 0;
            if (frame.getMousePosition() != null) {
                mx = frame.getMousePosition().getX();
                my = frame.getMousePosition().getY();
            }

            NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.NONE;

            /** PlayButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 380 && my <= 440) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.JOUER;
                }
            }

            /** HelpButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 480 && my <= 540) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.AIDE;
                }
            }

            /** QuitButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 580 && my <= 640) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.QUITTER;
                }
            }
        }
        /** === Lancer le jeu === */

        frame.removeMouseListener(mouseListener);
        frame.setFocusable(false);
        frame.dispose();
        
        
        JFrame fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false); // Pas la possibilité de déformer la fenêtre
        fenetre.setTitle("Menu 7elda"); // Titre de la fenêtre

        fenetre.setLocationRelativeTo(null); // Affichage de la fenêtre au centre de l'ecran
        fenetre.setVisible(true); // Fenêtre visible
        fenetre.requestFocusInWindow();
        fenetre.setLocation(40, 30);
        

        // Ajout du jeu à la fenêtre
        Jeu jeu = new Jeu();
        fenetre.add(jeu);
        fenetre.pack();
        // Lancement de la boucle du jeu
        jeu.debutThread();
        System.out.println("Debut du jeu");
    }

    /** Action de Jouer */
    static public void ActionJouer() {
        State = STATE.JEU;
    }

    /** Action de Aide */
    static public void ActionHelp() {
        //State = STATE.AIDE;
        System.out.println("Christophe donne ton code batar");
        // TO be continued...
    }

    /** Action de Quitter */
    static public void ActionQuitter() {
        System.exit(0);
    }

}