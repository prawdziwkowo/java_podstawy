package r14_watki.p04_swing_worker;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

import javax.swing.*;

/**
 * Program demonstrujący wątek roboczy wykonujący potencjalnie czasochłonne zadanie
 * @version 1.1 2007-05-18
 * @author Cay Horstmann
 */
public class SwingWorkerTest
{
    public static void main(String[] args) throws Exception
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new SwingWorkerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Ramka mająca obszar tekstowy pokazujący zawartość pliku tekstowego, menu pozwalające otworzyć plik
 * i anulować proces otwierania pliku oraz wiersz stanu pokazujący postęp ładowania pliku
 */
class SwingWorkerFrame extends JFrame
{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder, ProgressData> textReader;
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 60;

    public SwingWorkerFrame()
    {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statusLine = new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Plik");
        menuBar.add(menu);

        openItem = new JMenuItem("Otwórz");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                // Wyświetlenie okna dialogowego wyboru pliku
                int result = chooser.showOpenDialog(null);

                // Jeśli plik został wybrany, zostanie on ustawiony jako ikona etykiety
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    textArea.setText("");
                    openItem.setEnabled(false);
                    textReader = new TextReader(chooser.getSelectedFile());
                    textReader.execute();
                    cancelItem.setEnabled(true);
                }
            }
        });

        cancelItem = new JMenuItem("Anuluj");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                textReader.cancel(true);
            }
        });
        pack();
    }

    private class ProgressData
    {
        public int number;
        public String line;
    }

    private class TextReader extends SwingWorker<StringBuilder, ProgressData>
    {
        private File file;
        private StringBuilder text = new StringBuilder();

        public TextReader(File file)
        {
            this.file = file;
        }

        // Poniższa metoda jest wykonywana w wątku roboczym — nie operuje na komponentach Swing

        @Override
        public StringBuilder doInBackground() throws IOException, InterruptedException
        {
            int lineNumber = 0;
            try (Scanner in = new Scanner(new FileInputStream(file)))
            {
                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    lineNumber++;
                    text.append(line);
                    text.append("\n");
                    ProgressData data = new ProgressData();
                    data.number = lineNumber;
                    data.line = line;
                    publish(data);
                    Thread.sleep(1); // Test operacji anulowania, nie ma potrzeby robienia tego w swoich programach
                }
            }
            return text;
        }

        // Poniższe metody są wykonywane w wątku dystrybucji zdarzeń

        @Override
        public void process(List<ProgressData> data)
        {
            if (isCancelled()) return;
            StringBuilder b = new StringBuilder();
            statusLine.setText("" + data.get(data.size() - 1).number);
            for (ProgressData d : data)
            {
                b.append(d.line);
                b.append("\n");
            }
            textArea.append(b.toString());
        }

        @Override
        public void done()
        {
            try
            {
                StringBuilder result = get();
                textArea.setText(result.toString());
                statusLine.setText("Zakończono");
            }
            catch (InterruptedException ex)
            {
            }
            catch (CancellationException ex)
            {
                textArea.setText("");
                statusLine.setText("Anulowano");
            }
            catch (ExecutionException ex)
            {
                statusLine.setText("" + ex.getCause());
            }

            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }

    };
}
