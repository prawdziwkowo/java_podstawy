package r09swing.p01_calculator_layouty;

import javax.swing.*;
import java.awt.*;

public class Draw {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new CalculatorPanel();
                frame.setTitle("Kalkulatorątko");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
