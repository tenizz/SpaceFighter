/**
 * This class creates Laser Explosion objects which will show up when comets and asteroids are destroyed.
 *
 * @author Anuar Tenizbayev
 * @version April 23, 2023
 */
package gameCode;

import java.awt.*;

public class LaserExplosion extends GameObject {
    private Control control;
    private GameState state;
    private int x;
    private int y;
    private double timeLine;
    public LaserExplosion(Control control, GameState state, int x, int y){
        this.control = control;
        this.state = state;
        this.x = x;
        this.y = y;
        timeLine = 0.0;
    }

    /**
     * Updates objects frames
     *
     * @param elapsedTime time which is required for the update
     */
    @Override
    public void update(double elapsedTime) {
        // Follows the position of the cursor
        if (timeLine <= 1.0) {
            timeLine += elapsedTime;

        } else
            this.hasExpired = true;
    }

    /**
     * Draws the objects
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        drawCenteredImage(g, control.getImage("explosion.png"), x, y, 0.1);
        //g.drawImage(control.getImage("probe.png"), x, y, null);
    }
}
