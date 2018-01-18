package r09swing.p04_radio;

import javax.swing.*;
import java.awt.*;

public class Draw {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new RadioFrame();
                frame.setTitle("radyjka");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
