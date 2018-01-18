package r09swing.p02_pola_tekstowe;

import javax.swing.*;
import java.awt.*;

public class Draw {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new TextComponentFrame();
                frame.setTitle("Tekstściąnko");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
