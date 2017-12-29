package r07grafika.firstPaintComponent;

import javax.swing.*;
import java.awt.*;

public class HelloWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new HelloWorldFrame();
                frame.setTitle("World hello! Kurwa!");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
