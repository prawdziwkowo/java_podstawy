package r07grafika.sizedFrame;

import javax.swing.*;
import java.awt.*;

public class SizedFrame extends JFrame {
    public SizedFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setSize(screenWidth/2, screenHeight/2);
        setLocationByPlatform(true);
    }
}
