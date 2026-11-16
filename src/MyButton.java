import javax.swing.JButton;

public class MyButton extends JButton {
    
    private boolean selected = false;
    
    public MyButton(String text) {

        super(text);
        this.setFocusable(false);
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (!selected) {
            setBackground(null);
        }
    }
    
    public boolean isSelected() {
        
        if (selected) {
            return true;
        }
        
        return false;
    }
}
