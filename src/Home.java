
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Home {
    
    // List of valid themes
    private final String[] THEMES = {"Renewable Energy", "Trash"};

    private JFrame window;                     // The application window
    private JPanel center;                     // The area within the borders within the window
	private JPanel welcomePanel;               // Panel for welcome text located in first row of center panel
	private JPanel modeSelectionPanel;         // Panel for mode selection buttons located in second row of center panel
	private JPanel modeDescriptionPanel;       // Panel for mode description located in third row of center panel
	private JPanel difficultySelectionPanel;   // Panel for difficulty selection buttons located in fourth row of center panel
	private JPanel difficultyDescriptionPanel; // Panel for difficulty desription located in fifth row of center panel
	private JPanel themePanel;                 // Panel for theme selection located in sixth row of center panel
	private JPanel buttonPanel;                // Panel for start and credits buttons located in seventh row of center panel
    private JLabel welcome;                    // Welcome message
    private JLabel modeSelection;              // Mode selection instructions
	private JButton survival;           // Survival mode selector button
	private JButton timed;              // Timed mode selector button
	private JLabel modeDescription;            // Description of hovered or selected mode
    private JLabel difficultySelection;        // Difficulty selection instructions
	private JButton easy;               // Easy difficulty selector button
	private JButton medium;             // Medium difficulty selector button
	private JButton hard;               // Hard difficulty selector button
	private JLabel difficultyDescription;      // Description of hovered or selected difficulty
    private JLabel themeSelection;             // Theme selection instructions
	private JComboBox<String> themes;          // Dropdown menu of themes
	private CustomButton start;                // Start button
	private CustomButton credits;              // Credits button
    private Mode mode;                         // Selected mode
    private Difficulty difficulty;             // Selected difficulty
	
    // Constructor
	public Home(JFrame window, JPanel center) {
        this.window = window;
        this.center = center;
        welcomePanel = new JPanel(); 
        modeSelectionPanel = new JPanel();
        modeDescriptionPanel = new JPanel();
        difficultySelectionPanel = new JPanel();
        difficultyDescriptionPanel = new JPanel();
        themePanel = new JPanel();
        buttonPanel = new JPanel();
        welcome = new JLabel();
        modeSelection = new JLabel();
        survival = new SelectorButton();
        timed = new SelectorButton();
        modeDescription = new JLabel();
        difficultySelection = new JLabel();
        easy = new SelectorButton();
        medium = new SelectorButton(); 
        hard = new SelectorButton();       
        difficultyDescription = new JLabel();
        themeSelection = new JLabel();
        themes = new JComboBox<String>();
        start = new CustomButton();
        credits = new CustomButton();
        init();
	}
	
    // Initializer
	private void init() {
        createWelcomePanel();               // Create panel for welcome text
        createModeSelectionPanel();         // Create panel for mode selection buttons
        createModeDescriptionPanel();       // Create panel for mode description
        createDifficultySelectionPanel();   // Create panel for difficulty selection buttons
        createDifficultyDescriptionPanel(); // Create panel for difficulty description
        createThemePanel();                 // Create panel for theme selection
        createButtonPanel();                // Create panel for start and credits buttons
		addPanels();                        // Add subpanels to central panel
        window.revalidate();                // Validate the window layout
        window.repaint();                   // Refresh the window layout
	}
    
    // Create panel for welcome text
	private void createWelcomePanel() {
        welcome.setText("Welcome to Sustainability Scramble!"); // Add text to label
        SustainabilityScramble.setFontAvenir(welcome, 32.0f);   // Set font to Avenir Regular 32 point
        welcomePanel.add(welcome);                                    // Add label to panel
	}
	
    // Create panel for mode selection buttons
    private void createModeSelectionPanel() {
        addModeSelectionInstructions();
        addSurvivalButton();
        addTimedButton();
    }
    private void addModeSelectionInstructions() {
        modeSelection.setText("1) Select Mode: ");                  // Add text to label
        SustainabilityScramble.setFontAvenir(modeSelection, 24.0f); // Set font to Avenir Regular 24 point
        modeSelectionPanel.add(modeSelection);                            // Add label to panel
    }
    private void addSurvivalButton() {

        survival.setText("Survival"); // Add text to button

    	// Set survival button behavior on click
        survival.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode = Mode.SURVIVAL;                                   // Change selected mode
                survival.setSelected(true);                          // Set selected mode
                timed.setSelected(false);                            // De-select other mode
                timed.setFont(SustainabilityScramble.avenirRegular);    // Reset font family of other mode's button text to Avenir Regular
                timed.setFont(timed.getFont().deriveFont(20.0f)); // Scale the new font to 20 point 
                easy.setEnabled(true);                               // Enable the difficulty options
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setSelected(false);                             // Clear the selected difficulty option
                easy.setFont(SustainabilityScramble.avenirRegular);    // Revert font family of the difficulty buttons to Avenir Regular
                easy.setFont(easy.getFont().deriveFont(20.0f));   // Scale the new font to 20 point 
                medium.setSelected(false);
                medium.setFont(SustainabilityScramble.avenirRegular);
                medium.setFont(medium.getFont().deriveFont(20.0f)); 
                hard.setSelected(false);
                hard.setFont(SustainabilityScramble.avenirRegular);
                hard.setFont(hard.getFont().deriveFont(20.0f));
                difficultyDescription.setText("");                // Remove the text associated with the previously selected difficulty option
                start.setEnabled(false);                             // Disable the start button
            }
        });
        
        survival.addMouseListener(new MouseAdapter() {

            String previous;

            // Set survival button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                survival.setBackground(new Color(Colors.MEDIUM_PURPLE_50));                                     // Change button color to medium purple
                survival.setFont(SustainabilityScramble.avenirDemi);                                            // Change font family to Avenir Demi
                survival.setFont(survival.getFont().deriveFont(20.0f));                                    // Scale new font to 20 point
                previous = modeDescription.getText();                                                           // Save the current text in the mode description label
                modeDescription.setText("Solve as many scrambles as you can before running out of lives"); // Change the text in the mode description label to describe the hovered mode
            }
            
            // Set survival button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (!survival.isSelected()) {
                    survival.setBackground(new Color(Colors.WHITE));              // Revert the button color to white
                    survival.setFont(SustainabilityScramble.avenirRegular);       // Revert the font family to Avenir Regular
                    survival.setFont(survival.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                    modeDescription.setText(previous);                            // Revert the text in the mode description label to the previous text
                }
            }
        });
        
        modeSelectionPanel.add(survival); // Add button to panel
    }    
    private void addTimedButton() {

        timed.setText("Timed"); // Add text to button

    	// Set timed button behavior on click
        timed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mode = Mode.TIMED;                                            // Change selected mode
                timed.setSelected(true);                                   // Set selected mode
                survival.setSelected(false);                               // De-select other mode
                survival.setFont(SustainabilityScramble.avenirRegular);       // Reset font family of other mode's button text to Avenir Regular
                survival.setFont(survival.getFont().deriveFont(20.0f)); // Scale the new font to 20 point 
                easy.setEnabled(true);                                     // Enable the difficulty options
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setSelected(false);                                   // Clear the selected difficulty option
                easy.setFont(SustainabilityScramble.avenirRegular);           // Revert font family of the difficulty buttons to Avenir Regular
                easy.setFont(easy.getFont().deriveFont(20.0f));         // Scale the new font to 20 point 
                medium.setSelected(false);
                medium.setFont(SustainabilityScramble.avenirRegular);
                medium.setFont(medium.getFont().deriveFont(20.0f));
                hard.setSelected(false);
                hard.setFont(SustainabilityScramble.avenirRegular);
                hard.setFont(hard.getFont().deriveFont(20.0f));
                difficultyDescription.setText("");                      // Remove the text associated with the previously selected difficulty option
                start.setEnabled(false);                                   // Disable the start button
            }
        });
        
        timed.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Set timed button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                timed.setBackground(new Color(Colors.MEDIUM_PURPLE_50));                             // Change button color to medium purple
                timed.setFont(SustainabilityScramble.avenirDemi);                                    // Change font family to Avenir Demi
                timed.setFont(timed.getFont().deriveFont(20.0f));                              // Scale new font to 20 point
                previous = modeDescription.getText();                                                // Save the current text in the mode description label
                modeDescription.setText("Solve a set number of scrambles as fast as you can"); // Change the text in the mode description label to describe the hovered mode
            }
            
            // Set time button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (!timed.isSelected()) {
                    timed.setBackground(new Color(Colors.WHITE));           // Revert the button color to white
                    timed.setFont(SustainabilityScramble.avenirRegular);    // Revert the font family to Avenir Regular
                    timed.setFont(timed.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                    modeDescription.setText(previous);                      // Revert the text in the mode description label to the previous text
                }
            }
        }); 
        
        modeSelectionPanel.add(timed); // Add button to panel
    }
    
    // Create panel for mode description
    private void createModeDescriptionPanel() {
        SustainabilityScramble.setFontAvenir(modeDescription, 20.0f); // Set font family to Avenir Regular 20 point
        modeDescriptionPanel.add(modeDescription);                          // Add label to panel
    }
    
    // Create panel for difficulty selection buttons
    private void createDifficultySelectionPanel() {
        addDifficultySelectionInstructions();
        addEasyButton();
        addMediumButton();
        addHardButton();
    }
    private void addDifficultySelectionInstructions() {
        difficultySelection.setText("2) Select Difficulty: ");            // Add text to label
        SustainabilityScramble.setFontAvenir(difficultySelection, 24.0f); // Set font family to Avenir Regular
        difficultySelectionPanel.add(difficultySelection);                      // Add label to panel
    }
    private void addEasyButton() {

        easy.setEnabled(false);  // Difficulty cannot be selected until a mode is selected 
        easy.setText("Easy"); // Add text to button

    	// Set easy button behavior on click
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = Difficulty.EASY;                             // Change selected difficulty
                easy.setSelected(true);                                // Set selected difficulty
                medium.setSelected(false);                             // De-select other difficulties
                medium.setFont(SustainabilityScramble.avenirRegular);     // Reset font family of other difficulty button's text to Avenir Regular
                medium.setFont(medium.getFont().deriveFont(20.0f)); // Scale the new font to 20 point 
                hard.setSelected(false);                        
                hard.setFont(SustainabilityScramble.avenirRegular);
                hard.setFont(hard.getFont().deriveFont(20.0f));
                start.setEnabled(true);                                // Enable the start button
            }
        });
        
        easy.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Set easy button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                
                if (easy.isEnabled()) {
                    easy.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change button color to medium purple
                    easy.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    easy.setFont(easy.getFont().deriveFont(20.0f));   // Scale new font to 20 point
                }
                
                previous = difficultyDescription.getText();              // Save the current text in the difficulty description label
                if (survival.isSelected()) {                             // Change the text in the difficulty description label to describe the hovered difficulty depending on the selected mode
                    difficultyDescription.setText("Five Lives");  
                }
                if (timed.isSelected()) {
                    difficultyDescription.setText("Five Scrambles");
                }
            }
            
            // Set easy button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (!easy.isSelected()) {
                    easy.setBackground(new Color(Colors.WHITE));          // Revert the button color to white
                    easy.setFont(SustainabilityScramble.avenirRegular);   // Revert the font family to Avenir Regular
                    easy.setFont(easy.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                    difficultyDescription.setText(previous);              // Revert the text in the difficulty description label to the previous text
                }
            }
        });    
        
        difficultySelectionPanel.add(easy); // Add easy button to panel
    }   
    private void addMediumButton() {

        medium.setEnabled(false);    // Difficulty cannot be selected until a mode is selected 
        medium.setText("Medium"); // Add text to button

    	// Set medium button behavior on click
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = Difficulty.MEDIUM;                       // Change selected difficulty
                medium.setSelected(true);                          // Set selected difficulty
                easy.setSelected(false);                           // De-select other difficulties
                easy.setFont(SustainabilityScramble.avenirRegular);   // Reset font family of other difficulty button's text to Avenir Regular
                easy.setFont(easy.getFont().deriveFont(20.0f)); // Scale the new font to 20 point 
                hard.setSelected(false);
                hard.setFont(SustainabilityScramble.avenirRegular);
                hard.setFont(hard.getFont().deriveFont(20.0f));
                start.setEnabled(true);                            // Enable the start button
            }
        });
        
        medium.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Set medium button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                
                if (medium.isEnabled()) {
                    medium.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change button color to medium purple
                    medium.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    medium.setFont(medium.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
                
                previous = difficultyDescription.getText();              // Save the current text in the difficulty description label
                if (survival.isSelected()) {                             // Change the text in the difficulty description label to describe the hovered difficulty depending on the selected mode
                    difficultyDescription.setText("Three Lives");
                }
                
                if (timed.isSelected()) {
                    difficultyDescription.setText("Ten Scrambles");
                }
            }
            
            // Set medium button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (!medium.isSelected()) {
                    medium.setBackground(new Color(Colors.WHITE));            // Revert the button color to white
                    medium.setFont(SustainabilityScramble.avenirRegular);     // Revert the font family to Avenir Regular
                    medium.setFont(medium.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                    difficultyDescription.setText(previous);                  // Revert the text in the difficulty description label to the previous text
                }
            }
        });  
        
        difficultySelectionPanel.add(medium); // Add button to panel
    }
    private void addHardButton() {
    	
        hard.setEnabled(false);  // Difficulty cannot be selected until a mode is selected 
        hard.setText("Hard"); // Add text to button

        // Set hard button behavior on click
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = Difficulty.HARD;                             // Change selected difficulty
                hard.setSelected(true);                                // Set selected difficulty
                easy.setSelected(false);                               // De-select other difficulties
                easy.setFont(SustainabilityScramble.avenirRegular);       // Reset font family of other difficulty button's text to Avenir Regular
                easy.setFont(easy.getFont().deriveFont(20.0f));     // Scale the new font to 20 point 
                medium.setSelected(false);
                medium.setFont(SustainabilityScramble.avenirRegular);
                medium.setFont(medium.getFont().deriveFont(20.0f));
                start.setEnabled(true);                                // Enable the start button
            }
        });
        
        hard.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Set hard button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                
                if (hard.isEnabled()) {
                    hard.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change button color to medium purple
                    hard.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    hard.setFont(hard.getFont().deriveFont(20.0f));   // Scale new font to 20 point
                }

                previous = difficultyDescription.getText();                // Save the current text in the difficulty description label
                if (survival.isSelected()) {                               // Change the text in the difficulty description label to describe the hovered difficulty depending on the selected mode
                    difficultyDescription.setText("One Life");
                }
                
                if (timed.isSelected()) {
                    difficultyDescription.setText("Twenty Scrambles");
                }
            }
            
            // Set hard button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (!hard.isSelected()) {
                    hard.setBackground(new Color(Colors.WHITE));          // Revert the button color to white
                    hard.setFont(SustainabilityScramble.avenirRegular);   // Revert the font family to Avenir Regular
                    hard.setFont(hard.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                    difficultyDescription.setText(previous);              // Revert the text in the difficulty description label to the previous text
                }
            }
        });
        
        difficultySelectionPanel.add(hard); // Add button to panel
    }
    
    // Create panel for difficulty description
    private void createDifficultyDescriptionPanel() {
        SustainabilityScramble.setFontAvenir(difficultyDescription, 20.0f); // Set font to Avenir Regular 20 point
        difficultyDescriptionPanel.add(difficultyDescription);                    // Add label to panel
    }
    
    // Add dropdown menu of themes to one one row of the home screen
    private void createThemePanel() {
        themeSelection.setText("3) Select Theme: ");                 // Add text to label
        SustainabilityScramble.setFontAvenir(themeSelection, 24.0f); // Set font to Avenir Regular 24 point
        themePanel.add(themeSelection);                                    // Add label to panel
        
        themes.setRenderer(new DefaultListCellRenderer() {
            @Override 
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    label.setBackground(new Color(Colors.MEDIUM_PURPLE_50));          // Set background color of options in dropdown to medium purple when hovered over
                } else {
                    label.setBackground(new Color(Colors.WHITE));                     // Set background color of options in dropdown to white when not hovered over
                }

                label.setBorder(new EmptyBorder(6, 2, 2, 2)); // Center the text for the options in the dropdown vertically 
                return label;
            }
        });

        themes.setFocusable(false);
        themes.setBackground(new Color(Colors.WHITE));            // Set background color of the dropdown to white 
        themes.setFont(SustainabilityScramble.avenirRegular);     // Set font family to Avenir Regular
        themes.setFont(themes.getFont().deriveFont(20.0f)); // Set font size to 20 point

        for (String theme : THEMES) { // Add themes to dropdown
            themes.addItem(theme); 
        }

        themePanel.add(themes); // Add dropdown to panel
    }
    
    // Add start and credits buttons to one row of the home screen
    private void createButtonPanel() {
    	addStartButton();
        addCreditsButton();
    }
    private void addStartButton() {
        
    	start.setEnabled(false);   // The start button does not become enabled until both a mode and difficulty are selected
        start.setText("Start"); // Add text to button

        // Set start button behavior on click
        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		center.removeAll();                                                          // Remove all components from center panel
        		String theme = (String) themes.getSelectedItem();
                if (mode == Mode.SURVIVAL) {
                    @SuppressWarnings("unused")
                    SurvivalGame game = new SurvivalGame(window, center, difficulty, theme); // Switch to a new survival game scene
                } else {
                    @SuppressWarnings("unused")
                    TimedGame game = new TimedGame(window, center, difficulty, theme);       // Switch to a new timed game scene
                }
        	}
        });

        start.addMouseListener(new MouseAdapter() {

            // Set start button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (start.isEnabled()) {
                    start.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    start.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    start.setFont(start.getFont().deriveFont(20.0f));  // Scale new font to 20 point
                }
            }
            
            // Set start button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (start.isEnabled()) {
                    start.setBackground(new Color(Colors.WHITE));           // Revert the button color to white
                    start.setFont(SustainabilityScramble.avenirRegular);    // Revert the font family to Avenir Regular
                    start.setFont(start.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });
        
        buttonPanel.add(start); // Add start button to panel
    }
    private void addCreditsButton() {

        credits.setText("Credits"); // Add text to button

        // Set credits button behavior on click
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                center.removeAll();                            // Remove all components from center panel
                @SuppressWarnings("unused")
                Credits credits = new Credits(window, center); // Switch to the credits scene
            }
        });

        credits.addMouseListener(new MouseAdapter() {

            // Set credits button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (credits.isEnabled()) {
                    credits.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    credits.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    credits.setFont(credits.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
            
            // Set credits button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (credits.isEnabled()) {
                    credits.setBackground(new Color(Colors.WHITE));            // Revert the button color to white
                    credits.setFont(SustainabilityScramble.avenirRegular);     // Revert the font family to Avenir Regular
                    credits.setFont(credits.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });

        buttonPanel.add(credits); // Add credits button to panel
    }
	
    // Change layout and add rows to the home screen
	private void addPanels() {
        center.setLayout(new GridLayout(7, 1)); // Change the home screen layout
        center.add(welcomePanel);                          // Add welcome text to first row of center panel
        center.add(modeSelectionPanel);                    // Add mode selection to second row of center panel
        center.add(modeDescriptionPanel);                  // Add mode description to third row of center panel
        center.add(difficultySelectionPanel);              // Add difficulty selection to fourth row of center panel
        center.add(difficultyDescriptionPanel);            // Add difficulty description to fifth row of center panel
        center.add(themePanel);                            // Add theme selection to sixth row of center panel
        center.add(buttonPanel);                           // Add start and credits button to seventh row of center panel
	}
}
