package r09swing.p08_menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MenuFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private Action saveAction;
    private Action saveAsAction;
    private JCheckBoxMenuItem readOnlyItem;
    private JPopupMenu popup;

    public MenuFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenu fileMenu = new JMenu("Plik");
        fileMenu.add(new TestAction("Nowy"));

        //Akceleratory
        JMenuItem openItem = fileMenu.add(new TestAction("Otwórz"));
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));

        fileMenu.addSeparator();

        saveAction = new TestAction("Zapisz");
        JMenuItem saveItem = fileMenu.add(saveAction);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl + S"));

        saveAsAction = new TestAction("Zapisz jako");
        fileMenu.add(saveAsAction);

        fileMenu.addSeparator();

        fileMenu.add(new AbstractAction("Zakończ") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //Menu z polem wybory i przełącznikami

        readOnlyItem = new JCheckBoxMenuItem("Tylko do odczytu");
        readOnlyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean saveOk = !readOnlyItem.isSelected();
                saveAction.setEnabled(saveOk);
                saveAsAction.setEnabled(saveOk);
            }
        });

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Wstawianie");
        insertItem.setSelected(true);
        JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Nadpisywanie");

        group.add(insertItem);
        group.add(overtypeItem);

        //ikony
        Action cutAction = new TestAction("Wytnij");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/cut.gif")));
        Action copyAction = new TestAction("Kopiuj");
        copyAction.putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/copy.gif")));
        Action pasteAction = new TestAction("Wklej");
        pasteAction.putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/paste.gif")));

        JMenu editMenu = new JMenu("Edycja");
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);

        //Zagnieżdżone menu
        JMenu optionMenu = new JMenu("Opcje");
        optionMenu.add(readOnlyItem);
        optionMenu.addSeparator();
        optionMenu.add(insertItem);
        optionMenu.add(overtypeItem);

        editMenu.addSeparator();
        editMenu.add(optionMenu);

        //Mnemoniki
        JMenu helpMenu = new JMenu("Pomoc");
        helpMenu.setMnemonic('P');

        JMenuItem indexItem = new JMenuItem("Index");
        indexItem.setMnemonic('I');
        helpMenu.add(indexItem);

        //Mnemoniki do akcji
        Action aboutAction = new TestAction("O programie");
        aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('O'));
        helpMenu.add(aboutAction);

        //dodanie menu do paska menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        //menu kontekstowe

        popup = new JPopupMenu();
        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);

        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popup);
        add(panel);

        //to jest obejscie błędu javy
        panel.addMouseListener(new MouseAdapter() {
        });
    }

    private class TestAction extends AbstractAction {
        public TestAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("Wybrano " + getValue(Action.NAME));
        }
    }
}
