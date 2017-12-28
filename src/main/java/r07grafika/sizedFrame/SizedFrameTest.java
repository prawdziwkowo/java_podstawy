package r07grafika.sizedFrame;

import javax.swing.*;
import java.awt.*;

public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new SizedFrame();
                frame.setTitle("Sized");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                ImageIcon ii = new ImageIcon(this.getClass().getResource("/1up-mushroom.png"));
                frame.setIconImage(ii.getImage());

//                frame.setExtendedState(Frame.MAXIMIZED_BOTH);//automatyczna maksymalizacja

                frame.setVisible(true);
            }
        });
    }
}
