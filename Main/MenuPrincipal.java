package Main;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Map.ModeleMap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class MenuPrincipal{
    
	JPanel panel=new JPanel(new FlowLayout ( ) ); 
	
	Jeu jeu = new Jeu();
	Graphics graph;
	JFrame fenetre = new JFrame();
	JFrame fenetreJeu = new JFrame();
	JButton boutonPasser = new JButton ( "Passer" ) ;
	BufferedImage img;
	
	private static JFrame frame = new JFrame("7elda");
   
    private class ActionNouvellePartie implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			 	frame.setVisible(false);
				fenetre.setResizable(false); // Pas la possibilité de déformer la fenêtre
				fenetre.setTitle("7elda"); // Titre de la fenêtre
				
				fenetre.setLocationRelativeTo(null); // Affichage de la fenêtre au centre de l'ecran
				
			    boutonPasser.addActionListener(new ActionPasser());
			    //boutonPasser.setSize(50,50);
			    
				fenetre.setSize(300, 300);
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    fenetre.setVisible(true);
			    try {
			    	
					fenetre.setContentPane(new JPanel() {
						BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_explication.png"));
					    public void paintComponent (Graphics g) {
					        super.paintComponent(g);
					        g.drawImage(img, 0, 0, 300, 300, this);
					        
					        
					    }
					});
					fenetre.add(boutonPasser);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    
			 } 
		 
	}
	private class ActionQuitter implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			 System.exit(0);
		 } 
		 
	}
	
	 private class ActionPasser implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			
			 fenetre.setVisible(false);
			 	
			 //Ajouter de la musique au jeu.
			 try {
				 java.net.URL soundFile = getClass().getResource("/ressources/musique.wav");
		          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); 
		          AudioFormat format = audioIn.getFormat();  
		          //Obtenir une ressource du type clip
		         DataLine.Info info = new DataLine.Info(Clip.class, format);
		         Clip clip = (Clip)AudioSystem.getLine(info);
		         //Ouvrir l'audio et charger le son
		         clip.open(audioIn);
		         clip.start();
		         //Le fichier ne contient pas le bon audio
		      } catch (UnsupportedAudioFileException e) {
		    	  //Afficher l'erreur grâce à une méthode de la classe Throwable
		    	  e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		         //Une ligne ne peut pas être ouverte
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }
			
			 	// Ajout du jeu à la fenêtre
				Jeu jeu = new Jeu();
				fenetreJeu.add(jeu);
				fenetreJeu.pack();
				fenetreJeu.setSize(1024, 780);
				fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    fenetreJeu.setVisible(true);
				// Lancement de la boucle du jeu
				jeu.debutThread();
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
