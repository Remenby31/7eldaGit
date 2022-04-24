package Entite;
import java.awt.Graphics2D;

public interface Entite {
	
	
    /**Savoir si l'entit� est pr�sent sur la map
     * @return un bool�en (true = pr�sent, false = abscent)
     */
    public boolean estPresent();

    public void afficher(Graphics2D g);
    
    public void miseAJour();



}