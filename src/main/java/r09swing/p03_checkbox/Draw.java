package r09swing.p03_checkbox;

import javax.swing.*;
import java.awt.*;

public class Draw {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new CheckBoxFrame();
                frame.setTitle("pudełka zaczekowane");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
