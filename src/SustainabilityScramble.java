
import javax.swing.*;
import java.awt.*;

public class SustainabilityScramble {
	
	private JFrame window; // The application window
    private JPanel center; // The top border of the window
    private JPanel north;  // The bottom border
    private JPanel south;  // The left border
    private JPanel east;   // The right border
    private JPanel west;   // The area within the borders

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
        setWindowParameters();
        createPanels();
        addPanels();

        @SuppressWarnings("unused")
        Home home = new Home(window, center); // Open the application on the home screen
    }

    private void setWindowParameters() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ends program execution if the window is closed
        window.setTitle("Sustainability Scramble");
        window.setSize(800, 600);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
	}
    
    private void createPanels() {
        createNorthPanel();
        createSouthPanel();
        createEastPanel();
        createWestPanel();
    }
    private void createNorthPanel() {
        north.setPreferredSize(new Dimension(window.getWidth(), 50));
        north.setBackground(Color.RED);
    }
    private void createSouthPanel() {
        south.setPreferredSize(new Dimension(window.getWidth(), 50));
        south.setBackground(Color.RED);
    }
    private void createEastPanel() {
        east.setPreferredSize(new Dimension(50, window.getHeight()));
        east.setBackground(Color.BLUE);
    }
    private void createWestPanel() {
        west.setPreferredSize(new Dimension(50, window.getHeight()));
        west.setBackground(Color.BLUE);
    }
    
    private void addPanels() {
        window.add(center, BorderLayout.CENTER);
        window.add(north, BorderLayout.NORTH);
        window.add(south, BorderLayout.SOUTH);
        window.add(east, BorderLayout.EAST);
        window.add(west, BorderLayout.WEST);
    }
}