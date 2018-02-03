package r09swing.p12_wlasne_okno_dialogowe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner) {
        super(owner, "Test okna o programie", true);
        add(new JLabel("<html><h1>Tekst H1</h1><hr>A tu już zwykły</html>"), BorderLayout.CENTER);

        JButton ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        //Przycisk ok na dole
        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        pack();
    }
}
