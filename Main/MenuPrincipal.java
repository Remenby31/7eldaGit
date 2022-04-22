package Main;
import javax.imageio.ImageIO;
import javax.swing.*;

import map.ModeleMap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPrincipal{
    
	JPanel panel = new JPanel(new FlowLayout ( ) ); 
	
	Jeu jeu = new Jeu();
	Graphics graph;
	private static JFrame frame = new JFrame("7elda");
   
    private class ActionNouvellePartie implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			 	frame.setVisible(false);
			 	JFrame fenetre = new JFrame();
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.setResizable(false); // Pas la possibilité de déformer la fenêtre
				fenetre.setTitle("7elda"); // Titre de la fenêtre
				
				fenetre.setLocationRelativeTo(null); // Affichage de la fenêtre au centre de l'ecran
				fenetre.setVisible(true); // Fenêtre visible
				
				// Ajout du jeu à la fenêtre
				Jeu jeu = new Jeu();
				fenetre.add(jeu);
				fenetre.pack();
				// Lancement de la boucle du jeu
				jeu.debutThread();
				
			 } 
		 
	}
	private class ActionQuitter implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			 System.exit(0);
		 } 
		 
	}
	
	public void Boutons() throws IOException {
		

	    frame.setContentPane(new JPanel() {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_principal.png"));
	        public void paintComponent (Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(image, 0, 0, 300, 300, this);
	        }
	    });
		
		JButton bStart = new JButton ( "Start" ) ;
	    frame.add(bStart);
	    JButton bQuitter = new JButton ( "Quitter" ) ;
	    frame.add(bQuitter);
	    bStart.addActionListener(new ActionNouvellePartie());
	    bQuitter.addActionListener(new ActionQuitter());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 300);
	    frame.setVisible(true);
	}
	
}