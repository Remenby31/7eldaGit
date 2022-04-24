package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleClavier implements KeyListener{

	public boolean haut, bas, gauche, droite, fleche;
	public boolean inv = false;
	public boolean give = false;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		int codeTouche = e.getKeyCode();
		
		if(codeTouche == KeyEvent.VK_Z) {
			haut = true;
		}
		
		if(codeTouche == KeyEvent.VK_Q) {
			gauche = true;
		}

		if(codeTouche == KeyEvent.VK_S) {
			bas = true;
		}

		if(codeTouche == KeyEvent.VK_D) {
			droite = true;
		}
		
		if(codeTouche == KeyEvent.VK_I) {
			Jeu.etatJeu = (Jeu.etatJeu == Jeu.EtatJeu.EN_JEU) ? Jeu.EtatJeu.INVENTAIRE : Jeu.EtatJeu.EN_JEU;

		}
		
		if(codeTouche == KeyEvent.VK_C) {
			give = true;
		}
		
		if(codeTouche == KeyEvent.VK_E) {
			fleche = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int codeTouche = e.getKeyCode();
		
		if(codeTouche == KeyEvent.VK_Z) {
			haut = false;
		}
		
		if(codeTouche == KeyEvent.VK_Q) {
			gauche = false;
		}

		if(codeTouche == KeyEvent.VK_S) {
			bas = false;
		}

		if(codeTouche == KeyEvent.VK_D) {
			droite = false;
		}
		
		if(codeTouche == KeyEvent.VK_C) {
			give = false;
		}
		
		if(codeTouche == KeyEvent.VK_E) {
			fleche = false;
		}
		
	}
}
