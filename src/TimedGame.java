
import javax.swing.*;
import java.awt.event.*;

public class TimedGame extends Game {

    private Timer timer;                    // Timer
    private JLabel timerLabel;              // Text label for the elapsed time
    private JLabel scramblesRemainingLabel; // Text label for scrambles remaining counter
    private JButton giveUp;                 // Button to return to the home screen
    private int elapsedTime;                // Time in seconds since the timer was started
    private int scramblesRemaining;         // Scrambles remaining counter

    // Constructor
    public TimedGame(JFrame window, JPanel center, Difficulty difficulty, Theme theme) {
        super(window, center, difficulty, theme);
        timerLabel = new JLabel();
        scramblesRemainingLabel = new JLabel();
        giveUp = new JButton();
        scramblesRemaining = parseDifficulty(difficulty);
        elapsedTime = 0;
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime = elapsedTime + 1;              // Increment elapsed time
                timerLabel.setText("Time: " + elapsedTime); // Update elapsed time display
            }
        });
        init();
    }

    // Initializer
	private void init() {
        addTimer(game1_1);              // Add timer to top left of the screen
        addScramblesRemaining(game1_2); // Add scrambles remaining count to top right of game screen
        addGiveUpButton(game1_2);       // Add give up button to top right of game screen
        addFirstScramble(game2);        // Add scramble to second row of game screen
        addInputFields(game3);          // Add text field and submit button to third row of game screen
        setSubmitButton();              // Set the behavior of the submit button 
        addFeedback(game4);             // Add the feedback to the fourth row of the game screen
        addPanels();                    // Change layout and add rows to the game screen
        window.repaint();               // Refresh the game screen layout
        timer.start();                  // Start the timer
	}

    // Set the behavior of the submit button
    private void setSubmitButton() {
        // Submit button is clicked
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guess = textField.getText().strip().toLowerCase();                          // Process user input
                // Correct answer
                if (guess.equals(word)) { 
                    scramblesRemaining = scramblesRemaining - 1;                                   // Decrement scrambles remaining
                    scramblesRemainingLabel.setText("Scrambles Remaining: " + scramblesRemaining); // Update score display
                    textField.setText("");                                                      // Clear the text field
                    showNextScramble();
                }

                // Game over
                if (scramblesRemaining == 0) { 
                    timer.stop();                                                                    // Stop the timer
                    fini();
                    @SuppressWarnings("unused")
                    GameOver gameover = new GameOver(window, center, Mode.TIMED, difficulty, theme); // Switch to the game over screen
                }
            }
        });
    }

    // Add the timer to the one row of the game screen
    private void addTimer(JPanel panel) {
        timerLabel.setText("Time: " + elapsedTime);
        panel.add(timerLabel);
    }

    // Add the number of scrambles remaining to one row of the game screen
    private void addScramblesRemaining(JPanel panel) {
        scramblesRemainingLabel.setText("Scrambles Remaining: " + scramblesRemaining);
        panel.add(scramblesRemainingLabel);
    }
    
    // Add a button to exit to the home screen to the game screen
    private void addGiveUpButton(JPanel panel) {

        giveUp.setText("Give Up");
        giveUp.setFocusable(false);

        // Give up button is clicked
        giveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fini();
                @SuppressWarnings("unused")
                Home home = new Home(window, center); // Switch to the home screen
            }
        });

        panel.add(giveUp);
    }

    // Set the number of scrambles based on the selected difficulty
    private int parseDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            return 5;  // Easy: 5 scrambles
        } else if (difficulty == Difficulty.MEDIUM) {
            return 10; // Medium: 10 scrambles
        } else { // difficulty == Difficulty.HARD
            return 20; // Hard: 20 scrambles
        }
    }
}
