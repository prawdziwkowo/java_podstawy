package r08obslugazdarzen.p02_klasy_wewnetrzne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ButtonFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //dodanie przycisków do panelu
        buttonPanel = new JPanel();
        makeButton("żółty", Color.YELLOW);
        makeButton("niebieski", Color.BLUE);
        makeButton("czerwony", Color.RED);

        //dodanie panelu do ramki
        add(buttonPanel);
    }


    private void makeButton(String name, final Color backgroundColor) {
        JButton button = new JButton(name);
        buttonPanel.add(button);
        //konstuktor nie jest potrzebny, finalne zmienne wykorzystywane są przesyłane automatycznie
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setBackground(backgroundColor);
            }
        });
    }
}
