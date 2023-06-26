/**
 * Interface that forms methods to get the coordinates.
 *
 * @author Anuar Tenizbayev
 * @version April 16, 2023
 */
package gameCode;

import java.awt.*;

public interface Targetable {
    /**
     * Gets the location of the objects
     *
     * @return Point variable with coordinates
     */
    public Point getLocation ();
}