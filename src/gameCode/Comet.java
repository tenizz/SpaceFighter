/**
 * This class creates Comet objects.
 *
 * @author Anuar Tenizbayev
 * @version April 4, 2023
 */
package gameCode;

import java.awt.*;

public class Comet extends GameObject implements Targetable{
    private GameState state;
    private Control control;
    private double percentage;  // The position along the path
    private double age;

    /**
     * Constructor of Comet class, assigns Control and GameState, percentage is 0.
     * It means that it is located at the starting point.
     *
     * @param control Control class
     * @param state GameState class
     */
    public Comet(Control control, GameState state){
        super();
        this.control = control;
        this.state = state;
        percentage = 0;
        age = 0.0;
    }
    /**
     * Method from Animatable which helps to update position of the objects
     *
     * @param elapsedTime Time of the timer. Rate of the Updates
     */
    @Override
    public void update(double elapsedTime) {
        if (percentage >= 0.99){
            hasExpired = true;
            state.setCities(state.getCities() - 10);
        }
        percentage += (10.1/100.0) * elapsedTime;  // Slightly different values should be used here for different object types
        age += elapsedTime;
    }

    /**
     * Method from Animatable that draws the object.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        Point loc = control.getPath().convertToCoordinates(percentage);
        drawCenteredImage(g, control.getImage("comet.png"), loc.x, loc.y, 0.09);
    }

    /**
     * Gets the location of the objects
     *
     * @return Point variable with coordinates
     */
    @Override
    public Point getLocation() {
        Point loc = control.getPath().convertToCoordinates(percentage);
        return loc;
    }
}
