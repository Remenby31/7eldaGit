package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputMenu implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        /**
         * public Rectangle playButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70,
         * 150, 140, 50);
         * public Rectangle helpButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70,
         * 250, 140, 50);
         * public Rectangle quitButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70,
         * 350, 140, 50);
         */

         
        // PlayButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 350 && my <= 400) {
                //System.out.println("Play !");
                NewMenuPrincipal.ActionJouer();

            }
        }

        // HelpButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 450 && my <= 500) {
                //System.out.println("Help !");
                NewMenuPrincipal.ActionHelp();
            }
        }

        // QuitButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 550 && my <= 600) {
                System.out.println("Quitter !");
                NewMenuPrincipal.ActionQuitter();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
