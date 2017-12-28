package r07grafika.simpleFrame;

import javax.swing.*;

public class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//        setBounds(100, 500, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Moja pierwsza ramka do jasnej ciasnej anielki");
//        setLocationByPlatform(true);

        ImageIcon ii = new ImageIcon(this.getClass().getResource("/1up-mushroom.png"));
        setIconImage(ii.getImage());
    }
}
