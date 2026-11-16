
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	
	JFrame window;
	
	JPanel game1;
	JPanel game2;
	JPanel game3;
	
	String mode;
	String difficulty;
	Theme theme;
	
	int score;
	int lives;
	
	public Game(JFrame window, Theme theme) {
		
		this.window = window;
		
		game1 = new JPanel();
        game2 = new JPanel();
        game3 = new JPanel();
        
        this.theme = theme;
        
		this.score = 0;
        
		init();
	}
	
	private void init() {
        window.add(game1);
        window.add(game2);
        window.add(game3);
        
	}

        
	/*
		
        Words words = new Words(theme);
        
        window.setLayout(new GridLayout(3, 1));
        

       
        game1.setLayout(new GridLayout(1, 2));
        JPanel game1_1 = new JPanel();
        JPanel game1_2 = new JPanel();
        game1.add(game1_1);
        game1.add(game1_2);
        
        JLabel jumble = new JLabel();
        game2.add(jumble);

        JTextField textField = new JTextField(30);
        textField.setEnabled(true);
        game3.add(textField);
        JButton submit = new JButton("Submit");
        submit.setFocusable(false);
        game3.add(submit);
        
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
        
        JLabel livesLabel = new JLabel();
        livesLabel.setText("Lives: " + lives);
        game1_1.add(livesLabel);
        
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score: " + score);
        game1_2.add(scoreLabel);
        
        word = words.getNext();
        String jumbled = Words.jumble(word);
        jumble.setText(jumbled);
    
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guess = textField.getText().toLowerCase();
                textField.setText("");
                if (guess.equals(word)) {
                    score = score + 1;
                    scoreLabel.setText("Score: " + score);
                } else {
                    lives = lives - 1;
                    livesLabel.setText("Lives: " + lives);
                }

                if (lives == 0) {
                    
                    window.remove(game1);
                    window.remove(game2);
                    window.remove(game3);
                    window.repaint();
                    
                    window.setLayout(new GridLayout(2, 1));
                    JPanel results1 = new JPanel();
                    results1.add(new JLabel("Game Over"));
                    window.add(results1);
                    JPanel results2 = new JPanel();
                    window.add(results2);
                    window.setVisible(true);
                    
                    JButton retry = new JButton("Retry");
                    results2.add(retry);
                    JButton home = new JButton("Home");
                    results2.add(home);
                    JButton exit = new JButton("Exit");
                    results2.add(exit);
                    
                    String record;
                    
                    if (survival.isSelected()) {
                        record = "Survival,";
                    } else {
                        record = "Timed,";
                    }
                    
                    if (easy.isSelected()) {
                        record = record + "Easy,";
                    } else if (medium.isSelected()) {
                        record = record + "Medium,";
                    } else {
                        record = record  + "Hard,";
                    }
                    
                    if (survival.isSelected()) {
                        record = record + score;
                    } else {
                        //record  = record + time;
                    }
                    
                    List<String> lines;
                    try {
                        lines = Files.readAllLines(Paths.get("Leaderboards.txt"));
                    } catch (IOException f) {
                        lines = new ArrayList<String>();
                    }
                    
                    String records = "";
                    for (String line : lines) {
                        records = records + line + "\n";
                    }
                    records = records + record;
                    
                    byte [] bytes = records.getBytes();
                    try {
                        Files.write(Paths.get("Leaderboards.txt"), bytes);
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                    
                    home.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            window.remove(results1);
                            window.remove(results2);
                            window.setLayout(new GridLayout(7, 1));
                            window.add(home1);
                            window.add(home2);
                            window.add(home3);
                            window.add(home4);
                            window.add(home5);
                            window.add(home6);
                            window.add(home7);
                            window.repaint();
                        }
                    });
                    
                    retry.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            
                        }
                    });
                    
                    exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            window.dispose();
                        }
                    });
                    
                }
                                      
                word = words.getNext();
                String jumbled = Words.jumble(word);
                jumble.setText(jumbled);
            }
            */
}

