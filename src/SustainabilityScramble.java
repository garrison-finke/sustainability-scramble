
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SustainabilityScramble {
	
	private JFrame window;             // The application window
    private JPanel center;             // The top border of the window
    private JPanel north;              // The bottom border
    private JPanel south;              // The left border
    private JPanel east;               // The right border
    private JPanel west;               // The area within the borders
    public static Font avenirRegular;  // Avenir Next LT Pro Regular font
    public static Font avenirDemi;     // Avenir Next LT Pr Demi font
    public static Font bodoniRegular;  // BodoniModa Regular font
    public static Font bodoniSemiBold; // BodoniModa SemiBold font

    // Constructor
    public SustainabilityScramble() {
        window = new JFrame();
        north = new JPanel();
        south = new JPanel();
        east = new JPanel();
        west = new JPanel();
        center = new JPanel();
        init();
    }

    // Initializer
    private void init() {
        loadFonts();                          // Load the custom fonts into the class
        setWindowParameters();                // Adjust the window settings
        createPanels();                       // Set sizes and add content to panels
        addPanels();                          // Add the panels to the window
        @SuppressWarnings("unused")
        Home home = new Home(window, center); // Open the application on the home screen
    }
    
    // Load the custom fonts from the classpath
    private void loadFonts() {
        avenirRegular = loadFont("/assets/fonts/Avenir_Next_LT_Pro_Regular.otf"); // Load Avenir Next LT Pro Regular
        avenirDemi = loadFont("/assets/fonts/Avenir_Next_LT_Pro_Demi.otf");       // Load Avenir Next LT Pro Demi
        bodoniRegular = loadFont("/assets/fonts/BodoniModa_9pt-Regular.ttf");     // Load BodoniModa Regular
        bodoniSemiBold = loadFont("/assets/fonts/BodoniModa_9pt-SemiBold.ttf");   // Load BodoniModa SemiBold
    }
    private Font loadFont(String path) {
        try (InputStream in = getClass().getResourceAsStream(path)) {             // Access the font file from the Java classpath
            Font base = Font.createFont(Font.TRUETYPE_FONT, in);                  // Create a base truetype or opentype font
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(base); // Register the font so that Swing can access it
            return base.deriveFont(Font.PLAIN, 11.0f);                       // Return the font defaulted to size 11
        } catch (IOException | FontFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void setFontAvenir(JComponent component, float size) {
        component.setFont(SustainabilityScramble.avenirRegular);                                  // Set font to Avenir regular
        component.setFont(component.getFont().deriveFont(size));                                  // Set font to passed in size
        component.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0)); // Center text vertically in component
    }
    
    // Adjust the window settings
    private void setWindowParameters() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ends program execution if the window is closed
        window.setTitle("Sustainability Scramble");     // Sets the title of the window
        window.setSize(800, 600);               // Sets the size of the window
        window.setLayout(new BorderLayout());                 // Set the window to a border layout
        window.setVisible(true);                            // Ensure that the window is visible
	}

    // Create the window borders and central panel
    private void createPanels() {
        createNorthPanel();
        createSouthPanel();
        createEastPanel();
        createWestPanel();
        createCenterPanel();
    }
    private void createNorthPanel() {
        north.setLayout(new GridBagLayout());                                 // Set panel to grid bag layout
        north.setPreferredSize(new Dimension(window.getWidth(), 25)); // Set size of north panel
        north.setBackground(new Color(Colors.MEDIUM_PURPLE_100));             // Set color of north panel
    }
    private void createSouthPanel() {
        south.setPreferredSize(new Dimension(window.getWidth(), 50));                                                    // Set size of south panel
        south.setBackground(new Color(Colors.MEDIUM_PURPLE_100));                                                               // Set color of south panel
        try (InputStream in = getClass().getResourceAsStream("/assets/logos/st_sig_rev.png")) {
            ImageIcon icon = new ImageIcon(ImageIO.read(in));                                                                   // Read logo in
            ImageIcon iconScaled = new ImageIcon(icon.getImage().getScaledInstance(175, 35, Image.SCALE_SMOOTH)); // Scale logo
            south.add(new JLabel(iconScaled));
        } catch (NullPointerException | IOException e) {
            throw new IllegalArgumentException();
        }
    }
    private void createEastPanel() {
        east.setPreferredSize(new Dimension(25, window.getHeight())); // Set size of east panel
        east.setBackground(new Color(Colors.MEDIUM_PURPLE_100));             // Set color of east panel
    }
    private void createWestPanel() {
        west.setPreferredSize(new Dimension(25, window.getHeight())); // Set size of west panel
        west.setBackground(new Color(Colors.MEDIUM_PURPLE_100));             // Set color of west panel
    }
    private void createCenterPanel() {
        center.setBackground(new Color(Colors.COOL_GRAY_10)); // Set color of central panel
    }

    // Add panels to the window
    private void addPanels() {
        window.add(center, BorderLayout.CENTER); // Add center panel to the window
        window.add(north, BorderLayout.NORTH);   // Add north panel to the window
        window.add(south, BorderLayout.SOUTH);   // Add south panel to the window
        window.add(east, BorderLayout.EAST);     // Add east panel to the window
        window.add(west, BorderLayout.WEST);     // Add west panel to the window
    }
}
