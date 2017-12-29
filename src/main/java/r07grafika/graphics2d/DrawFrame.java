package r07grafika.graphics2d;

import javax.swing.*;

public class DrawFrame extends JFrame {
    public DrawFrame() {
        add(new DrawComponent());
        pack();
    }
}
