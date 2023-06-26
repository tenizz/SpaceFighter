/**
 * This class generates the pattern of Comet and Asteroid objects release.
 *
 * @author Anuar Tenizbayev
 * @version April 15, 2023
 */
package gameCode;

import java.awt.*;

public class Generator extends GameObject{
    private GameState state;
    private Control control;
    private double releaseTime;
    private double asteroidCoolDown;
    private double cometCoolDown;
    private double asteroidTimeLine;
    private double cometTimeLine;
    private int asteroidNumber;
    private int cometNumber;
    private int asteroidCounter;
    private int cometCounter;
    private int generationCounter;

    /**
     * Constructor of Generator class, assigns Control and GameState.
     * Has multiple variable which keep track of time, number of the objects,
     * and delay between their release.
     *
     * @param control Control class
     * @param state GameState class
     */
    public Generator(Control control, GameState state){
        super();
        this.control = control;
        this.state = state;
        releaseTime = 0.5; // Release time between objects in the set
        asteroidCoolDown = 2.0; // Cool Down between releases
        cometCoolDown = 6.0; // Cool Down between releases
        asteroidNumber = 6; // Number of initial set of Asteroids
        cometNumber = 4; // Number of initial set of Comets
        asteroidCounter = 0;
        cometCounter = 0;
        generationCounter = 0; // Keeps track of released generations
        asteroidTimeLine = asteroidCoolDown; // Sets the timeline of the Asteroid set
        cometTimeLine = cometCoolDown + releaseTime * asteroidNumber;  // Sets the timeline of the Comet set
    }

    /**
     * Method from Animatable which helps to update position of the objects
     *
     * @param elapsedTime Time of the timer. Rate of the Updates
     */
    @Override
    public void update(double elapsedTime) {

        asteroidTimeLine -= elapsedTime;
        cometTimeLine -= elapsedTime;

        if (asteroidTimeLine <= 0.0){
            if (asteroidCounter < asteroidNumber) {
                asteroidTimeLine = releaseTime; // 0.5 seconds release time between objects
                state.addGameObject(new Asteroid(control, state));
                asteroidCounter++;
            } else {
                asteroidCounter = 0;
                asteroidTimeLine = asteroidCoolDown + releaseTime * cometNumber + cometCoolDown ; // 9.0
                generationCounter++;
            }
        }

        if (cometTimeLine <= 0.0){
            if (cometCounter < cometNumber) {
                cometTimeLine = releaseTime; // 0.5 seconds release time between objects
                state.addGameObject(new Comet(control, state));
                cometCounter++;
            } else {
                cometCounter = 0;
                cometTimeLine = cometCoolDown + releaseTime * asteroidNumber + asteroidCoolDown; // 10.0
            }
        }

        // This increases the number of Comets and Asteroids each 4 generation have been released
        if (generationCounter >= 4){
            asteroidNumber += generationCounter * 2;
            cometNumber += generationCounter;
            generationCounter = 0;
        }

    }

    /**
     * Method from Animatable that draws the object.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {}
}
