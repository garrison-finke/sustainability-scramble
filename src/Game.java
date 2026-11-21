
import javax.swing.*;
import java.awt.*;

public class Game {
	
    protected JFrame window;         // The application window
	protected JPanel center;         // The area within the borders within the window
    protected Difficulty difficulty; // The difficulty selected on the home screen
    protected Theme theme;           // The theme selected on the home screen
    protected JPanel game1_1;        // The left side of the first row
    protected JPanel game1_2;        // The right side of the second row
    protected JPanel game1;          // The first row in the center panel
	protected JPanel game2;          // The second row in the center panel
	protected JPanel game3;          // The third row in the center panel
    protected JPanel game4;          // The fourth row in the center panel
    protected JLabel scramble;       // Text label for the scrambled word
    protected JLabel feedback;       // Text label for the feedback
    protected JTextField textField;  // Area where the player enters their answer
    protected JButton submit;        // Button for submitting answer
    protected WordBank wordBank;     // The bank of possible words
    protected String word;           // The current word
	
    // Constructor
	public Game(JFrame window, JPanel center, Difficulty difficulty, Theme theme) {
		this.window = window;
		this.center = center;
        this.difficulty = difficulty;
        this.theme = theme;
        game1_1 = new JPanel();
        game1_2 = new JPanel();
		game1 = new JPanel();
        game2 = new JPanel();
        game3 = new JPanel();
        game4 = new JPanel();
        scramble = new JLabel();
        feedback = new JLabel();
        textField = new JTextField();
        submit = new JButton();
        wordBank = new WordBank();
	}
    
    // Finisher
    protected void fini() {
        center.remove(game1);  // Remove first row and its components from the home screen
        center.remove(game2);  // Remove second row and its components from the home screen
        center.remove(game3);  // Remove third row and its components from the home screen
        center.remove(game4);  // Remove fourth row and its components from the home screen
        window.repaint();      // Refresh the home screen layout
    }

    // Add scramble to one row of the game screen
    protected void addFirstScramble(JPanel panel) {
        wordBank.readWords(theme); // Populate the word bank with the words corresponding to the selected theme
        showNextScramble();
        panel.add(scramble);
    }
    protected void showNextScramble() {
        word = wordBank.getNext();                  // Get a word from the word bank
        String scrambled = wordBank.scramble(word); // Scramble the word
        scramble.setText(scrambled);                // Show the scrambled word
    }

    // Add feedback to one row of the game screen
    protected void addFeedback(JPanel panel) {
        panel.add(feedback);
    }

    // Add the text field to one row of the game screen
    // Add the fields for user input to one row of the game screen
    protected void addInputFields(JPanel panel) {
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
        panel.add(submit);
    }

    // Change layout and add rows to the home screen
    protected void addPanels() {
        game1.setLayout(new GridLayout(1, 2));  // Change the first row layout
        game1.add(game1_1);                                // Add the left side to the first row
        game1.add(game1_2);                                // Add the right side to the first row
        center.setLayout(new GridLayout(4, 1)); // Change the game screen layout
        center.add(game1);                                 // Add the first row to the game screen
        center.add(game2);                                 // Add the second row to the game screen
        center.add(game3);                                 // Add the third row to the game screen
        center.add(game4);                                 // Add the fourth row to the game screen
        window.revalidate();                               // Validate the game screen layout
    }
}
