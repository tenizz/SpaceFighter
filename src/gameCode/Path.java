/**
 * This class creates Path by drawing Line within the given points and store it in the ArrayList.
 *
 * @author Anuar Tenizbayev
 * @version March 28, 2023
 */
package gameCode;

import java.awt.*;
import java.util.ArrayList;

public class Path {

    // Instance variables
    ArrayList<Point> path;
    /**
     * Constructor that creates new ArrayList of Point objects
     */
    public Path(){
        path = new ArrayList<Point>();
    }

    /**
     * Gets the size of the ArrayList
     *
     * @return number of elements in the ArrayList
     */
    public int getNumOfPoints() {
        return path.size();
    }

    /**
     * gets Point at some position from the ArrayList
     *
     * @param n position of the needed Point in the ArrayList
     * @return Point at position n
     */
    public Point getPointAt(int n) {
        return path.get(n);
    }

    /**
     * Adds a Point to the ArrayList
     *
     * @param x x coordinate of the Point
     * @param y y coordinate of the Point
     */
    public void addPoint(int x, int y){
        path.add(new Point(x, y));
    }

    /**
     * Draws Path by going over ArrayList Points and connects them by drawing a Line
     *
     * @param g the <code>Graphics</code> context in which to paint
     * @param pathToDraw ArrayList of Points
     */
    public void drawPath(Graphics g, ArrayList<Point> pathToDraw){
        // Draw all points
        for (int pos = 0; pos < path.size(); pos++){
            g.drawLine((int) path.get(pos).getX(),(int) path.get(pos).getY(),(int) path.get(pos+1).getX(),(int) path.get(pos+1).getY());
        }
    }

    /**
     * Draws a red Path
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    public void draw(Graphics g){
        // Draws the Path
        g.setColor(Color.RED);
        for (int pos = 0; pos < path.size()-1; pos++){
            g.drawLine((int) path.get(pos).getX(),(int) path.get(pos).getY(),(int) path.get(pos+1).getX(),(int) path.get(pos+1).getY());
        }
    }

    /**
     * toString() method which is called when object is printed out. Gets the number of all elements in the ArrayList.
     * Then, lists all Point coordinates which are in the ArrayList
     *
     * @return size of the ArrayList and its content separated by spacing.
     */
    public String toString(){
        String count = String.valueOf(path.size()) + "\n";
        String resultText = count;
        for (int pos = 0; pos < path.size(); pos++) {
            resultText = resultText + String.valueOf((int) path.get(pos).getX()) +  " " + String.valueOf((int) path.get(pos).getY()) + "\n";
        }
        return resultText;
    }

    /**
     * Given a percentage between 0% and 100%, this method calculates
     * the location along the path that is exactly this percentage
     * along the path. The location is returned in a Point object
     * (integer x and y), and the location is a screen coordinate.
     *
     * If the percentage is less than 0%, the starting position is
     * returned. If the percentage is greater than 100%, the final
     * position is returned.
     *
     * Callers must not change the x or y coordinates of any returned
     * point object (or the caller could be changing the path).
     *
     * @param percentTraveled a distance along the path
     * @return  the screen coordinate of this position along the path
     */
    public Point convertToCoordinates(double percentTraveled){
        int resultX, resultY;
        int startX, startY, endX, endY;

        if (percentTraveled < 0){
            return new Point((int) path.get(0).getX(), (int) path.get(0).getY());
        }
        if (percentTraveled >= 1.0){
            return new Point((int) path.get(path.size()-1).getX(), (int) path.get(path.size()-1).getY());
        }

        int segment = (int) (path.size() * percentTraveled);
        if (segment + 1 >= path.size()){
            return new Point((int) path.get(path.size()-1).getX(), (int) path.get(path.size()-1).getY());
        }
        double percentOfSegment = path.size() * percentTraveled - segment;
        startX = (int) path.get(segment).getX();
        startY = (int) path.get(segment).getY();
        endX = (int) path.get(segment+1).getX();
        endY = (int) path.get(segment+1).getY();

        int deltaX = (endX - startX);
        int deltaY = (endY - startY);
        double segmentLength = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        int pos = (int) (segmentLength * percentOfSegment);

        resultX = (int) ((1.0 - percentOfSegment) * startX + percentOfSegment * endX);
        resultY = (int) ((1.0 - percentOfSegment) * startY + percentOfSegment * endY);

        Point currentPoint = new Point(resultX, resultY);

        return currentPoint;
    }
}
