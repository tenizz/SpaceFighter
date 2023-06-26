/**
 * GameState class creates ArrayList of frames which are used to store game objects
 * and deal with the in different frames.
 *
 * @author Anuar Tenizbayev
 * @version April 4, 2023
 */
package gameCode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private List<GameObject> currentFrameObjects;
    private List<GameObject> nextFrameObjects;
    private int cities;
    private int money;
    private int score;
    private int bombStationCost;
    private int satelliteCost;
    private int healBoxCost;
    private Point currentMousePosition;
    private Point clickedMousePosition;
    private long lastFrameStartTime;
    private double elapsedTime;
    private int highScore;

    /**
     * Constructor that creates new ArrayList of Game Objects
     * Initializes the number of cities and money.
     */
    public GameState()
    {
        currentFrameObjects = new ArrayList<GameObject>();
        cities = 100; // Real Game: 200, Testing: 10
        money = 1000; // Real Game: 100, Testing: 10000
        bombStationCost = 500;
        satelliteCost = 100;
        healBoxCost = 1000;
        lastFrameStartTime = System.currentTimeMillis();
    }

    /**
     * Gets Current Frames of the Object from ArrayList
     *
     * @return List of frames of the object
     */
    public List<GameObject> getCurrentObjects ()
    {
        return currentFrameObjects;
    }

    /**
     * Gets the number of cities.
     *
     * @return number of cities
     */
    public int getCities(){
        return cities;
    }

    /**
     * Sets the number of cities.
     *
     * @param cities number of cities to be set.
     */
    public void setCities(int cities) {
        this.cities = cities;
    }

    /**
     * Gets the number of available money.
     *
     * @return number of money.
     */
    public int getMoney(){
        return money;
    }

    /**
     * Sets the amount of money.
     *
     * @param money the amount of money to be set.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets the score.
     *
     * @return Score.
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets the Score.
     *
     * @param score Score of the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the cost of Bomb Station.
     *
     * @return Price of Bomb Station.
     */
    public int getBombStationCost(){
        return bombStationCost;
    }

    /**
     * Sets the price from the Bomb Station.
     *
     * @param bombCost How much it costs to buy BombStation
     */
    public void setBombStationCost(int bombCost) {
        this.bombStationCost = bombCost;
    }

    /**
     * Gets the cost of Satellite Station.
     *
     * @return Price of Satellite Station.
     */
    public int getSatelliteCost(){
        return satelliteCost;
    }

    /**
     * Sets the price from the Satellite Station.
     *
     * @param satelliteCost How much it costs to buy Satellite Station
     */
    public void setSatelliteCost(int satelliteCost) {
        this.satelliteCost = satelliteCost;
    }

    /**
     * Gets the cost of Heal Box.
     *
     * @return Price of Satellite Station.
     */
    public int getHealBoxCost(){
        return healBoxCost;
    }

    /**
     * Sets the price from the Heal Box.
     *
     * @param healBoxCost How much it costs to buy a Heal Box
     */
    public void setHealBoxCost(int healBoxCost) {
        this.healBoxCost = healBoxCost;
    }

    /**
     * Gets the Current Position of the Mouse Cursor.
     *
     * @return Coordinates of Mouse Position.
     */
    public Point getCurrentMousePosition(){
        return currentMousePosition;
    }

    /**
     * Sets the position of the Mouse Cursor.
     *
     * @param currentMousePosition Coordinates of the Cursor.
     */
    public void setCurrentMousePosition(Point currentMousePosition) {
        this.currentMousePosition = currentMousePosition;
        //System.out.println(this.currentMousePosition);
    }

    /**
     * Gets the Clicked Position of the Mouse Cursor.
     *
     * @return Coordinates of Clicked Mouse Position.
     */
    public Point getClickedMousePosition(){
        return clickedMousePosition;
    }

    /**
     * Sets the position of the Clicked Mouse Cursor.
     *
     * @param clickedMousePosition Coordinates of the Cursor.
     */
    public void setClickedMousePosition(Point clickedMousePosition){
        this.clickedMousePosition = clickedMousePosition;
    }

    /**
     * Accessor method that gets the Elapsed time.
     *
     * @return elapsed time
     */
    public double getElapsedTime(){
        return elapsedTime;
    }

    public void setHighScore(int newScore){
        highScore = newScore;
    }
    public int getHighScore(){
        return highScore;
    }

    /**
     * Method creates next list of frames and adds the current set to it
     */
    public void startFrame ()
    {
        long currentFrameStartTime = System.currentTimeMillis();
        elapsedTime = (currentFrameStartTime - lastFrameStartTime) / 1000.0;
        lastFrameStartTime = currentFrameStartTime;
        //System.out.println(elapsedTime);

        nextFrameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameObjects.addAll(currentFrameObjects);      // Add all the current ones to the new list.
    }

    /**
     * Method adds Game Object to the next frame list
     *
     * @param a Game Object
     */
    public void addGameObject (GameObject a)
    {
        nextFrameObjects.add(a);
    }

    /**
     * Method checks if Game Objects in current Frame are expired or not.
     * If they are expired it removes them from next Frames before setting them
     * to current Frames.
     */
    public void finishFrame ()
    {
        for (int i = 0; i < currentFrameObjects.size(); i++){
            if (currentFrameObjects.get(i).hasExpired)
                nextFrameObjects.remove(currentFrameObjects.get(i));
        }
        currentFrameObjects = nextFrameObjects;
        nextFrameObjects = null;  // This makes it clear there is only a current list now.
    }

    /**
     * Finds the closest Targetable game object.
     *
     * @param somePoint coordinates of the object
     * @return reference of the closest Targetable game object
     */
    public Targetable findNearest (Point somePoint){
        double distance = 1000.0;
        Targetable closestObject = null;
        for (GameObject a : this.getCurrentObjects()){
            if (a instanceof Targetable){
                if (((Targetable) a).getLocation().distance(somePoint) <= distance){
                    distance = ((Targetable) a).getLocation().distance(somePoint);
                    closestObject = (Targetable) a;
                }
            }
        }
        return closestObject;
    }

    /**
     * Returns the object of the Collision
     *
     * @param somePoint coordinates of the object
     * @return reference of the collided game object
     */
    public GameObject collideEvent (Point somePoint){
        double distance = 100.0;
        GameObject closestObject = null;
        for (GameObject a : this.getCurrentObjects()){
            if (a instanceof Targetable){
                if (((Targetable) a).getLocation().distance(somePoint) <= distance){
                    distance = ((Targetable) a).getLocation().distance(somePoint);
                    closestObject = a;
                }
            }
        }
        return closestObject;
    }

    /**
     * Returns all objects in radius 100
     *
     * @param somePoint coordinates of the object
     * @return reference of the collided game object
     */
    public ArrayList<GameObject> collideBombEvent (Point somePoint){
        double distance = 200.0;
        ArrayList<GameObject> closestObjects = new ArrayList<GameObject>();
        for (GameObject a : this.getCurrentObjects()){
            if (a instanceof Targetable){
                if (((Targetable) a).getLocation().distance(somePoint) <= distance){
                    closestObjects.add(a);
                }
            }
        }
        return closestObjects;
    }
}