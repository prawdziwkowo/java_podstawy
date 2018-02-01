package r09swing.p07_suwaki;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class SliderFrame extends JFrame {
    private JPanel sliderPanel;
    private JTextField textField;
    private ChangeListener listener;

    public SliderFrame() {
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridBagLayout());

        //słuchacz dla suwaków
        listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                textField.setText("" + source.getValue());
            }
        };

        //zwykły suwak
        JSlider slider = new JSlider();
        addSlider(slider, "Zwyklaczek");

        //z podziałką
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Podziałkowany");

        //Suwak z dosuwanie do najbliższej kreski
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Dosuwowany");

        //bez prowadnicy
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintTrack(false);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Bez prowadnicy");

        //odwrócony
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setInverted(true);
        addSlider(slider, "inverted");

        //etykiety liczbowe
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Etykietowany");

        //etykiety literkowe
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel("A"));
        labelTable.put(20, new JLabel("B"));
        labelTable.put(40, new JLabel("C"));
        labelTable.put(60, new JLabel("D"));
        labelTable.put(80, new JLabel("E"));
        labelTable.put(100, new JLabel("F"));

        slider.setLabelTable(labelTable);
        addSlider(slider, "Literkowany");

        //ikony
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        labelTable = new Hashtable<Integer, Component>();

        labelTable.put(0, new JLabel(new ImageIcon(this.getClass().getResource("/bullet_ball_yellow.png"))));
        labelTable.put(20, new JLabel(new ImageIcon(this.getClass().getResource("/bullet_ball_blue.png"))));
        labelTable.put(40, new JLabel(new ImageIcon(this.getClass().getResource("/bullet_ball_red.png"))));
        labelTable.put(60, new JLabel(new ImageIcon(this.getClass().getResource("/bullet_ball_red.png"))));
        labelTable.put(80, new JLabel(new ImageIcon(this.getClass().getResource("/bullet_ball_blue.png"))));
        labelTable.put(100, new JLabel(new ImageIcon(this.getClass().getResource("/bullet_ball_yellow.png"))));

        slider.setLabelTable(labelTable);
        addSlider(slider, "Ikonkowany");

        //pole teksciarkie
        textField = new JTextField();
        add(sliderPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        pack();
    }

    private void addSlider(JSlider s, String desc) {
        s.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(desc));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = sliderPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.WEST;
        sliderPanel.add(panel, gbc);
    }
}
