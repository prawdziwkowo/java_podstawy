package r09swing.p03_checkbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxFrame extends JFrame {
    public CheckBoxFrame() {
        final JCheckBox bold = new JCheckBox("Grubość");
        final JCheckBox italic = new JCheckBox("Pochyłość");

        JPanel panel = new JPanel();
        panel.add(bold);
        panel.add(italic);

        final JLabel label = new JLabel("Jakiś tekścik do wyświetlenia", SwingConstants.CENTER);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mode = 0;

                if(bold.isSelected()) mode += Font.BOLD;
                if(italic.isSelected()) mode += Font.ITALIC;

                label.setFont(new Font("Serif", mode, 14));
            }
        };

        bold.addActionListener(listener);
        italic.addActionListener(listener);

        bold.setSelected(true);
        add(label, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        pack();
    }
}
