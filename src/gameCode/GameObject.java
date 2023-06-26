/**
 * GameObject is a super class that implements the Animatable interface.
 * It indicates if Game object has Expired or not.
 *
 * @author Anuar Tenizbayev
 * @version April 8, 2023
 */
package gameCode;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract class GameObject implements Animatable {
    protected boolean hasExpired;

    /**
     * By the default Game Objects are not expired.
     */
    public GameObject(){
        this.hasExpired = false;
    }

    /**
     * Gets the value of hasExpired.
     *
     * @return True or False.
     */
    public boolean getHasExpired(){
        return hasExpired;
    }

    /**
     * Consumes click if it was clicked and returns a boolean expression.
     * By the default it is set to false.
     *
     * @return True or False
     */
    public boolean consumeClick(){
        return false;
    }

    /**
     * This method helps to draw images getting the central points rather than top-left
     *
     * @param k the <code>Graphics</code> context in which to paint
     * @param image image that needs to be drawn
     * @param x position X of the image
     * @param y position Y of the image
     * @param scale Scale of the image
     */
    protected void drawCenteredImage (Graphics k, BufferedImage image, int x, int
            y, double scale)
    {
        int width = (int)(image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        int nx = x - width/2;
        int ny = y - height/2;
        k.drawImage(image, nx, ny, width, height, null);
    }
}
