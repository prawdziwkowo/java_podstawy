package r09swing.p05_obramowania;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderFrame extends JFrame {
    private JPanel demoPanel;
    private JPanel buttonPanel;
    private ButtonGroup group;

    public BorderFrame() {
        demoPanel = new JPanel();
        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadio("Lowered bevel", BorderFactory.createLoweredBevelBorder());
        addRadio("Raised bevel", BorderFactory.createRaisedBevelBorder());
        addRadio("Etched", BorderFactory.createEtchedBorder());
        addRadio("Line", BorderFactory.createLineBorder(Color.RED));
        addRadio("Matte", BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
        addRadio("Empty", BorderFactory.createEmptyBorder());

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Wybierz rodzaj obramowanka");
        buttonPanel.setBorder(titled);

        setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        add(demoPanel);
        pack();
    }

    private void addRadio(String name, final Border border) {
        JRadioButton button = new JRadioButton(name);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                demoPanel.setBorder(border);
            }
        });
        group.add(button);
        buttonPanel.add(button);
    }
}
