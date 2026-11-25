
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SurvivalGame extends Game {

    private JLabel scoreLabel; // Text label for score counter
    private JLabel livesLabel; // Text label for lives counter
    private int score;         // Score counter
    private int lives;         // Lives counter


    // Constructor
    public SurvivalGame(JFrame window, JPanel center, Difficulty difficulty, String theme) {
        super(window, center, difficulty, theme);
        scoreLabel = new JLabel();
        livesLabel = new JLabel();
        score = 0; 
        lives = parseDifficulty(difficulty);
        init();
    }

    // Initializer
	private void init() {
        createScorePanel();  // Add score label to top left of game screen
        createLivesPanel();  // Add lives label to top right of game screen
        setSubmitButton();   // Set behavior of submit button
        window.revalidate(); // Validate the game screen layout 
        window.repaint();    // Refresh the game screen layout
	}

    // Set the behavior of the submit button
    private void setSubmitButton() {
        // Submit button is clicked
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guess = textField.getText().strip().toLowerCase(); // Process user input
                textField.setText("");                                  // Clear the text field
                if (guess.equals(currentWord)) { // Correct answer
                    score = score + 1;                                // Increment score
                    scoreLabel.setText("Score: " + score);            // Update score display
                    feedback.setText("Correct!");               // Update feedback
                    feedback.setForeground(new Color(0x00C800)); // Set feedback color to green
                    previousWord.setText(currentWord);                // Show solution 
                    definition.setText("<html><div style='width: 500px; text-align: center;'>" + wordBank.getDefinition(currentWord) + "</div></html>"); // Show definition
                    SustainabilityScramble.setFontAvenir(definition, 24.0f); // Set font to Avenir Regular 24 point
                    definition.revalidate();
                    definition.repaint();
                } else {                         // Incorrect answer
                    lives = lives - 1;                                 // Decrement lives
                    livesLabel.setText("Lives: " + lives);             // Update lives display
                    feedback.setText("Incorrect");               // Update feedback
                    feedback.setForeground(Color.RED);                 // Set color to red 
                    previousWord.setText("Solution: " + currentWord); // Show solution
                    definition.setText("<html><div style='width: 500px; text-align: center;'>" + wordBank.getDefinition(currentWord) + "</div></html>"); // Show definition
                    SustainabilityScramble.setFontAvenir(definition, 24.0f); // Set font to Avenir Regular 24 point
                    definition.revalidate();
                    definition.repaint();
                }

                if (lives == 0) { // Game over
                    center.removeAll(); // Remove all components from center panel
                    @SuppressWarnings("unused")
                    SurvivalGameOver gameover = new SurvivalGameOver(window, center, difficulty, theme, score); // Switch to the game over screen
                } else {
                    showNextScramble();
                }
            }
        });

        submit.addMouseListener(new MouseAdapter() {

            // Set credits button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (submit.isEnabled()) {
                    submit.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    submit.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    submit.setFont(submit.getFont().deriveFont(20.0f));  // Scale new font to 20 point
                }
            }
            
            // Set credits button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (submit.isEnabled()) {
                    submit.setBackground(new Color(Colors.WHITE));            // Revert the button color to white
                    submit.setFont(SustainabilityScramble.avenirRegular);     // Revert the font family to Avenir Regular
                    submit.setFont(submit.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });
    }

    // Add score to one row of the game screen
    private void createScorePanel() {
        statusPanelLeft.setLayout(new GridBagLayout());                // Set panel to grid bag layout
        scoreLabel.setText("Score: " + score);                         // Add text to label
        SustainabilityScramble.setFontAvenir(scoreLabel, 24.0f); // Set font to Avenir Regular 24 point
        statusPanelLeft.add(scoreLabel);                               // Add label to panel
    }

    // Add lives to one row of the game screen
    private void createLivesPanel() {
        statusPanelRight.setLayout(new GridBagLayout());               // Set panel to grid bag layout
        livesLabel.setText("Lives: " + lives);                         // Add text to label
        SustainabilityScramble.setFontAvenir(livesLabel, 24.0f); // Set font to Avenir Regular 24 point
        statusPanelRight.add(livesLabel);                              // Add label to panel
    }

    // Set the number of lives based on the selected difficulty
    private int parseDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            return 5; // Easy: 5 lives
        } else if (difficulty == Difficulty.MEDIUM) {
            return 3; // Medium: 3 lives
        } else { // difficulty == Difficulty.HARD
            return 1; // Hard: 1 life
        }
    }
}
