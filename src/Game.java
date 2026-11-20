
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
	
    JFrame window;        // The application window
	JPanel center;        // The area within the borders within the window
    Theme theme;          // The theme selected on the home screen
	JPanel game1;         // The first row in the center panel
    JPanel game1_1;       // The left side of the first row
    JPanel game1_2;       // The right side of the second row
	JPanel game2;         // The second row in the center panel
	JPanel game3;         // The third row in the center panel
    JLabel livesLabel;    // Text label for the remaining number of lives
    JLabel scoreLabel;    // Text label for the current score
    JLabel scramble;      // Text label for the scrambled word
    JTextField textField; // Area where the player enters their answer
    JButton submit;       // Button for submitting answer
    String word;          // The current word
    WordBank wordBank;    // The bank of possible words
	int score;            // The current score
	int lives;            // The number of lives currently remaining
	
    // Constructor
	public Game(JFrame window, JPanel center, Theme theme) {
		this.window = window;
		this.center = center;
        this.theme = theme;
		game1 = new JPanel();
        game1_1 = new JPanel();
        game1_2 = new JPanel();
        game2 = new JPanel();
        game3 = new JPanel();
        livesLabel = new JLabel();
        scoreLabel = new JLabel();
        scramble = new JLabel();
        textField = new JTextField();
        submit = new JButton();
        wordBank = new WordBank();
		this.score = 0;
        this.lives = 3; // TODO: Parse the correct number of lives or jumbles from the selected difficulty
        /*
        if (survival.isSelected()) {
            if (easy.isSelected()) {
                lives = 5;
            }
            if (medium.isSelected()) {
                lives = 3;
            }
            if (hard.isSelected()) {
                lives = 1;
            }
        }
        */
        
		init();
	}
	
    // Initializer
	private void init() {
        addScoreLabel(game1_1);  // Add score label to top left of game screen
        addLivesLabel(game1_2);  // Add lives label to top right of game screen
        addFirstScramble(game2); // Add scramble to second row of game screen
        addPlayerInput(game3);   // Add text field and submit button to third row of game screen
        addPanels();             // Change layout and add rows to the game screen
        window.repaint();        // Refresh the game screen layout
	}
    
    // Finisher
    private void fini() {
        center.remove(game1);  // Remove first row and its components from the home screen
        center.remove(game2);  // Remove second row and its components from the home screen
        center.remove(game3);  // Remove third row and its components from the home screen
        window.repaint();      // Refresh the home screen layout
    }

    // Add player status to one row of the game screen
    private void addLivesLabel(JPanel panel) {
        livesLabel.setText("Lives: " + lives);
        panel.add(livesLabel);
    }
    private void addScoreLabel(JPanel panel) {
        scoreLabel.setText("Score: " + score);
        panel.add(scoreLabel);
    }

    // Add scramble to one row of the game screen
    private void addFirstScramble(JPanel panel) {
        wordBank.readWords(theme); // Populate the word bank with the words corresponding to the selected theme
        showNextScramble();
        panel.add(scramble);
    }
    private void showNextScramble() {
        word = wordBank.getNext();                  // Get a word from the word bank
        String scrambled = wordBank.scramble(word); // Scramble the word
        scramble.setText(scrambled);                // Show the scrambled word
    }

    // Add player input to one row of the game screen
    private void addPlayerInput(JPanel panel) {
        addTextField(panel);
        addSubmitButton(panel);
    }
    private void addTextField(JPanel panel) {
        textField.setColumns(30);
        textField.setEnabled(true);
        panel.add(textField);
    }
    private void addSubmitButton(JPanel panel) {
        
        submit.setText("Submit");
        submit.setFocusable(false);

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
                    GameOver gameover = new GameOver(window, center, theme); // Switch to the game over screen
                } else {          // Game continues
                    showNextScramble();
                }
            }
        });

        panel.add(submit);
    }

    // Change layout and add rows to the home screen
    private void addPanels() {
        game1.setLayout(new GridLayout(1, 2));  // Change the first row layout
        game1.add(game1_1);                                // Add the left side to the first row
        game1.add(game1_2);                                // Add the right side to the first row
        center.setLayout(new GridLayout(3, 1)); // Change the game screen layout
        center.add(game1);                                 // Add the first row to the game screen
        center.add(game2);                                 // Add the second row to the game screen
        center.add(game3);                                 // Add the third row to the game screen
        window.revalidate();                               // Validate the game screen layout
    }
}