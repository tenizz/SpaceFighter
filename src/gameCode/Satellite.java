/**
 * This class creates Towers (Satellites) which will destroy comets and asteroids and protect the Earth.
 *
 * @author Anuar Tenizbayev
 * @version April 9, 2023
 */
package gameCode;

import java.awt.*;

public class Satellite extends GameObject {
    private Control control;
    private GameState state;
    private boolean isMoving;
    private int x;
    private int y;
    private double timeLine;
    public Satellite(Control control, GameState state){
        isMoving = true;
        this.control = control;
        this.state = state;
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
        if (isMoving){
            this.x = (int) state.getCurrentMousePosition().getX();
            this.y = (int) state.getCurrentMousePosition().getY();
        } else {
            timeLine += elapsedTime;
            if (state.findNearest(new Point(x, y)) != null) {
                Point objectPoint = state.findNearest(new Point(x, y)).getLocation();
                Point currentPoint = new Point(x, y);
                if (currentPoint.distance(objectPoint) <= 100) {
                    // Fire
                    if (timeLine >= 2.0){
                        state.addGameObject(new PhotonTorpedo(control, state, currentPoint, objectPoint)); // Add a Photon to the list
                        timeLine = 0.0;
                    }
                }
            }
        }

    }

    /**
     * Draws the objects
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        drawCenteredImage(g, control.getImage("satellite.png"), x, y, 0.1);
        g.setColor(Color.RED);
        if (isMoving){
            g.drawRect(120, 230, 100, 100);
            g.drawRect(400, 130, 50, 50);
            g.drawRect(465, 345, 40, 40);
        }
    }

    /**
     * Consumes click if user is dragging the object and chooses a spot to place in the region of
     * the Game not Menu. If clicked is consumed object stops following the cursor.
     *
     * @return True or False.
     */
    @Override
    public boolean consumeClick() {
        if (isMoving){
            if ((x >= 0 && x <= 600 && y >= 0 && y <= 600)
                    && !(x >= 120 && x <= 220 && y >= 230 && y <= 430)
                    && !(x >= 400 && x <= 450 && y >= 130 && y <= 180)
                    && !(x >= 465 && x <= 505 && y >= 345 && y <= 385)){
                isMoving = false;
            } else {
                hasExpired = true;
                state.setMoney(state.getMoney() + state.getSatelliteCost());
            }
            return true;
        } else
            return false;
    }
}
