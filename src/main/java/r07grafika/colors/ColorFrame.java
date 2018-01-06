package r07grafika.colors;

import javax.swing.*;
import java.awt.*;

public class ColorFrame extends JFrame {
    public ColorFrame() {
        JComponent component = new ColorComponent();
        add(component);
        pack();
    }
}
