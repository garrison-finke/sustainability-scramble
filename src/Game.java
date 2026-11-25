
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Game {
	
    protected JFrame window;            // The application window
	protected JPanel center;            // The area within the borders of the window
    protected Difficulty difficulty;    // The difficulty selected on the home screen
    protected String theme;             // The theme selected on the home screen
    protected JPanel statusPanel;       // Panel for game status located in first row of game screen
    protected JPanel statusPanelLeft;   // Left side of status panel
    protected JPanel statusPanelRight;  // Right side of status panel
	protected JPanel scramblePanel;     // Panel for the scramble located in second row of game screen
	protected JPanel inputPanel;        // Panel for answer input located in third row of game screen
    protected JPanel informationPanel;  // Panel for feedback and previous word located in fourth row of game screen
    protected JPanel feedbackPanel;     // Panel for feedback located in top of fourth row
    protected JPanel previousWordPanel; // Panel for previous word located in bottom of fourth row
    protected JPanel definitionPanel;   // Panel for definitions located in fifthr row of game screen
    protected WordBank wordBank;        // The collection of possible words and their definitions
    protected String currentWord;       // The current word
    protected JLabel scramble;          // Scramble text
    protected JTextField textField;     // Answer text field
    protected CustomButton submit;      // Submit button
    protected JLabel feedback;          // Feedback text
    protected JLabel previousWord;      // Previous word text
    protected JLabel definition;        // Definition text
	
    // Constructor
	public Game(JFrame window, JPanel center, Difficulty difficulty, String theme) {
		this.window = window;
		this.center = center;
        this.difficulty = difficulty;
        this.theme = theme;
        statusPanel = new JPanel();
        statusPanelLeft = new JPanel();
		statusPanelRight = new JPanel();
        scramblePanel = new JPanel();
        inputPanel = new JPanel();
        informationPanel = new JPanel();
        feedbackPanel = new JPanel();
        previousWordPanel = new JPanel();
        definitionPanel = new JPanel();
        wordBank = new WordBank();
        scramble = new JLabel();
        textField = new JTextField();
        submit = new CustomButton();
        feedback = new JLabel();
        previousWord = new JLabel();
        definition = new JLabel();
        init();
	}

    // Initializer
    private void init() {
        createScramblePanel();     // Create panel with scramble text
        createInputPanel();        // Create panel with answer input elements
        createFeedbackPanel();     // Create panel with feedback
        createPreviousWordPanel(); // Create panel with previous word
        createDefinitionPanel();   // Create panel with definition
        addPanels();               // Add subpanels to central panel
        window.revalidate();       // Validate the window layout
        window.repaint();          // Refresh the window layout
    }

    // Create panel with scramble text
    protected void createScramblePanel() {
        SustainabilityScramble.setFontAvenir(scramble, 24.0f); // Set font to Avenir Regular 24 point
        wordBank.readWords(theme);                                   // Add theme words to word bank
        showNextScramble();                                          // Add text to scramble
        scramblePanel.add(scramble);                                 // Add scramble to panel
    }
    protected void showNextScramble() {
        currentWord = wordBank.getNext();                  // Get word from word bank
        String scrambled = wordBank.scramble(currentWord); // Scramble word
        scramble.setText(scrambled);                       // Add text to scramble
    }

    // Create panel with answer input elements
    protected void createInputPanel() {
        addTextField();
        addSubmitButton();
    }
    private void addTextField() {
        textField.setEnabled(true); 
        textField.setColumns(20);                            // Set horizontal size of text field
        SustainabilityScramble.setFontAvenir(textField, 24.0f); // Set font to Avenir Regular 24 point
        inputPanel.add(textField);                                    // Add text field to panel
    }
    private void addSubmitButton() {
        submit.setText("Submit"); // Add text to button
        inputPanel.add(submit);         // Add button to panel
    }

    // Create panel with feedback
    protected void createFeedbackPanel() {
        SustainabilityScramble.setFontAvenir(feedback, 24.0f); // Set font to Avenir Regular 24 point
        feedbackPanel.add(feedback);                                 // Add text to panel
    }

    // Create panel with previous word
    protected void createPreviousWordPanel() {
        SustainabilityScramble.setFontAvenir(previousWord, 24.0f); // Set font to Avenir Regular 24 point
        previousWordPanel.add(previousWord);                             // Add text to panel
    }

    // Create panel with definition
    protected void createDefinitionPanel() {
        SustainabilityScramble.setFontAvenir(definition, 24.0f); // Set font to Avenir Regular 24 point
        definitionPanel.add(definition);                               // Add label to panel
    }

    // Add subpanels to central panel
    protected void addPanels() {
        center.setLayout(new GridLayout(5, 1));            // Set center panel to 5 by 1 grid layout
        statusPanel.setLayout(new GridLayout(1, 2));       // Set status panel to 1 by 2 grid layout
        statusPanel.add(statusPanelLeft);                             // Add left side to status panel
        statusPanel.add(statusPanelRight);                            // Add right side to status panel
        center.add(statusPanel);                                      // Add status to first row of center panel
        center.add(scramblePanel);                                    // Add scramble to second row of center panel
        center.add(inputPanel);                                       // Add answer input to third row of center panel
        informationPanel.setLayout(new GridLayout(2, 1));  // Set information panel to 2 by 1 grid layout
        informationPanel.add(feedbackPanel);                          // Add feedback to top of information panel
        informationPanel.add(previousWordPanel);                      // Add previous word to bottom of information panel
        center.add(informationPanel);                                 // Add information panel to fourth row of center panel
        center.add(definitionPanel);                                  // Add definition to fifth row of center panel
    }
}
