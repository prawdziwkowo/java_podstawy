package r07grafika.images;

import javax.swing.*;
import java.awt.*;

public class ImageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ImageFrame();
                frame.setTitle("Teścik obraześcik");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
