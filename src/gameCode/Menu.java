/**
 * This class creates Menu which will allow user to see the amount of Money and Lives he has.
 * Also, it will allow him to drag and drop Satellites to the game.
 *
 * @author Anuar Tenizbayev
 * @version April 8, 2023
 */
package gameCode;

import java.awt.*;

public class Menu extends GameObject{
    private GameState state;
    private Control control;
    private double time;

    public Menu(Control control, GameState state){
        super();
        this.control = control;
        this.state = state;
        time = 0.0;

    }
    /**
     * Updates objects frames
     *
     * @param elapsedTime time which is required for the update
     */
    @Override
    public void update(double elapsedTime) {
        // Menu is not updated as Background
        time += state.getElapsedTime();
    }

    /**
     * Draws the objects
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(600, 0, 200, 600);

        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString("Menu", 660, 40);
        g.setFont(g.getFont().deriveFont(15f));
        g.drawString("Cities: " + state.getCities(), 620, 80);
        g.drawString("Money: " + state.getMoney(), 620, 110);
        g.drawString("Score: " + state.getScore(), 620, 140);
        g.drawString("High Score: " + state.getHighScore(), 620, 170);
        g.drawString("Time: " + (int)time + "sec", 620, 200);
    }
}
