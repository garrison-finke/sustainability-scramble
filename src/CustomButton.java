
import javax.swing.*;
import java.awt.*;


public class CustomButton extends JButton {
    
    // Constructor
    public CustomButton() {
        super();
        init();
    }

    // Initializer
    private void init() {
        CustomButton button = this;
        button.setFocusable(false);                                             // Remove focusable
        SustainabilityScramble.setFontAvenir(button, 20.0f);                         // Set font to Avenir Regular 20 point
        button.setBackground(new Color(Colors.WHITE));                                     // Set background color to white
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(Colors.COOL_GRAY_100), 2), // Add thin gray border
            BorderFactory.createEmptyBorder(6, 5, 2, 5)            // Add padding
        ));
    }
}
