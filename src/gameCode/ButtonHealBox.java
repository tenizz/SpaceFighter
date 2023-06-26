/**
 * This class, ButtonHealBox, is a class of a button for the Heal Box Button that will give cites for the user.
 * Each purchase will increase the price.
 *
 * @author Anuar Tenizbayev
 * @version April 24, 2023
 */
package gameCode;

import java.awt.*;

public class ButtonHealBox extends GameObject{
    private GameState state;
    private Control control;
    private int counter;

    public ButtonHealBox(Control control, GameState state){
        super();
        this.control = control;
        this.state = state;
        counter = 1;
    }
    /**
     * Updates objects frames
     *
     * @param elapsedTime time which is required for the update
     */
    @Override
    public void update(double elapsedTime) {
        // Button of the Heal Box is not updated
    }

    /**
     * Draws the objects of Satellite button
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(620, 430, 50, 50); // Border zone.
        drawCenteredImage(g, control.getImage("heal-box.png"), 645, 455, 0.15);
        g.drawString("Cost: " + state.getHealBoxCost(), 680, 450);
        g.drawString("+100 Cities", 680, 470);
    }

    /**
     * Consumes the click if it was in the region of the Ammo object button. It creates the Satellite object
     * and allows user to place it in the game.
     *
     * @return True or False. True if clicked was consumed.
     */
    @Override
    public boolean consumeClick() {
        // If clicked on the zone of Rocket Station button creates Satellite object and consumes click
        if ((state.getClickedMousePosition().getX() <= 670 && state.getClickedMousePosition().getX() >= 620) && (state.getClickedMousePosition().getY() <= 480 && state.getClickedMousePosition().getY() >= 430)
                && state.getMoney() >= state.getHealBoxCost()){
            state.setCities(state.getCities() + 100);
            state.setMoney(state.getMoney() - state.getHealBoxCost());
            counter++;
            state.setHealBoxCost(state.getHealBoxCost() * counter);
            return true;
        } else
            return false;
    }
}
