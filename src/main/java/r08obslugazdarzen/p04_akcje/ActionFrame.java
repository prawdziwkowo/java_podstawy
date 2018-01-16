package r08obslugazdarzen.p04_akcje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActionFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ActionFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        //definicja akcji
        Action yellowAction = new ColorAction(
                "żółty",
                new ImageIcon(this.getClass().getResource("/bullet_ball_yellow.png")),
                Color.YELLOW
        );
        Action blueAction = new ColorAction(
                "niebieski",
                new ImageIcon(this.getClass().getResource("/bullet_ball_blue.png")),
                Color.BLUE
        );
        Action redAction = new ColorAction(
                "czerwony",
                new ImageIcon(this.getClass().getResource("/bullet_ball_red.png")),
                Color.RED
        );

        //dodanie przycisków do akcji
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));

        //dodanie panelu do ramki
        add(buttonPanel);

        //powiazanie klawiszy
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl Z"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl N"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl C"), "panel.red");

        //powiazanie nazw z akcjami
        ActionMap actionMap = buttonPanel.getActionMap();
        actionMap.put("panel.yellow", yellowAction);
        actionMap.put("panel.blue", blueAction);
        actionMap.put("panel.red", redAction);
    }

    public class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "Ustaw kolor panelu na " + name.toLowerCase());
            putValue("color", c);
        }

        public void actionPerformed(ActionEvent e) {
            Color c = (Color)getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}
