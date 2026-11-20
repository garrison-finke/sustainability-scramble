import javax.swing.JButton;

public class SelectorButton extends JButton {
    
    private boolean selected = false; // Whether the option associated with the button is selected or not
    
    public SelectorButton() {
        super();
        this.setFocusable(false);
    }
    
    // 
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (!selected) {
            setBackground(null);
        }
    }
    
    public boolean isSelected() {
        return selected;
    }
}
