package r09swing.p02_pola_tekstowe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextComponentFrame extends JFrame {
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLS = 20;

    public TextComponentFrame() {
        final JTextField textField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.add(new JLabel("Nazwa użytkownika: ", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Hasło: ", SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLS);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton button = new JButton("Wstaw");
        southPanel.add(button);

        //wszystkie zmienne final są przekazane do tej klasy
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textArea.getText().length() > 0) {
                    textArea.append("\n");
                }
                textArea.append("Nazwa użytkownika: " + textField.getText() +
                        "\nHasło: " + new String(passwordField.getPassword()) + "\n"
                );
            }
        });

        add(southPanel, BorderLayout.SOUTH);
        pack();
    }
}
