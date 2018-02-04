package r10.p02_api_preference;

import javax.swing.*;
import java.awt.*;

//UWAGA: to raczej nie działa poprawnie w windows 10
//jak będziesz chciał używać, to sprawdzić, czy błąd został naprawiony
public class Draw {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new PreferencesFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
