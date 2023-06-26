/**
 * This application allows user to draw a path and Save it to a .txt file. It has Save, Load, Clear buttons
 * which helps to do the correlating manipulations.
 *
 * @author Anuar Tenizbayev
 * @version March 28, 2023
 */
package gameCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PathEditor extends JPanel implements Runnable, MouseListener, ActionListener {
    BufferedImage background;
    JMenuItem loadItem, clearItem, saveItem;

    Path points = new Path();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new PathEditor());
    }

    /**
     *  Overrides the main method
     */
    @Override
    public void run() {
        JFrame frame = new JFrame("Path Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600, 600));
        this.setPreferredSize(new Dimension(600, 600));
        frame.setContentPane(this);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        loadItem = new JMenuItem("Load");
        fileMenu.add(loadItem);
        loadItem.addActionListener(this);

        clearItem = new JMenuItem("Clear");
        fileMenu.add(clearItem);
        clearItem.addActionListener(this);

        saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        saveItem.addActionListener(this);

        frame.setJMenuBar(menuBar);


        frame.pack();
        frame.setVisible(true);

        // Background image loading.
        try {
            background = javax.imageio.ImageIO.read(new File("background.png"));
        }
        catch (IOException e)
        {
            System.out.println("The background image was not found");
        }

        this.addMouseListener(this);


    }

    /**
     * Method to draw objects
     *
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        // Drawing image
        if (background != null)
            g.drawImage(background, 0,0, null);

        g.setColor(Color.RED);
        for (int i = 0; i < points.getNumOfPoints()-1; i++){
            g.drawLine((int) points.getPointAt(i).getX(),(int) points.getPointAt(i).getY(),(int) points.getPointAt(i+1).getX(),(int) points.getPointAt(i+1).getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * When user clicks this method creates and puts a point coordinates to the ArrayList.
     * Then it connects points by drawing a Line
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println(e.getX() + " " + e.getY());
        points.addPoint(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Action that needs to be done is determined here.
     * Load - loads text file and gets coordinates from it
     * Save - saves to a text file coordinates of the path
     * Clear - clears window
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            //System.out.println("Load");
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choose Path to load");
            int result = chooser.showOpenDialog(this);
            points = new Path();

            if (result == JFileChooser.APPROVE_OPTION){
                File chosenFile = chooser.getSelectedFile();
                try {
                    Scanner in = new Scanner(chosenFile);
                    // Loads the Path
                    in.nextInt();
                    while (in.hasNext()){
                        points.addPoint(in.nextInt(), in.nextInt());
                    }
                    in.close();
                } catch (IOException ex){
                    System.out.println("The file cannot be loaded");
                }
            }

            repaint();
        }
        if (e.getSource() == clearItem) {
            points = new Path();
            repaint();
        }
        if (e.getSource() == saveItem){
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save File");
            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION){
                File chosenFile = chooser.getSelectedFile();
                try {
                    PrintWriter fileOut = new PrintWriter(chosenFile);
                    fileOut.println(points); // Saves the Path
                    fileOut.close();
                } catch (IOException ex){
                    System.out.println("The file cannot be saved");
                }
            }
        }
    }
}


