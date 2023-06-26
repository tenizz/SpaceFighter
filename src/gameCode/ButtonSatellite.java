/**
 * This class, Ammo (Ammunition), is a class of a button for the defensing objects that will protect the Earth.
 *
 * @author Anuar Tenizbayev
 * @version April 8, 2023
 */
package gameCode;

import java.awt.*;

public class ButtonSatellite extends GameObject{
    private GameState state;
    private Control control;

    public ButtonSatellite(Control control, GameState state){
        super();
        this.control = control;
        this.state = state;
    }
    /**
     * Updates objects frames
     *
     * @param elapsedTime time which is required for the update
     */
    @Override
    public void update(double elapsedTime) {
        // Button of the Satellite is not updated
    }

    /**
     * Draws the objects of Satellite button
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(620, 230, 50, 50); // Border zone.
        drawCenteredImage(g, control.getImage("satellite.png"), 645, 255, 0.1);
        g.drawString("Cost: " + state.getSatelliteCost(), 680, 250);
        g.drawString("CoolDown: 2 sec", 680, 270);
    }

    /**
     * Consumes the click if it was in the region of the Ammo object button. It creates the Satellite object
     * and allows user to place it in the game.
     *
     * @return True or False. True if clicked was consumed.
     */
    @Override
    public boolean consumeClick() {
        // If clicked on the zone of Satellite button creates Satellite object and consumes click
        if ((state.getClickedMousePosition().getX() <= 670 && state.getClickedMousePosition().getX() >= 620) && (state.getClickedMousePosition().getY() <= 280 && state.getClickedMousePosition().getY() >= 230)
                && state.getMoney() >= state.getSatelliteCost()){
            state.addGameObject(new Satellite(control, state));
            state.setMoney(state.getMoney() - state.getSatelliteCost());
            return true;
        } else
            return false;
    }
}
