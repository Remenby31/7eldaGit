package Entite;
import java.awt.Graphics2D;

public interface Entite {
	
	
    /**Savoir si l'entité est présent sur la map
     * @return un booléen (true = présent, false = abscent)
     */
    public boolean estPresent();

    public void afficher(Graphics2D g);
    
    public void miseAJour();



}