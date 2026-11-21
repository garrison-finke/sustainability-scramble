
import javax.swing.*;
import java.awt.event.*;

public class SurvivalGame extends Game {

    private JLabel scoreLabel; // Text label for score counter
    private JLabel livesLabel; // Text label for lives counter
    private int score;         // Score counter
    private int lives;         // Lives counter

    // Constructor
    public SurvivalGame(JFrame window, JPanel center, Difficulty difficulty, Theme theme) {
        super(window, center, difficulty, theme);
        scoreLabel = new JLabel();
        livesLabel = new JLabel();
        score = 0; 
        lives = parseDifficulty(difficulty);
        init();
    }

    // Initializer
	private void init() {
        addScoreLabel(game1_1);  // Add score label to top left of game screen
        addLivesLabel(game1_2);  // Add lives label to top right of game screen
        addFirstScramble(game2); // Add scramble to second row of game screen
        addInputFields(game3);   // Add text field and submit button to third row of game screen
        setSubmitButton();       // Set the behavior of the submit button
        addFeedback(game4);      // Add the feedback to the fourth row of the game screen
        addPanels();             // Change layout and add rows to the game screen
        window.repaint();        // Refresh the game screen layout
	}

    // Set the behavior of the submit button
    private void setSubmitButton() {
        // Submit button is clicked
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guess = textField.getText().strip().toLowerCase(); // Process user input
                textField.setText("");                                  // Clear the text field
                if (guess.equals(word)) { // Correct answer
                    score = score + 1;                                    // Increment score
                    scoreLabel.setText("Score: " + score);                // Update score display
                } else {                  // Incorrect answer
                    lives = lives - 1;                                    // Decrement lives
                    livesLabel.setText("Lives: " + lives);                // Update lives display
                }

                if (lives == 0) { // Game over
                    fini();
                    @SuppressWarnings("unused")
                    GameOver gameover = new GameOver(window, center, Mode.SURVIVAL, difficulty, theme); // Switch to the game over screen
                } else {
                    showNextScramble();
                }
            }
        });
    }

    // Add score to one row of the game screen
    private void addScoreLabel(JPanel panel) {
        scoreLabel.setText("Score: " + score);
        panel.add(scoreLabel);
    }

    // Add lives to one row of the game screen
    private void addLivesLabel(JPanel panel) {
        livesLabel.setText("Lives: " + lives);
        panel.add(livesLabel);
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
