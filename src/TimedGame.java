
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimedGame extends Game {

    private Timer timer;                    // Timer
    private JLabel timerLabel;              // Text label for the elapsed time
    private JPanel scramblesRemainingPanel; // Panel for scrambles remaining located at top of top right of center window
    private JPanel helpPanel;               // Panel for hints and give up button located at bottom of top right of center window
    private JLabel scramblesRemainingLabel; // Scrambles remaining text
    private CustomButton giveUp;            // Give up button
    private CustomButton hints;             // Hints toggle
    private boolean hintsOn;                // Status of hints toggle
    private int elapsedTime;                // Time in seconds since the timer was started
    private int scramblesRemaining;         // Scrambles remaining counter

    // Constructor
    public TimedGame(JFrame window, JPanel center, Difficulty difficulty, String theme) {
        super(window, center, difficulty, theme);
        scramblesRemainingPanel = new JPanel();
        helpPanel = new JPanel();
        timerLabel = new JLabel();
        scramblesRemainingLabel = new JLabel();
        giveUp = new CustomButton();
        hints = new CustomButton();
        hintsOn = false;
        elapsedTime = 0;
        scramblesRemaining = parseDifficulty(difficulty);
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
        createTimerPanel();              // Add timer to top left of the screen
        createHelpPanel();
        setSubmitButton();               // Set the behavior of the submit button 
        window.revalidate();             // Validate the game screen layout
        window.repaint();                // Refresh the game screen layout
        timer.start();                   // Start the timer
	}

    // Set the behavior of the submit button
    private void setSubmitButton() {
        // Submit button is clicked
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guess = textField.getText().strip().toLowerCase();                          // Process user input
                // Correct answer
                if (guess.equals(currentWord)) { 
                    scramblesRemaining = scramblesRemaining - 1;                                   // Decrement scrambles remaining
                    scramblesRemainingLabel.setText("Scrambles Remaining: " + scramblesRemaining); // Update score display
                    textField.setText("");                                                      // Clear the text field
                    feedback.setText("Correct!");                                            // Update feedback
                    feedback.setForeground(new Color(0x00C800));                              // Set feedback color to green
                    previousWord.setText(currentWord);                                             // Show solution 
                    definition.setText(wordBank.getDefinition(currentWord));                       // Show definition
                    showNextScramble();
                } else {
                    feedback.setText("Incorrect");                                            // Update feedback
                    feedback.setForeground(Color.RED);                                              // Set feedback color to red
                    previousWord.setText("");                                                 // Clear previous solution
                    if (hintsOn) {
                        definition.setText("Hint: " + wordBank.getDefinition(currentWord));         // Show hint
                    } else {
                        definition.setText("");
                    }
                }

                // Game over
                if (scramblesRemaining == 0) { 
                    timer.stop();                                                                               // Stop the timer
                    center.removeAll();
                    @SuppressWarnings("unused")
                    TimedGameOver gameover = new TimedGameOver(window, center, difficulty, theme, elapsedTime); // Switch to the game over screen
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

    // Add the timer to the one row of the game screen
    private void createTimerPanel() {
        statusPanelLeft.setLayout(new GridBagLayout());
        timerLabel.setText("Time: " + elapsedTime);                    // Add text to label
        SustainabilityScramble.setFontAvenir(timerLabel, 24.0f); // Set font to Avenir Regular 24 point
        statusPanelLeft.add(timerLabel);                               // Add label to panel
    }

    private void createHelpPanel() {
        statusPanelRight.setLayout(new GridLayout(2, 1));
        createScramblesRemainingPanel();
        createHelpButtonPanel();
        statusPanelRight.add(helpPanel);
        statusPanelRight.add(scramblesRemainingPanel);    
    }
    // Add the number of scrambles remaining to one row of the game screen
    private void createScramblesRemainingPanel() {
        scramblesRemainingPanel.setLayout(new GridBagLayout());                        // Set panel to grid bag layout
        scramblesRemainingLabel.setText("Scrambles Remaining: " + scramblesRemaining); // Add text to label
        SustainabilityScramble.setFontAvenir(scramblesRemainingLabel, 24.0f);    // Set font to Avenir Regular 24 point
        scramblesRemainingPanel.add(scramblesRemainingLabel);                          // Add label to panel
    }
    
    // Add a button to exit to the home screen to the game screen
    private void createHelpButtonPanel() {
        addHintToggle();
        addGiveUpButton();
    }
    private void addGiveUpButton() {

        giveUp.setText("Give Up");                          // Add text to button
        giveUp.setFont(giveUp.getFont().deriveFont(16.0f)); // Set font size of button text to 20 point

        // Give up button is clicked
        giveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                center.removeAll();
                @SuppressWarnings("unused")
                Home home = new Home(window, center); // Switch to the home screen
            }
        });

        giveUp.addMouseListener(new MouseAdapter() {

            // Set give up button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (giveUp.isEnabled()) {
                    giveUp.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    giveUp.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    giveUp.setFont(giveUp.getFont().deriveFont(16.0f));  // Scale new font to 20 point
                }
            }
            
            // Set give up button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (giveUp.isEnabled()) {
                    giveUp.setBackground(new Color(Colors.WHITE));           // Revert the button color to white
                    giveUp.setFont(SustainabilityScramble.avenirRegular);    // Revert the font family to Avenir Regular
                    giveUp.setFont(giveUp.getFont().deriveFont(16.0f)); // Scale new font to 20 point
                }
            }
        });

        helpPanel.add(giveUp); // Add button to panel
    }

    // Add toggle for hints
    private void addHintToggle() {

        hints.setText("Hints: OFF");                      // Add text to button
        hints.setFont(hints.getFont().deriveFont(16.0f)); // Set font size of button text to 20 point

        // Hints button is clicked
        hints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hintsOn = !hintsOn;
                if (hintsOn) {
                    hints.setText("Hints: ON");                                       // Update button text
                    if (feedback.getText() == "Incorrect") {
                        definition.setText("Hint: " + wordBank.getDefinition(currentWord)); // Show hint
                    }
                } else {
                    hints.setText("Hints: OFF");
                    if (feedback.getText() == "Incorrect") {
                        definition.setText("");
                    }
                }
            }
        });

        hints.addMouseListener(new MouseAdapter() {

            // Set hints button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (hints.isEnabled()) {
                    hints.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    hints.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    hints.setFont(hints.getFont().deriveFont(16.0f));  // Scale new font to 20 point
                }
            }
            
            // Set hints button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (hints.isEnabled()) {
                    hints.setBackground(new Color(Colors.WHITE));           // Revert the button color to white
                    hints.setFont(SustainabilityScramble.avenirRegular);    // Revert the font family to Avenir Regular
                    hints.setFont(hints.getFont().deriveFont(16.0f)); // Scale new font to 20 point
                }
            }
        });

        helpPanel.add(hints); // Add button to panel
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
