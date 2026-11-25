
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver {

    protected JFrame window;           // The application window
    protected JPanel center;           // The area within the borders of the window
    protected Difficulty difficulty;   // The difficulty selected on the home screen
    protected String theme;            // The theme selected on the home screen
    protected JPanel gameOverPanel;    // Panel for game over
    protected JPanel performancePanel; // Panel for score or time
    protected JPanel buttonPanel;      // Panel for retry and home buttons
    protected JLabel gameOver;         // Game over text
    protected JLabel performance;      // Text label for score or time
    protected CustomButton retry;      // Retry button
    protected CustomButton home;       // Home button

    // Constructor
    public GameOver(JFrame window, JPanel center, Difficulty difficulty, String theme) {
        this.window = window;
        this.center = center;
        this.difficulty = difficulty;
        this.theme = theme;
        gameOverPanel = new JPanel();
        performancePanel = new JPanel();
        buttonPanel = new JPanel();
        gameOver = new JLabel();
        performance = new JLabel();
        retry = new CustomButton();
        home = new CustomButton();
        init();
    }

    // Initializer
    private void init() {
        createGameOverPanel(); // Create panel with game over text
        createButtonPanel();   // Create panel with retry and home buttons button
        addPanels();           // Add subpanels to center panel
        window.revalidate();   // Validate the window layout 
        window.repaint();      // Refresh the window layout
    }

    // Create panel with game over text
    private void createGameOverPanel() {
        gameOver.setText("Game Over");                         // Add text to label
        SustainabilityScramble.setFontAvenir(gameOver, 32.0f); // Set font to Avenir Regular 32 point
        gameOverPanel.add(gameOver);                                 // Add label to panel
    }

    // Create panel with retry and home buttons button
    private void createButtonPanel() {
        addRetryButton();
        addHomeButton();
    }
    private void addRetryButton() {
        retry.setText("Retry"); // Add text to button             
        buttonPanel.add(retry);       // Add button to panel 
    }
    private void addHomeButton() {

        home.setText("Home"); // Add text to button

        // Set home button behavior on click
        home.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                center.removeAll();                   // Remove all components from center panel
                @SuppressWarnings("unused")
                Home home = new Home(window, center); // Switch to the home screen
            }
        });

        home.addMouseListener(new MouseAdapter() {

            // Set home button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (home.isEnabled()) {
                    home.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    home.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    home.setFont(home.getFont().deriveFont(20.0f));   // Scale new font to 20 point
                }
            }
            
            // Set home button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (home.isEnabled()) {
                    home.setBackground(new Color(Colors.WHITE));          // Revert the button color to white
                    home.setFont(SustainabilityScramble.avenirRegular);   // Revert the font family to Avenir Regular
                    home.setFont(home.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });

        buttonPanel.add(home); // Add button to panel
    }
    
    // Add subpanels to center panel
    private void addPanels() {
        center.setLayout(new GridLayout(3, 1)); // Set center panel to 3 by 1 grid layout
        gameOverPanel.setLayout(new GridBagLayout());      // Set game over panel to grid bag layout
        center.add(gameOverPanel);                         // Add game over text to first row of game over screen
        center.add(performancePanel);                      // Add performance text to second row of game over screen
        center.add(buttonPanel);                           // Add retry and home buttons to third row of game over screen 
    }
}
