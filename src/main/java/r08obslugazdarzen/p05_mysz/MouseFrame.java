package r08obslugazdarzen.p05_mysz;

import javax.swing.*;

public class MouseFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public MouseFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(new MouseComponent());
    }
}
