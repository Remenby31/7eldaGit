package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Entite.Ennemi1;
import Entite.Joueur;
import Map.Importationsalle;
import Map.ModeleMap;
import Map.ModeleMap2;
import Map.Salle;
import Map.Salle2;

public class Jeu extends JPanel implements Runnable {

	/** Paramètres Ecran */
	public final static int tailleCaseDeBase = 32; // Textures en 32 X 32
	public final static int echelle = 2;
	public final static int tailleCaseReelle = tailleCaseDeBase * echelle; // Taille à l'écran
	public final int maxCol = 16; // Nombre de cases en colonne
	public final int maxLigne = 12; // Nombre de cases en ligne
	public final int largeurEcran = 1024; // Taille de la fenêtre en largeur
	public final int hauteurEcran = 750; // Taille de la fenêtre en hauteur
	public final int IPS = 70; // Images par seconde

	/** Etat de jeu (JEU - PAUSE - INVENTAIRE) */
	enum EtatJeu {
		EN_JEU, PAUSE, INVENTAIRE
	};

	public static EtatJeu etatJeu = EtatJeu.EN_JEU;
	public Inventaire inv;
	public Collision collisionVerificateur = new Collision(this);

	// Definir la carte du jeu
	public ModeleMap map = new ModeleMap(this);
	public Salle2 salle2 = new Salle2(this);
	public Importationsalle salle  =  new Importationsalle(new ModeleMap(this));
	public Salle sallec;
	// Paramètre joueur
	ControleClavier gestionnaireTouche = new ControleClavier();

	// Définir la collision
	Collision collision = new Collision(this);
	public Ennemi1 ennemi1 = new Ennemi1(this);
	public Joueur joueur = new Joueur(this, gestionnaireTouche);
	public GUI gui = new GUI(this, joueur);

	// Création du "jeu"
	public Jeu() {
		this.setPreferredSize(new Dimension(largeurEcran, hauteurEcran)); // Taille de la fenêtre
		this.setDoubleBuffered(true);
		this.addKeyListener(gestionnaireTouche); // Ajout du gestionnaire de touches
		this.setFocusable(true);
		this.requestFocusInWindow();

	}

	/* Getteur du joueur (pour les ennemis) */
	public Joueur getJoueur() {
		return joueur;
	}

	// Création de la boucle
	Thread threadJeu;

	public void debutThread() {
		threadJeu = new Thread(this);
		threadJeu.start();
	}

	// Méthode qui actualise la position du joueur toutes les x secondes
	@Override
	public void run() {

		double intervalleAffichage = 1000000000 / IPS; // Une actualisation toutes les x secondes
		double delta = 0;
		long itePrecedente = System.nanoTime();
		long iteCourante;
		this.grabFocus();

		while (threadJeu != null) {

			/** Boucle de JEU */
			iteCourante = System.nanoTime();

			delta += (iteCourante - itePrecedente) / intervalleAffichage;
			itePrecedente = iteCourante;

			if (delta >= 1) {
				if (etatJeu == EtatJeu.EN_JEU) {
					miseAJour();
				}
				repaint();
				delta -= 1;
			}
		}
	}

	public void miseAJour() {
		joueur.miseAJour(); // Actualise le joueur
		ennemi1.miseAJour(); // Actualise l'ennemi
		
	}

	// Affichage (tous les �l�ments qui seront affich�s devront contenir une m�thode
		// afficher)
		public void paintComponent(Graphics graphique) {
			super.paintComponent(graphique);
			Graphics2D graphique2D = (Graphics2D) graphique;
			/** Affichage des elements du jeu */
			//salle2.dessiner(graphique2D);
			
			salle = new Importationsalle(new ModeleMap(this)); 
			salle.getSalle().dessiner(graphique2D);
			joueur.afficher(graphique2D);
			ennemi1.afficher(graphique2D);
			gui.afficher(graphique2D);
			
			if(collisionVerificateur.lancer_salle2==true) {
				//map.map[11].collision = false;
				salle = new Importationsalle(new Salle2(this));
				sallec =salle.getSalle();
				salle.getSalle().dessiner(graphique2D);
				joueur.afficher(graphique2D);
				
				
				
			}
			
			if(collisionVerificateur.lancer_salle3==true) {
				//map.map[11].collision = false;
				salle = new Importationsalle(new ModeleMap2(this));
				sallec =salle.getSalle();
				salle.getSalle().dessiner(graphique2D);
				joueur.afficher(graphique2D);
				
				
				
			}
			
			if(collisionVerificateur.lancer_ModeleMap == true) {
				salle = new Importationsalle(new ModeleMap(this));
				salle.getSalle().dessiner(graphique2D);
				joueur.afficher(graphique2D);
				ennemi1.afficher(graphique2D);
				gui.afficher(graphique2D);


			}
			

			if(collisionVerificateur.lancer_ModeleMap2 == true) {
				salle = new Importationsalle(new Salle2(this));
				sallec =salle.getSalle();
				salle.getSalle().dessiner(graphique2D);
				joueur.afficher(graphique2D);
				ennemi1.afficher(graphique2D);
				gui.afficher(graphique2D);
				

			}
			
			
			
			/** Affichage de l'inventaire */
			if (etatJeu == EtatJeu.INVENTAIRE) {
				gui.afficherInventaire(graphique2D, joueur.inv);

				if (joueur.mouvement.give) {gui.inv.ajouterObjet(gui.inv.tousLesObjets[1]);} //Triche de Christophe
			}
			
			graphique2D.dispose();
			
			}
}

