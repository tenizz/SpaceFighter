/**
 * This class is controls the whole game.
 *
 * @author Anuar Tenizbayev
 * @version April 4, 2023
 */
package gameCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener {
    private GameState state;
    private View view;
    private GameOver gameOverForm;
    private Path path;
    private TreeMap<String, BufferedImage> map = new TreeMap<String, BufferedImage>();
    private boolean mousePressed;
    private boolean gameIsOver;
    private boolean gameOverFormIsDrawn;

    /**
     * SwitchingUtilities in the Constructor
     *
     */
    public Control(){
        SwingUtilities.invokeLater(this);
    }

    /**
     * Overriding by run method
     *
     */
    @Override
    public void run() {
        state = new GameState(); // Assigning Game state
        // Loading Path
        try {
            // Loads the Path
            Scanner pathFile = new Scanner(new File("path_01.txt"));
            Path points = new Path();
            pathFile.nextInt();
            while (pathFile.hasNext()){
                points.addPoint(pathFile.nextInt(), pathFile.nextInt());
            }
            pathFile.close();
            path = points;
        } catch (IOException ex){
            // In case file is not found the IOException will be called out
            System.out.println("The Path file cannot be loaded");
        }
        view  = new View(this, state); // Assigning view

        view.addMouseListener(this); // Listens for Mouse actions.
        view.addMouseMotionListener(this); // Listens for Mouse motion.
        mousePressed = false; // Sets that mouse is not pressed by the default.

        state.startFrame();  // Prepares the creation of the 'next' frame
        // Add objects to the list
        state.addGameObject(new Background(this, state));
        state.addGameObject(new Menu(this, state));
        state.addGameObject(new ButtonSatellite(this, state));
        state.addGameObject(new ButtonBombStation(this, state));
        state.addGameObject(new ButtonHealBox(this, state));
        state.addGameObject(new Generator(this, state));
        state.finishFrame();    // Mark the next frame as ready

        view.repaint();    // Draw it.
        gameOverFormIsDrawn = false;

        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
    }

    /**
     * Getter method gets the path.
     *
     * @return Path
     */
    public Path getPath(){
        return path;
    }

    /**
     * Set the condition of the Game, if the game is over or not
     *
     * @param gameIsOver True of False
     */
    public void setGameIsOver (boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    /**
     * Loads image from the given file name.
     *
     * @param filename name of the wanted file
     * @return BufferedImage
     */
    private BufferedImage loadImage (String filename){
        // Image loading.
        try {
            return javax.imageio.ImageIO.read(new File(filename));
        }
        catch (IOException e)
        {
            System.out.println("Image was not found");
            return null;
        }
    }

    /**
     * This function loads the image only if it is not already loaded. It uses map to store the images.
     *
     * @param filename name of the image file that wants to be loaded.
     * @return Image from the given file.
     */
    public BufferedImage getImage (String filename){
        // Image loading.
        try {
            // Checks if map contains given filename.
            if (!map.containsKey(filename)){
                BufferedImage image = javax.imageio.ImageIO.read(new File(filename));
                map.put(filename, image);
                System.out.println("Image '" + filename + "' was uploaded."); // Check that image is uploaded once
            }

            return map.get(filename);
        }
        catch (IOException e)
        {
            System.out.println("Image was not found");
            return null;
        }
    }

    /**
     * Action Listener Method. Updates the frames.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        state.startFrame();

        // If number of cities is below zero, gameIsOver = true.
        gameIsOver = state.getCities() <= 0;

        if (!gameIsOver){
            for (GameObject a : state.getCurrentObjects())
                a.update(state.getElapsedTime());

            if (mousePressed){
                for (GameObject a : state.getCurrentObjects()){
                    if (a.consumeClick()){
                        break;
                    }
                }
            }
            mousePressed = false;
        } else if (gameIsOver && !gameOverFormIsDrawn){
            gameOverForm = new GameOver(this, state);
            gameOverFormIsDrawn = true;
        }

        state.finishFrame();
        view.repaint();
    }

    /**
     * Performs ... when mouse is clicked.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Performs ... when mouse is pressed but not released.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Sets position of the Click and sends it to Game State when mouse is released.
     * Also, makes mousePressed true.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        state.setClickedMousePosition(new Point(e.getX(), e.getY()));
        mousePressed = true;
    }

    /**
     * Performs ... when mouse is entered.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Performs ... when mouse is exits the form.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Sends the coordinates of the cursor to the Game State when mouse drags.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        state.setCurrentMousePosition(new Point(e.getX(), e.getY()));
    }

    /**
     * Sends the coordinates of the cursor to the Game State when mouse is moving.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        state.setCurrentMousePosition(new Point(e.getX(), e.getY()));
    }
}