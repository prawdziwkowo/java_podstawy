package r14_watki.p03_swing_threads;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Program udowadniający, że wątek działający równolegle z wątkiem dystrybucji zdarzeń może powodować błędy w komponentach Swing

 * cause errors in Swing components.
 * @version 1.23 2007-05-17
 * @author Cay Horstmann
 */
public class SwingThreadTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new SwingThreadFrame();
                frame.setTitle("SwingThreadTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Ramka mająca dwa przyciski służące do zapełniania listy w osobnym wątku. Przycisk Dobry wykorzystuje
 * kolejkę zdarzeń, a Zły modyfikuje listę bezpośrednio.
 */

class SwingThreadFrame extends JFrame
{
    public SwingThreadFrame()
    {
        final JComboBox<Integer> combo = new JComboBox<>();
        combo.insertItemAt(Integer.MAX_VALUE, 0);
        combo.setPrototypeDisplayValue(combo.getItemAt(0));
        combo.setSelectedIndex(0);

        JPanel panel = new JPanel();

        JButton goodButton = new JButton("Dobry");
        goodButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                new Thread(new GoodWorkerRunnable(combo)).start();
            }
        });
        panel.add(goodButton);
        JButton badButton = new JButton("Zły");
        badButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                new Thread(new BadWorkerRunnable(combo)).start();
            }
        });
        panel.add(badButton);

        panel.add(combo);
        add(panel);
        pack();
    }
}

/**
 * Klasa modyfikująca listę rozwijaną, dodając do niej i usuwając z niej losowe liczby. Może to spowodować błędy,
 * ponieważ metody listy rozwijalnej nie są synchronizowane, przez co wątek roboczy i wątek dystrybucji zdarzeń
 * uzyskują dostęp do tej listy.
 */

class BadWorkerRunnable implements Runnable
{
    private JComboBox<Integer> combo;
    private Random generator;

    public BadWorkerRunnable(JComboBox<Integer> aCombo)
    {
        combo = aCombo;
        generator = new Random();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                int i = Math.abs(generator.nextInt());
                if (i % 2 == 0) combo.insertItemAt(i, 0);
                else if (combo.getItemCount() > 0) combo.removeItemAt(i % combo.getItemCount());
                Thread.sleep(1);
            }
        }
        catch (InterruptedException e)
        {
        }
    }
}

/**
 * Klasa modyfikująca listę rozwijaną, dodając do niej i usuwając z niej losowe liczby. Aby uniknąć
 * uszkodzenia tej listy, operacje edycji są przesyłane do wątku dystrybucji zdarzeń.
 */
class GoodWorkerRunnable implements Runnable
{
    private JComboBox<Integer> combo;
    private Random generator;

    public GoodWorkerRunnable(JComboBox<Integer> aCombo)
    {
        combo = aCombo;
        generator = new Random();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                EventQueue.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        int i = Math.abs(generator.nextInt());
                        if (i % 2 == 0) combo.insertItemAt(i, 0);
                        else if (combo.getItemCount() > 0) combo.removeItemAt(i
                                % combo.getItemCount());
                    }
                });
                Thread.sleep(1);
            }
        }
        catch (InterruptedException e)
        {
        }
    }
}