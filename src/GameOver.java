
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver {

    JFrame window;         // The application window
    JPanel center;         // The area within the borders within the window
    Mode mode;             // The mode selected on the mode screen
    Difficulty difficulty; // The difficulty selected on the home screen
    Theme theme;           // The theme selected on the home screen
    JPanel results1;       // The first row in the center panel
    JPanel results2;       // The second row in the center panel
    JButton retry;         // Button to play again with the same settings
    JButton home;          // Button to return to the home screen

    // Constructor
    public GameOver(JFrame window, JPanel center, Mode mode, Difficulty difficulty, Theme theme) {
        this.window = window;
        this.center = center;
        this.mode = mode;
        this.difficulty = difficulty;
        this.theme = theme;
        results1 = new JPanel();
        results2 = new JPanel();
        retry = new JButton();
        home = new JButton();
        init();
    }

    // Initializer
    private void init() {
        addGameOverMessage(results1); // Add game over message to the first row of the game over screen
        addRetryButton(results2);     // Add retry button to the second row of the game over screen
        addHomeButton(results2);      // Add home button to the second row of the game over screen
        addPanels();                  // Change layout and add rows to the game over screen
        window.repaint();             // Refresh the game over screen layout
    }

    // Finisher
    private void fini() {
        center.remove(results1); // Remove first row and its components from the game over screen
        center.remove(results2); // Remove second row and its components from the game over screen
        window.repaint();        // Refresh the game over screen layout
    }

    // Add game over message to one row of the game over screen
    private void addGameOverMessage(JPanel panel) {
        panel.add(new JLabel("Game Over"));
    }

    // Add retry button to one row of the game over screen
    private void addRetryButton(JPanel panel) {

        retry.setText("Retry");
        retry.setFocusable(false);

        // Retry button is clicked
        retry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fini();
                if (mode == Mode.SURVIVAL) { // Switch to a new game screen with the same settings
                    @SuppressWarnings("unused")
                    SurvivalGame game = new SurvivalGame(window, center, difficulty, theme);
                } else { // mode = Mode.TIMED
                    @SuppressWarnings("unused")
                    TimedGame game = new TimedGame(window, center, difficulty, theme); 
                }
                
            }
        });
        
        panel.add(retry);
    }

    // Add home button to one row of the game over screen
    private void addHomeButton(JPanel panel) {

        home.setText("Home");
        home.setFocusable(false);
        
        // Home button is clicked
        home.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                fini();
                @SuppressWarnings("unused")
                Home home = new Home(window, center); // Switch to the home screen
            }
        });

        panel.add(home);
    }

    // Change layout and add rows to the game over screen
    private void addPanels() {
        center.setLayout(new GridLayout(2, 1)); // Change the game over screen layout
        center.add(results1);                              // Add the first row to the game over screen
        center.add(results2);                              // Add the second row to the game over screen
        window.revalidate();                               // Validate the game over screen layout
    }
}
