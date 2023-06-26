/**
 * This class creates Bomb objects which are shot from the Satellite objects.
 *
 * @author Anuar Tenizbayev
 * @version April 22, 2023
 */
package gameCode;

import java.awt.*;

public class Bomb extends GameObject{
    private Control control;
    private GameState state;
    private double percentage;
    private int sourceX;
    private int sourceY;
    private int targetX;
    private int targetY;
    private Path bombPath;
    private double age;

    /**
     * Constructs Bomb Objects
     *
     * @param control Control Class
     * @param state Game State Class
     * @param sourcePosition Initial Starting position
     * @param targetPosition Aiming final position
     */
    public Bomb(Control control, GameState state, Point sourcePosition, Point targetPosition){
        super();
        this.control = control;
        this.state = state;
        percentage = 0;
        sourceX = (int) sourcePosition.getX();
        sourceY = (int) sourcePosition.getY();
        targetX = (int) targetPosition.getX();
        targetY = (int) targetPosition.getY();
        bombPath = new Path();
        bombPath.addPoint(sourceX, sourceY);
        bombPath.addPoint(targetX, targetY);
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
        Point loc = bombPath.convertToCoordinates(percentage);
        if (percentage >= 0.99 && !state.collideBombEvent(new Point( loc.x, loc.y)).isEmpty()){
            for (int i = 0; i < state.collideBombEvent(new Point( loc.x, loc.y)).size() - 1; i++){
                if (state.collideBombEvent(new Point( loc.x, loc.y)).get(i) != null) {
                    Point objectPoint = new Point(targetX, targetY);
                    Point currentPoint = new Point(loc.x, loc.y);
                    if (currentPoint.distance(objectPoint) <= 100) {
                        hasExpired = true;
                        state.collideBombEvent(new Point(loc.x, loc.y)).get(i).hasExpired = true;
                        // Getting the location of each object and drawing explosion there
                        Point individualLoc = ((Targetable) state.collideBombEvent(new Point( loc.x, loc.y)).get(i)).getLocation();
                        state.addGameObject(new Explosion(control, state, individualLoc.x, individualLoc.y));
                        state.setMoney(state.getMoney() + 10);
                        state.setScore(state.getScore() + 1);
                    }
                }
            }
        }
        percentage += 6 * elapsedTime;
        age += elapsedTime;
    }

    /**
     * Draws the objects
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        Point loc = bombPath.convertToCoordinates(percentage);
        drawCenteredImage(g,control.getImage("bomb.png"), loc.x, loc.y, 0.1);
    }
}
