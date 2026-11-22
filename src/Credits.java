
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Credits {
    
    JFrame window;
    JPanel center;

    JPanel north;
    JPanel middle;
    JPanel south;

    JLabel title;
    JLabel credits;
    JButton back;

    public Credits(JFrame window, JPanel center) {
        this.window = window;
        this.center = center;
        north = new JPanel();
        middle = new JPanel();
        south = new JPanel();
        title = new JLabel();
        credits = new JLabel();
        back = new JButton();
        init();
    }

    private void init() {
        addTitle(north);
        addCredits(middle);
        addBackButton(south);
        addPanels();
        window.repaint();
    }

    private void fini() {
        center.remove(north);
        center.remove(middle);
        center.remove(south);
        window.repaint();
    }

    private void addTitle(JPanel panel) {
        title.setText("Credits");
        panel.add(title);
    }

    private void addCredits(JPanel panel) {

        List<String> lines;
        String path = "/assets/Credits.txt";
        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalArgumentException();
            }
            lines = new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        StringBuilder creditsText = new StringBuilder("<html>");
        for (String line : lines) {
            creditsText.append(line);
            creditsText.append("<br>");
        }
        creditsText.append("</html>");
        credits.setText(creditsText.toString());

        panel.add(credits);
    }

    private void addBackButton(JPanel panel) {

        back.setText("Back");
        back.setFocusable(false);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fini();
                @SuppressWarnings("unused")
                Home home = new Home(window, center); // Switch to the home screen
            }
        });
        
        panel.add(back);
    }

    private void addPanels() {
        center.setLayout(new BorderLayout());
        center.add(north, BorderLayout.NORTH);
        center.add(middle, BorderLayout.CENTER);
        center.add(south, BorderLayout.SOUTH);
        window.revalidate();
    }
}
