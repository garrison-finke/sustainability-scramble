
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home {
    
    private JFrame window;            // The application window
    private JPanel center;            // The area within the borders within the window
	private JPanel home1;             // The first row in the center panel
	private JPanel home2;             // The second row in the center panel
	private JPanel home3;             // The third row in the center panel
	private JPanel home4;             // The fourth row in the center panel
	private JPanel home5;             // The fifth row in the center panel
	private JPanel home6;             // The sixth row in the center panel
	private JPanel home7;             // The seventh row in the center panel
	private SelectorButton survival;  // Survival mode selector button
	private SelectorButton timed;     // Timed mode selector button
	private JLabel mode;              // Description of selected mode
	private SelectorButton easy;      // Easy mode selector button
	private SelectorButton medium;    // Medium mode selector button
	private SelectorButton hard;      // Hard mode selector button
	private JLabel difficulty;        // Description of selected difficulty
	private JComboBox<String> themes; // The dropdown menu for selecting the theme
	private JButton start;            // The start button
	private JButton credits;          // The credits button
	
    // Constructor
	public Home(JFrame window, JPanel center) {
        this.window = window;
        this.center = center;
        home1 = new JPanel(); 
        home2 = new JPanel();
        home3 = new JPanel();
        home4 = new JPanel();
        home5 = new JPanel();
        home6 = new JPanel();
        home7 = new JPanel();
        survival = new SelectorButton();
        timed = new SelectorButton();
        mode = new JLabel();
        easy = new SelectorButton();
        medium = new SelectorButton(); 
        hard = new SelectorButton();       
        difficulty = new JLabel();
        themes = new JComboBox<String>();
        start = new JButton();
        credits = new JButton();
        init();
	}
	
    // Initializer
	private void init() {
        addWelcomeMessage(home1);        // Add welcome message to first row of home screen
        addModeButtons(home2);           // Add mode selector buttons to second row of home screen
        addModeDescription(home3);       // Add description of selected mode to third row of home screen
        addDifficultyButtons(home4);     // Add difficulty selector buttons to fourth row of home screen
        addDifficultyDescription(home5); // Add description of selected difficulty to home screen
        addThemeDropdown(home6);         // Add dropdown menu for themes to sixth row of home screen
        addTransitionButtons(home7);     // Add start and credits buttons to seventh row of home screen
		addPanels();                     // Change layout and add rows to the home screen
        window.repaint();                // Refresh the home screen layout
	}
	
    // Finisher
	private void fini() {
        center.remove(home1); // Remove first row and its components from the home screen
        center.remove(home2); // Remove second row and its components from the home screen
        center.remove(home3); // Remove third row and its components from the home screen
        center.remove(home4); // Remove fourth row and its components from the home screen
        center.remove(home5); // Remove fifth row and its components from the home screen
        center.remove(home6); // Remove sixth row and its components from the home screen
        center.remove(home7); // Remove seventh row and its components from the home screen
        window.repaint();     // Refresh the home screen layout
	}
    
    // Add welcome message to one row of the home screen
	private void addWelcomeMessage(JPanel panel) {
	   panel.add(new JLabel("Welcome to Sustainability Scramble!"));
	}
	
    // Add survival and timed mode selector buttons to one row of the home screen
    private void addModeButtons(JPanel panel) {
        panel.add(new JLabel("1) Select Mode: "));
        addSurvivalButton(panel);
        addTimedButton(panel);
    }
    private void addSurvivalButton(JPanel panel) {

        survival.setText("Survival"); 

    	// Survival button is clicked
        survival.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                survival.setSelected(true);
                timed.setSelected(false);  // De-select other mode
                easy.setEnabled(true);            // If a mode is selected, the difficulty options become enabled
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setSelected(false);   // If the selected mode is changed, the selected difficulty option is cleared
                medium.setSelected(false);
                hard.setSelected(false);
                difficulty.setText("");        // Since the difficulty option has been cleared, remove the text associated with it
                start.setEnabled(false);          // Since the difficulty option has been cleared, the start option becomes disabled
            }
        });
        
        survival.addMouseListener(new MouseAdapter() {
            String previous;
            // Cursor enters Survival button
            public void mouseEntered(MouseEvent evt) {
                survival.setBackground(Color.CYAN); // Button color changes
                previous = mode.getText();          // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                mode.setText("Solve as many jumbles as you can before running out of lives");
            }
            
            // Cursor exits Survival button
            public void mouseExited(MouseEvent evt) {
                if (!survival.isSelected()) {
                    survival.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    mode.setText(previous);           // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        });
        
        panel.add(survival);
    }    
    private void addTimedButton(JPanel panel) {

        timed.setText("Timed");

    	// Timed button is clicked
        timed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timed.setSelected(true);
                survival.setSelected(false); // De-select other mode
                easy.setEnabled(true);              // If a mode is selected, the difficulty options become enabled
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setSelected(false);     // If the selected mode is changed, the selected difficulty option is cleared
                medium.setSelected(false);
                hard.setSelected(false);
                difficulty.setText("");          // Since the difficulty option has been cleared, remove the text associated with it
                start.setEnabled(false);            // Since the difficulty option has been cleared, the start option becomes disabled
            }
        });
        
        timed.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Cursor enters Timed button
            public void mouseEntered(MouseEvent evt) {
                timed.setBackground(Color.ORANGE); // Button color changes
                previous = mode.getText();         // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                mode.setText("Solve a set number of jumbles as fast as you can");
            }
            
            // Cursor exits Timed button
            public void mouseExited(MouseEvent evt) {
                if (!timed.isSelected()) {
                    timed.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    mode.setText(previous);        // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        }); 
        
        panel.add(timed);
    }
    
    // Add description of selected mode to one row of the home screen. Blank until mode selected
    private void addModeDescription(JPanel panel) {
        panel.add(mode);
    }
    
    // Add easy, medium, and hard difficulty selector buttons to one row of the home screen
    private void addDifficultyButtons(JPanel panel) {
        panel.add(new JLabel("2) Select Difficulty: "));   
        addEasyButton(panel);
        addMediumButton(panel);
        addHardButton(panel);
    }
    private void addEasyButton(JPanel panel) {

        easy.setText("Easy");

    	// Easy button is clicked
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                easy.setSelected(true);    // Ensure that the other two difficulties are de-selected
                medium.setSelected(false); 
                hard.setSelected(false);
                start.setEnabled(true);           // If the difficulty is selected, the player is allowed to start
            }
        });
        
        easy.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Cursor enters Easy button
            public void mouseEntered(MouseEvent evt) {
                
                if (easy.isEnabled()) {
                    easy.setBackground(Color.GREEN);         // Button color changes
                }
                
                previous = difficulty.getText();             // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                if (survival.isSelected()) {
                    difficulty.setText("Five Lives");  // Text assigned to panel below the button depends on the selected mode
                }
                
                if (timed.isSelected()) {
                    difficulty.setText("Five Jumbles");
                }
            }
            
            // Cursor exits Easy button
            public void mouseExited(MouseEvent evt) {
                if (!easy.isSelected()) {
                    easy.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    difficulty.setText(previous); // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        });    
        
        easy.setEnabled(false); // Difficulty cannot be selected until a mode is selected
        
        panel.add(easy);
    }   
    private void addMediumButton(JPanel panel) {

        medium.setText("Medium");

    	// Medium button is clicked
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                easy.setSelected(false); // Ensure that the other two difficulties are de-selected
                medium.setSelected(true);
                hard.setSelected(false);
                start.setEnabled(true); // If the difficulty is selected, the player is allowed to start
            }
        });
        
        medium.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Cursor enters Medium button
            public void mouseEntered(MouseEvent evt) {
                
                if (medium.isEnabled()) {
                    medium.setBackground(Color.YELLOW); // Button color changes
                }
                
                previous = difficulty.getText(); // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                if (survival.isSelected()) {
                    difficulty.setText("Three Lives"); // Text assigned to panel below the button depends on the selected mode
                }
                
                if (timed.isSelected()) {
                    difficulty.setText("Ten Jumbles");
                }
            }
            
            // Cursor exits Medium button
            public void mouseExited(MouseEvent evt) {
                if (!medium.isSelected()) {
                    medium.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    difficulty.setText(previous); // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        });  
        
        medium.setEnabled(false); // Difficulty cannot be selected until a mode is selected 
        
        panel.add(medium);
    }
    private void addHardButton(JPanel panel) {
    	
        hard.setText("Hard");

        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                easy.setSelected(false); // Ensure that the other two difficulties are de-selected
                medium.setSelected(false);
                hard.setSelected(true);
                start.setEnabled(true); // If the difficulty is selected, the player is allowed to start
            }
        });
        
        hard.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Cursor enters Hard button
            public void mouseEntered(MouseEvent evt) {
                
                if (hard.isEnabled()) {
                    hard.setBackground(Color.RED); // Button color changes
                }

                previous = difficulty.getText(); // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                if (survival.isSelected()) {
                    difficulty.setText("One Life"); // Text assigned to panel below the button depends on the selected mode
                }
                
                if (timed.isSelected()) {
                    difficulty.setText("Twenty Jumbles");
                }
            }
            
            // Cursor exits Hard button
            public void mouseExited(MouseEvent evt) {
                if (!hard.isSelected()) {
                    hard.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    difficulty.setText(previous); // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        });
        
        panel.add(hard); // Difficulty cannot be selected until a mode is selected
        
        hard.setEnabled(false);
    }
    
    // Add description of selected difficulty to one row of the home screen. Blank until difficulty selected
    private void addDifficultyDescription(JPanel panel) {
        panel.add(difficulty);
    }
    
    // Add dropdown menu of themes to one one row of the home screen
    private void addThemeDropdown(JPanel panel) {
        panel.add(new JLabel("3) Select Theme: "));
        themes.addItem("Animals"); // TODO: loop through files in theme folder
        themes.setFocusable(false);
        panel.add(themes);
    }
    
    // Add start and credits buttons to one row of the home screen
    private void addTransitionButtons(JPanel panel) {
    	addStartButton(panel);
        addCreditsButton(panel);
    }
    private void addStartButton(JPanel panel) {

        start.setText("Start");
        start.setFocusable(false); 
    	start.setEnabled(false); // The start button does not become enabled until both a mode and difficulty are selected

        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fini();
        		Theme theme = Theme.valueOf((String) themes.getSelectedItem());
                @SuppressWarnings("unused")
        		Game game = new Game(window, center, theme);
        	}
        });
        
        panel.add(start); // Add start button
    }
    private void addCreditsButton(JPanel panel) {

        credits.setText("Credits");
        credits.setFocusable(false);
        credits.setEnabled(true);

        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fini();
                //@SuppressWarnings("unused")
                //Credits credits = new Credits(window, center);
            }
        });

        panel.add(credits);
    }
	
    // Change layout and add rows to the home screen
	private void addPanels() {
        center.setLayout(new GridLayout(7, 1)); // Change the home screen layout
        center.add(home1);                                 // Add the first row to the home screen
        center.add(home2);                                 // Add the second row to the home screen
        center.add(home3);                                 // Add the third row to the home screen
        center.add(home4);                                 // Add the fourth row to the home screen
        center.add(home5);                                 // Add the fifth row to the home screen
        center.add(home6);                                 // Add the sixth row to the home screen
        center.add(home7);                                 // Add the seventh row to the home screen
        window.revalidate();                               // Validate the home screen layout
	}
}


