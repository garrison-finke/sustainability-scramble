
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimedGameOver extends GameOver {

    private JLabel timeLabel; // Text label for elapsed time
    private int elapsedTime;  // Time taken to beat game
    
    public TimedGameOver(JFrame window, JPanel center, Difficulty difficulty, String theme, int elapsedTime) {
        super(window, center, difficulty, theme);
        this.elapsedTime = elapsedTime;
        timeLabel = new JLabel();
        init();
    }

    private void init() {
        // Retry button is clicked
        retry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                center.removeAll();                                                // Remove all components from center panel
                @SuppressWarnings("unused")
                TimedGame game = new TimedGame(window, center, difficulty, theme); // Start a new timed game
            }
        });

        retry.addMouseListener(new MouseAdapter() {

            // Set retry button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (retry.isEnabled()) {
                    retry.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    retry.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    retry.setFont(retry.getFont().deriveFont(20.0f));  // Scale new font to 20 point
                }
            }
            
            // Set retry button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (retry.isEnabled()) {
                    retry.setBackground(new Color(Colors.WHITE));           // Revert the button color to white
                    retry.setFont(SustainabilityScramble.avenirRegular);    // Revert the font family to Avenir Regular
                    retry.setFont(retry.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });
        
        timeLabel.setText("Time: " + elapsedTime);                    // Add text to label
        SustainabilityScramble.setFontAvenir(timeLabel, 24.0f); // Set font to Avenir Regular 24 point
        performancePanel.add(timeLabel);                              // Add label to screen
    }
}
