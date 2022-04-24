package Map;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Jeu;

public class ModeleMap2 implements Salle {
	private Jeu jeu;
	public Map[] map;
	public int[][] coordonneeMap;//Cr�er une matrice qui sera les coordonn�es sur la map
	
	public ModeleMap2(Jeu jeu) {
		this.jeu = jeu;
		map = new Map[50]; //On cr�e 10 types diff�rents de briques
		getTexture();
		coordonneeMap = new int [jeu.maxCol][jeu.maxLigne];
		creerMap();
	}

	@Override
	public void getTexture() {
		try {
			
			map[0] = new Map();
			map[0].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol1.png"));
			
			map[1] = new Map();
			map[1].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol2.png"));
			
			map[2] = new Map();
			map[2].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol3.png"));
			
			map[3] = new Map();
			map[3].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol4.png"));
			
			map[4] = new Map();
			map[4].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol5.png"));
			
			map[5] = new Map();
			map[5].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol6.png"));
			
			map[6] = new Map();
			map[6].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol7.png"));
			
			map[7] = new Map();
			map[7].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol8.png"));
			
			map[8] = new Map();
			map[8].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol9.png"));
			
			map[9] = new Map();
			map[9].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol10.png"));
			
			map[10] = new Map();
			map[10].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol11.png"));
			
			map[11] = new Map();
			map[11].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol12.png"));
			
			map[12] = new Map();
			map[12].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol13.png"));
			
			map[13] = new Map();
			map[13].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol14.png"));
			
			map[14] = new Map();
			map[14].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol15.png"));
			
			map[15] = new Map();
			map[15].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol16.png"));
			
			map[16] = new Map();
			map[16].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol17.png"));
			
			map[17] = new Map();
			map[17].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol18.png"));
			
			map[18] = new Map();
			map[18].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol19.png"));
			
			map[19] = new Map();
			map[19].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol20.png"));
			
			map[20] = new Map();
			map[20].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol21.png"));
			
			map[21] = new Map();
			map[21].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol22.png"));
			
			map[22] = new Map();
			map[22].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol23.png"));
			
			map[23] = new Map();
			map[23].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol24.png"));
			
			map[24] = new Map();
			map[24].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol25.png"));
			
			map[25] = new Map();
			map[25].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol26.png"));
			
			map[26] = new Map();
			map[26].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol27.png"));
			
			map[27] = new Map();
			map[27].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol28.png"));
			
			map[28] = new Map();
			map[28].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol29.png"));
			
			map[29] = new Map();
			map[29].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol30.png"));
			
			map[30] = new Map();
			map[30].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol31.png"));
			
			map[31] = new Map();
			map[31].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol32.png"));
			
			map[32] = new Map();
			map[32].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol33.png"));
			
			map[33] = new Map();
			map[33].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol34.png"));
			
			map[34] = new Map();
			map[34].image = ImageIO.read(ModeleMap.class.getResource("/ressourcesmodelmap2/sol35.png"));
			
			
			//for (int k = 1; k<47; k++) {
			//	map[k].collision = true;
			//}
			
			//map[12].collision = false; 
			//map[13].collision = false; 
		}catch(IOException e) {
			System.out.print("Le fichier est inexistant");
		}
		
	}
	
	@Override
	public void creerMap()  {
		//Permet de lire le fichier txt
		InputStream carte = getClass().getResourceAsStream("/ressourcesmodelmap2/carte3");
		BufferedReader map = new BufferedReader(new InputStreamReader(carte));
		int colonne = 0;
		int ligne = 0 ;
		try {
		//On parcourt tout le fichier txt
		while (colonne < jeu.maxCol && ligne < jeu.maxLigne) {
			//On lit la ligne 
			
				String ligne_map = map.readLine();
			
			
			//Permet de parcourir toute la ligne
			while(colonne<jeu.maxCol) {
				String[] words = ligne_map.split(" ");
				int valeur_case = Integer.parseInt(words[colonne]);
				//On initialise notr matrice avec la valeur du fichier txt
				coordonneeMap[colonne][ligne] = valeur_case;
				colonne ++;
			}
			//Quand on a finit de traiter une ligne on passe a la suivante
			if(colonne == jeu.maxCol) {
				colonne =0;
				ligne++;
			}
			
		}
		map.close();
		}catch(IOException e){
			System.out.println("Erreur sur le fichier");
		}
	}
	
	@Override
	public void dessiner(Graphics graphique) {
		
		int cube_pos_x =0;
		int cube_pos_y = 0;
		int longueur = 0;
		int largeur = 0 ;
		
		while (longueur < jeu.maxCol && largeur < jeu.maxLigne) {
			int dessiner_map = coordonneeMap[longueur][largeur];
			graphique.drawImage(map[dessiner_map].image, cube_pos_x, cube_pos_y, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null); // Dessiner l'image
			longueur += 1;
			cube_pos_x += jeu.tailleCaseReelle;
			
			if (longueur == jeu.maxCol) {
				longueur = 0;
				cube_pos_x = 0;
				cube_pos_y += jeu.tailleCaseReelle; 
				largeur += 1;
				
			}
			
		}
	}

	@Override
	public Map[] getMap() {
		return this.map;
	}
	
	@Override
	public int[][] getCoordonneeMap () {
		return this.coordonneeMap;
	}
	
	
	}
