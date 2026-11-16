import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SustainabilityScramble {
	
	private JFrame window;
	
	private JPanel home1;
	private JPanel home2;
	private JPanel home3;
	private JPanel home4;
	private JPanel home5;
	private JPanel home6;
	private JPanel home7;
	
	private MyButton survival;
	private MyButton timed;
	
	private JLabel mode;
	
	private MyButton easy;
	private MyButton medium;
	private MyButton hard;
	
	private JLabel difficulty;
	
	private JComboBox<String> themes;
	
	private JButton start;
	private JButton leaderboards;
	private JButton credits;
	private JButton exit;
	
	// home screen (?)
    // game screen
    // credits screen
    // leaderboards screen
	
	public SustainabilityScramble() {
		
		// Instantiate application window
		window = new JFrame();
        
		// Instantiate containers for elements in each of the seven rows of the home screen
        home1 = new JPanel(); 
        home2 = new JPanel();
        home3 = new JPanel();
        home4 = new JPanel();
        home5 = new JPanel();
        home6 = new JPanel();
        home7 = new JPanel();
        
        survival = new MyButton("Suvival");
        timed = new MyButton("Timed");
        
        mode = new JLabel();
        
        easy = new MyButton("Easy");
        medium = new MyButton("Medium");
        hard = new MyButton("Hard");
        
        difficulty = new JLabel();
           
        themes = new JComboBox<String>();
        
        start = new JButton("Start");
        leaderboards = new JButton("Leaderboards");
        credits = new JButton("Credits");
        exit = new JButton("Exit");
        
        init();
	}
	
	private void init() {
        setWindowParameters();
        addWelcomeMessage(home1);
        addModeButtons(home2);
        addModeDescription(home3);
        addDifficultyButtons(home4);
        addDifficultyDescription(home5);
        addThemeDropdown(home6);
        addButtons(home7);
		addPanels();
        window.setVisible(true); // Make each panel and its contents visible
	}
	
	private void fini() {
        window.remove(home1);
        window.remove(home2);
        window.remove(home3);
        window.remove(home4);
        window.remove(home5);
        window.remove(home6);
        window.remove(home7);
        window.repaint();
	}
	
	private void setWindowParameters() {
		// Adjust settings
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ends program execution if the window is closed
        window.setTitle("Sustainability Scramble");
        window.setSize(500, 500);
        window.setLayout(new GridLayout(7, 1)); // Divides the window into seven rows and one column
	}
	
	private void addWelcomeMessage(JPanel panel) {
	   // First row: customized welcome message depending on whether this is the player's first time playing the game
	   // Leaderboards button created, then set enabled or disabled depending on outcome of file read (added to screen later)
	   try {
		   Files.readAllLines(Paths.get("Leaderboards.txt"));
	       panel.add(new JLabel("Welcome Back to Sustainability Scramble"));
	   } catch (IOException e) {
		   panel.add(new JLabel("Welcome to Sustainability Scramble"));
		   leaderboards.setEnabled(false); // If the leaderboards file does not exist (there are no recorded scores), the leaderboards screen is inaccessible
	   }
	}
	
    private void addModeButtons(JPanel panel) {
        // Second row: Buttons for mode select (Survival or Timed)
        panel.add(new JLabel("1) Select Mode: "));
        addSurvivalButton(panel);
        addTimedButton(panel);
    }
    
    private void addSurvivalButton(JPanel panel) {
    	// Survival button is clicked
        survival.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                survival.setSelected(true);
                timed.setSelected(false); // De-select other mode
                start.setEnabled(false); 
                easy.setEnabled(true); // If a mode is selected, the difficulty options become enabled
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setSelected(false); // If the selected mode is changed, the selected difficulty option is cleared
                medium.setSelected(false);
                hard.setSelected(false);
                difficulty.setText("");  // Since the difficulty option has been cleared, remove the text associated with it
            }
        });
        
        survival.addMouseListener(new MouseAdapter() {
            String previous;
            // Cursor enters Survival button
            public void mouseEntered(MouseEvent evt) {
                survival.setBackground(Color.CYAN); // Button color changes
                previous = mode.getText(); // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                mode.setText("Solve as many jumbles as you can before running out of lives");
            }
            
            // Cursor exits Survival button
            public void mouseExited(MouseEvent evt) {
                if (!survival.isSelected()) {
                    survival.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    mode.setText(previous); // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        });
        
        panel.add(survival);
    }
    
    private void addTimedButton(JPanel panel) {
    	// Timed button is clicked
        timed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timed.setSelected(true);
                survival.setSelected(false); // De-select other mode
                start.setEnabled(false);
                easy.setEnabled(true); // If a mode is selected, the difficulty options become enabled
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setSelected(false); // If the selected mode is changed, the selected difficulty option is cleared
                medium.setSelected(false);
                hard.setSelected(false);
                difficulty.setText("");  // Since the difficulty option has been cleared, remove the text associated with it
            }
        });
        
        timed.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Cursor enters Timed button
            public void mouseEntered(MouseEvent evt) {
                timed.setBackground(Color.ORANGE); // Button color changes
                previous = mode.getText(); // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
                mode.setText("Solve a set number of jumbles as fast as you can");
            }
            
            // Cursor exits Timed button
            public void mouseExited(MouseEvent evt) {
                if (!timed.isSelected()) {
                    timed.setBackground(null); // Remove color from button if it is not selected and not being hovered over
                    mode.setText(previous); // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        }); 
        
        panel.add(timed);
    }
    
    private void addModeDescription(JPanel panel) {
        // Third row: Text description of the selected mode. Blank until mode selected
        panel.add(mode);
    }
    
    private void addDifficultyButtons(JPanel panel) {
        // Fourth row: Buttons for difficulty select (Easy, Medium, or Hard)
        panel.add(new JLabel("2) Select Difficulty: "));   
        addEasyButton(panel);
        addMediumButton(panel);
        addHardButton(panel);
    }
    
    private void addEasyButton(JPanel panel) {
    	// Easy button is clicked
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                easy.setSelected(true); // Ensure that the other two difficulties are de-selected
                medium.setSelected(false); 
                hard.setSelected(false);
                start.setEnabled(true); // If the difficulty is selected, the player is allowed to start
            }
        });
        
        easy.addMouseListener(new MouseAdapter() {
        	
            String previous;
            
            // Cursor enters Easy button
            public void mouseEntered(MouseEvent evt) {
                
                if (easy.isEnabled()) {
                    easy.setBackground(Color.GREEN); // Button color changes
                }
                
                previous = difficulty.getText();  // The text in the panel below the button will revert if the mouse stops hovering over the button without selecting it
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
                    difficulty.setText(previous);  // Revert the text in the panel below the button to the previous text if it is not being hovered over
                }
            }
        });    
        
        easy.setEnabled(false); // Difficulty cannot be selected until a mode is selected
        
        panel.add(easy);
    }
    
    private void addMediumButton(JPanel panel) {
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
    	// Hard button is clicked
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
    
    private void addDifficultyDescription(JPanel panel) {
        // Fifth row: Text description of the selected difficulty. Blank until difficulty selected
        panel.add(difficulty);
    }
    
    private void addThemeDropdown(JPanel panel) {
        // Sixth row: Dropdown menu for theme selection
        panel.add(new JLabel("3) Select Theme: "));
        themes.addItem("Animals"); // loop through files in theme folder
        themes.setFocusable(false);
        panel.add(themes);
    }
    
    private void addButtons(JPanel panel) {
        // Seventh row: Buttons to transition away from the home screen (Start, Credits, Exit)
    	addStartButton(panel);
        
        leaderboards.setFocusable(false); // Add leaderboards button
        panel.add(leaderboards); 

        credits.setFocusable(false); // Add credits button
        panel.add(credits);

        exit.setFocusable(false); // Add exit button
        panel.add(exit);
    }
    
    private void addStartButton(JPanel panel) {
        start.setFocusable(false); 
    	start.setEnabled(false); // The start button does not become enabled until both a mode and difficulty are selected

        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fini();
        		Theme theme = Theme.valueOf((String) themes.getSelectedItem());
        		Game game = new Game(window, theme);
        	}
        });
        
        panel.add(start); // Add start button
    }
	
	private void addPanels() {
        // Add containers to the home screen
        window.add(home1);
        window.add(home2);
        window.add(home3);
        window.add(home4);
        window.add(home5);
        window.add(home6);
        window.add(home7);
	}
	

}

