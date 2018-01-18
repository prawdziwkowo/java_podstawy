package r09swing.p04_radio;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioFrame extends JFrame {
    private JPanel buttonPanel;
    private ButtonGroup group;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;

    public RadioFrame() {
        label = new JLabel("Siakiś tekścior do wyświetlonka", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("Maciupinka odrobinka", 8);
        addRadioButton("Większa niż maciupinka", 12);
        addRadioButton("Całkiem niczego sobie", 18);
        addRadioButton("Wielgachna", 36);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void addRadioButton(String name, final int size) {
        boolean selected = size == DEFAULT_SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        group.add(button);
        buttonPanel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font("Serif", Font.PLAIN, size));
            }
        });
    }
}
