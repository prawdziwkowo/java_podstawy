package r10.p01_preferencje_properties;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Properties;

public class PropertiesFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private File propertiesFile;
    private Properties settings;

    public PropertiesFrame() {
        String userDir = System.getProperty("user.home");
        File propertiesDir = new File(userDir, ".myjava");
        if (!propertiesDir.exists()) {
            propertiesDir.mkdir();
        }
        System.out.println(propertiesDir);
        propertiesFile = new File(propertiesDir, "program.properties");

        Properties defaultSettings = new Properties();
        defaultSettings.put("left", "0");
        defaultSettings.put("top", "0");
        defaultSettings.put("width", "" + DEFAULT_WIDTH);
        defaultSettings.put("height", "" + DEFAULT_HEIGHT);
        defaultSettings.put("title", "");

        settings = new Properties(defaultSettings);
        if (propertiesFile.exists()) {
            try {
                FileInputStream in = new FileInputStream(propertiesFile);
                settings.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int left = Integer.parseInt(settings.getProperty("left"));
        int top = Integer.parseInt(settings.getProperty("top"));
        int width = Integer.parseInt(settings.getProperty("width"));
        int height = Integer.parseInt(settings.getProperty("height"));

        setBounds(left, top, width, height);

        //Jeśli nie ma tytułu, to użytkownik go ddodaje
        String title = settings.getProperty("title");
        if (title.equals("")) {
            title = JOptionPane.showInputDialog("Wpisz tytuł");
            if (title == null) {
                title = "";
            }
        }
        setTitle(title);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                settings.put("left", "" + getX());
                settings.put("top", "" + getY());
                settings.put("width", "" + getWidth());
                settings.put("height", "" + getHeight());
                settings.put("title", "" + getTitle());
                try {
                    FileOutputStream out = new FileOutputStream(propertiesFile);
                    settings.store(out, "Ustawienia programu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
