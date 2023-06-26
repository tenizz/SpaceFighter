/**
 * This class creates Background.
 *
 * @author Anuar Tenizbayev
 * @version April 4, 2023
 */
package gameCode;

import java.awt.*;

public class Background extends GameObject{
    private GameState state;
    private Control control;

    /**
     * Constructor of Background class, assigns Control and GameState.
     *
     * @param control Control class
     * @param state GameState class
     */
    public Background(Control control, GameState state){
        super();
        this.control = control;
        this.state = state;
    }

    /**
     * Method from Animatable which helps to update position of the objects
     *
     * @param elapsedTime Time of the timer. Rate of the Updates
     */
    @Override
    public void update(double elapsedTime) {
        // Background does not update
    }

    /**
     * Method from Animatable that draws the object.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(control.getImage("background.png"), 0, 0, null);
    }
}