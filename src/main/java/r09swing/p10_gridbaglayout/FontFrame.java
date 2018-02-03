package r09swing.p10_gridbaglayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class FontFrame extends JFrame {
    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLS = 20;

    private JComboBox<String> face;
    private JComboBox<Integer> size;
    private JCheckBox bold;
    private JCheckBox italic;
    private JTextArea sample;

    public FontFrame() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        ActionListener listener = EventHandler.create(ActionListener.class, this, "updateSample");

        //Tworzenie komponentów
        JLabel faceLabel = new JLabel("Krój: ");

        face = new JComboBox<String>(new String[] {"Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"});
        face.addActionListener(listener);

        JLabel sizeLabel = new JLabel("Rozmiar: ");

        size = new JComboBox<Integer>(new Integer[]{8, 10, 12, 14, 16, 24, 36, 48});
        size.addActionListener(listener);

        bold = new JCheckBox("Pogrubienie");
        bold.addActionListener(listener);

        italic = new JCheckBox("Kursywa");
        italic.addActionListener(listener);

        sample = new JTextArea(TEXT_ROWS, TEXT_COLS);
        sample.setText("Lorem ipsum dolot coś tam zażółć gęśł żółtą jąźń czy coś takiego");
        sample.setEditable(false);
        sample.setLineWrap(true);
        sample.setBorder(BorderFactory.createEtchedBorder());

        //dodawanie komponentów do siatki, za pomocą klasy pomocniczej

        add(faceLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        add(face, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
        add(sizeLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        add(size, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));

        add(bold, new GBC(0, 2, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        add(italic, new GBC(0, 3, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));

        add(sample, new GBC(2, 0, 1, 4).setFill(GBC.BOTH).setWeight(100, 100));

        pack();
        updateSample();
    }

    public void updateSample() {
        String fontFace = (String) face.getSelectedItem();
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (italic.isSelected() ? Font.ITALIC : 0);

        int fontSize = size.getItemAt(size.getSelectedIndex());

        Font font = new Font(fontFace, fontStyle, fontSize);

        sample.setFont(font);
        sample.repaint();
    }
}
