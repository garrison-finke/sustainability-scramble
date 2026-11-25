
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SurvivalGameOver extends GameOver {
    
    private int score; // Score

    public SurvivalGameOver(JFrame window, JPanel center, Difficulty difficulty, String theme, int score) {
        super(window, center, difficulty, theme);
        this.score = score;
        init();
    }

    private void init() {
        // Retry button is clicked
        retry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                center.removeAll();                                                      // Remove all components from center panel
                @SuppressWarnings("unused")
                SurvivalGame game = new SurvivalGame(window, center, difficulty, theme); // Start a new survival game
            }
        });

        retry.addMouseListener(new MouseAdapter() {

            // Set credits button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (retry.isEnabled()) {
                    retry.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    retry.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    retry.setFont(retry.getFont().deriveFont(20.0f));   // Scale new font to 20 point
                }
            }
            
            // Set credits button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (retry.isEnabled()) {
                    retry.setBackground(new Color(Colors.WHITE));           // Revert the button color to white
                    retry.setFont(SustainabilityScramble.avenirRegular);    // Revert the font family to Avenir Regular
                    retry.setFont(retry.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });

        performance.setText("Score: " + score);                         // Add text to label
        SustainabilityScramble.setFontAvenir(performance, 24.0f); // Set font to Avenir Regular 24 point
        performancePanel.add(performance);                              // Add label to panel
    }
}
