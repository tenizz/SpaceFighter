/**
 * Interface that forms methods update and draw.
 *
 * @author Anuar Tenizbayev
 * @version April 4, 2023
 */
package gameCode;

import java.awt.*;

public interface Animatable {
    // Update method redraws the object

    /**
     * Updates objects frames
     * @param elapsedTime time which is required for the update
     */
    public void update (double elapsedTime);

    /**
     * Draws the objects
     * @param g the <code>Graphics</code> context in which to paint
     */
    public void draw (Graphics g);
}