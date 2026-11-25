
import java.awt.*;

public class SelectorButton extends CustomButton {
    
    private boolean selected = false; // Whether the button is selected
    
    public SelectorButton() {
        super();
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (!selected) {
            setBackground(new Color(Colors.WHITE));
        }
    }
    
    public boolean isSelected() {
        return selected;
    }
}
