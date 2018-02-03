package r09swing.p12_wlasne_okno_dialogowe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private AboutDialog dialog;

    public DialogFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //Tworzenie menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Plik");
        menuBar.add(fileMenu);

        //o programie
        JMenuItem aboutItem = new JMenuItem("Dialog");
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dialog == null) {
                    dialog = new AboutDialog(DialogFrame.this);
                }
                dialog.setVisible(true);
            }
        });
        fileMenu.add(aboutItem);

        //element zamyknij
        JMenuItem closeItem = new JMenuItem("Zamknij");
        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(closeItem);
    }
}
