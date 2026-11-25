
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Credits {
    
    private JFrame window;       // The application window
    private JPanel center;       // The area within the borders of the window
    private JPanel headerPanel;  // Panel for header text located at top of center panel
    private JPanel creditsPanel; // Panel for credits text located in middle of center panel
    private JPanel backPanel;    // Panel for back button located at bottom of center panel
    private JLabel header;       // Header text
    private JLabel credits;      // Credits text
    private CustomButton back;   // Back button

    // Constructor
    public Credits(JFrame window, JPanel center) {
        this.window = window;
        this.center = center;
        headerPanel = new JPanel();
        creditsPanel = new JPanel();
        backPanel = new JPanel();
        header = new JLabel();
        credits = new JLabel();
        back = new CustomButton();
        init();
    }

    // Initializer
    private void init() {
        createHeaderPanel();  // Create panel with header text
        createCreditsPanel(); // Create panel with credits text
        createBackPanel();    // Create panel with back button 
        addPanels();          // Add subpanels to central panel
        window.revalidate();  // Validate the game screen layout
        window.repaint();     // Refresh the window layout
    }

    // Create panel with header text
    private void createHeaderPanel() {
        header.setText("Credits");                           // Add text to header
        SustainabilityScramble.setFontAvenir(header, 32.0f); // Set font to Avenir Regular 32 point
        headerPanel.add(header);                                   // Add header to panel
    }

    // Create panel with credits text
    private void createCreditsPanel() {

        SustainabilityScramble.setFontAvenir(credits, 16.0f);                              // Set font to Avenir Regular 16 point
        credits.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0)); // Add extra space to bottom of text to prevent cutting

        List<String> lines;
        try (InputStream in = getClass().getResourceAsStream("/assets/Credits.txt")) {
            // Read the credits text file in by bytes and convert its lines to a list of Strings
            lines = new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
        } catch (NullPointerException | IOException e) {
            throw new IllegalArgumentException();
        }

        // Add all lines to a single String of HTML
        StringBuilder creditsText = new StringBuilder("<html>");
        for (String line : lines) {
            creditsText.append(line);        // Add line
            creditsText.append("<br>"); // Add line break
        }
        creditsText.append("</html>");
        credits.setText(creditsText.toString()); // Add text to label

        creditsPanel.add(credits); // Add label to panel
    }

    // Create panel with back button
    private void createBackPanel() {

        back.setText("Back"); // Add text to button

        // Set back button behavior on click
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                center.removeAll();                   // Remove all components from center panel
                @SuppressWarnings("unused")
                Home home = new Home(window, center); // Switch to home screen
            }
        });

        back.addMouseListener(new MouseAdapter() {

            // Set back button behavior on hover
            public void mouseEntered(MouseEvent evt) {
                if (back.isEnabled()) {
                    back.setBackground(new Color(Colors.MEDIUM_PURPLE_50)); // Change color to medium purple
                    back.setFont(SustainabilityScramble.avenirDemi);        // Change font family to Avenir Demi
                    back.setFont(back.getFont().deriveFont(20.0f));   // Scale new font to 20 point
                }
            }
            
            // Set back button behavior on cursor exit
            public void mouseExited(MouseEvent evt) {
                if (back.isEnabled()) {
                    back.setBackground(new Color(Colors.WHITE));          // Revert the button color to white
                    back.setFont(SustainabilityScramble.avenirRegular);   // Revert the font family to Avenir Regular
                    back.setFont(back.getFont().deriveFont(20.0f)); // Scale new font to 20 point
                }
            }
        });

        backPanel.add(back); // Add button to panel
    }

    // Add subpanels to central panel
    private void addPanels() {
        center.setLayout(new BorderLayout());          // Set center panel to a border layout
        center.add(headerPanel, BorderLayout.NORTH);   // Add header text to top of center panel 
        center.add(creditsPanel, BorderLayout.CENTER); // Add credits text to middle of center panel
        center.add(backPanel, BorderLayout.SOUTH);     // Add back button to bottom of center panel
    }
}
