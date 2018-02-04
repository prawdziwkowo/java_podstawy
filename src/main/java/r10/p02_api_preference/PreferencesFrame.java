package r10.p02_api_preference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.prefs.Preferences;

public class PreferencesFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public PreferencesFrame() {
        Preferences root = Preferences.userRoot();
        final Preferences node = root.node("pl/it-developers/java");
        int left = node.getInt("left", 0);
        int top = node.getInt("top", 0);
        int width = node.getInt("width", DEFAULT_WIDTH);
        int height = node.getInt("width", DEFAULT_HEIGHT);

        setBounds(left, top, width, height);

        String title = node.get("title", "");
        if (title.equals("")) {
            title = JOptionPane.showInputDialog("Wpisz tytuł");;
            if (title == null) {
                title = "";
            }
        }
        setTitle(title);

        //plik xml z preferencji, final aby można było przekazać do listenera
        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        //akceptacja wszystkich plików xml
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
            }

            public String getDescription() {
                return "XML files";
            }
        });

        //menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Plik");
        menuBar.add(menu);

        JMenuItem exportItem = new JMenuItem("Eksport ustawień");
        menu.add(exportItem);

        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chooser.showSaveDialog(PreferencesFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        OutputStream out = new FileOutputStream(chooser.getSelectedFile());
                        node.exportSubtree(out);
                        out.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JMenuItem importItem = new JMenuItem("Import ustawień");
        menu.add(importItem);

        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chooser.showOpenDialog(PreferencesFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        InputStream in = new FileInputStream(chooser.getSelectedFile());
                        Preferences.importPreferences(in);
                        in.close();
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Zamknij");
        menu.addSeparator();
        menu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                node.putInt("left", getX());
                node.putInt("top", getY());
                node.putInt("width", getWidth());
                node.putInt("height", getHeight());
                node.put("title", getTitle());
                System.exit(0);
            }
        });
    }
}
