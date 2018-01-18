package r09swing.p05_obramowania;

import javax.swing.*;
import java.awt.*;

public class Draw {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new BorderFrame();
                frame.setTitle("border");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
