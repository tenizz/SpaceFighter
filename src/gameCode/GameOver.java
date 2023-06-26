/**
 * This class contains the visuals of the Game Over Window. GUI Form
 *
 * @author Anuar Tenizbayev
 * @version April 22, 2023
 */
package gameCode;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class GameOver extends JPanel {
    private GameState state;
    private Control control;
    protected int highScore;

    /**
     * Constructor that creates GUI form of the Game Over Page.
     *
     * @param control Control Class
     * @param state GameState Class
     */
    public GameOver(Control control, GameState state){
        this.control = control;
        this.state = state;

        // Building the GUI
        JFrame frame = new JFrame("Game Over");
        this.setMinimumSize(new Dimension(600, 300));
        this.setPreferredSize(new Dimension(600, 300));
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);

        try {
            File highscoreFile = new File("highscore.txt");
            // Loads the File
            Scanner scoreFile = new Scanner(highscoreFile);
            if (scoreFile.hasNextInt()){
                highScore = scoreFile.nextInt();
                state.setHighScore(highScore);
            }
            scoreFile.close();

            PrintWriter fileOut = new PrintWriter(highscoreFile);
            if (state.getScore() > highScore){
                fileOut.print(state.getScore());
            }
            fileOut.close();
        } catch (IOException ex){
            // In case file is not found the IOException will be called out
            System.out.println("The file cannot be loaded");
        }
    }

    /**
     * Game Over Message.
     *
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 300);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 36));
        g.drawString("Game Over", 200, 50);
        g.drawString("Your Score: " + state.getScore(), 200, 150);
        g.drawString("High Score: " + highScore, 200, 250);

    }
}
