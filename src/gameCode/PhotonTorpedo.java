/**
 * This class creates Photon objects which are shot from the Satellite objects.
 *
 * @author Anuar Tenizbayev
 * @version April 15, 2023
 */
package gameCode;

import java.awt.*;

public class PhotonTorpedo extends GameObject{
    private Control control;
    private GameState state;
    private double percentage;
    private int sourceX;
    private int sourceY;
    private int targetX;
    private int targetY;
    private Path photonPath;
    private double age;

    /**
     * Constructs Photon Objects
     *
     * @param control Control Class
     * @param state Game State Class
     * @param sourcePosition Initial Starting position
     * @param targetPosition Aiming final position
     */
    public PhotonTorpedo(Control control, GameState state, Point sourcePosition, Point targetPosition){
        super();
        this.control = control;
        this.state = state;
        percentage = 0;
        sourceX = (int) sourcePosition.getX();
        sourceY = (int) sourcePosition.getY();
        targetX = (int) targetPosition.getX();
        targetY = (int) targetPosition.getY();
        photonPath = new Path();
        photonPath.addPoint(sourceX, sourceY);
        photonPath.addPoint(targetX, targetY);
        age = 0.0;
    }

    /**
     * Updates objects frames
     *
     * @param elapsedTime time which is required for the update
     */
    @Override
    public void update(double elapsedTime) {
        if (age >= 0.8){
            hasExpired = true;
        }
        Point loc = photonPath.convertToCoordinates(percentage);
        if (percentage >= 0.99){
            if (state.collideEvent(new Point( loc.x, loc.y)) != null) {
                Point objectPoint = new Point(targetX, targetY);
                Point currentPoint = new Point(loc.x, loc.y);
                if (currentPoint.distance(objectPoint) <= 100) {
                    hasExpired = true;
                    state.collideEvent(new Point(targetX, targetY)).hasExpired = true;
                    // Getting the location of each object and drawing explosion there
                    Point individualLoc = ((Targetable)  state.collideEvent(new Point(targetX, targetY))).getLocation();
                    state.addGameObject(new LaserExplosion(control, state, individualLoc.x, individualLoc.y));
                    state.setMoney(state.getMoney() + 10);
                    state.setScore(state.getScore() + 1);
                }
            }
        }
        percentage += 8 * elapsedTime;
        age += elapsedTime;
    }

    /**
     * Draws the objects
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        Point loc = photonPath.convertToCoordinates(percentage);
        drawCenteredImage(g, control.getImage("photon.png"), loc.x, loc.y, 0.1);
    }
}
