/**
 * This class contains the visuals of the game. GUI Form
 *
 * @author Anuar Tenizbayev
 * @version April 4, 2023
 */
package gameCode;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private GameState state;
    private Control control;

    /**
     * Constructor that creates GUI form of the game.
     *
     * @param control Control Class
     * @param state GameState Class
     */
    public View(Control control, GameState state){
        this.control = control;
        this.state = state;

        // Building the GUI
        JFrame frame = new JFrame("View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Paints the objects of the game.
     *
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(100, 100, 100, 100);
        //g.drawImage(control.loadImage("background.png"), 0, 0, null);
        for (Animatable a : state.getCurrentObjects())
            a.draw(g);

    }
}
