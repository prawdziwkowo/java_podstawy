package r09swing.p13_okna_dialogowe_wymiana_danych;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Ramka z menu, kt�rego akcja Plik->Po��cz wy�wietla okno dialogowe z polem has�a
 */
public class DataExchangeFrame extends JFrame
{
   public static final int TEXT_ROWS = 20;
   public static final int TEXT_COLUMNS = 40;
   private PasswordChooser dialog = null;
   private JTextArea textArea;

   public DataExchangeFrame()
   {
      // Tworzenie menu Plik

      JMenuBar mbar = new JMenuBar();
      setJMenuBar(mbar);
      JMenu fileMenu = new JMenu("Plik");
      mbar.add(fileMenu);

      // Tworzenie element�w menu Po��cz i Zamknij

      JMenuItem connectItem = new JMenuItem("Połącz");
      connectItem.addActionListener(new ConnectAction());
      fileMenu.add(connectItem);

      // Opcja Zamknij zamyka program

      JMenuItem exitItem = new JMenuItem("Zamknij");
      exitItem.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0);
            }
         });
      fileMenu.add(exitItem);

      textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      add(new JScrollPane(textArea), BorderLayout.CENTER);
      pack();
   }

   /**
    * Akcja Connect wy�wietla okno dialogowe z polem has�a
    */

   private class ConnectAction implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         // Je�li jest to pierwszy raz, tworzy okno dialogowe

         if (dialog == null) dialog = new PasswordChooser();

         // Ustawianie warto�ci domy�lnych
         dialog.setUser(new User("yourname", null));

         // Wy�wietlenie okna dialogowego
         if (dialog.showDialog(DataExchangeFrame.this, "Connect"))
         {
            // Pobranie danych u�ytkownika w przypadku zatwierdzenia
            User u = dialog.getUser();
            textArea.append("nazwa użytkownika = " + u.getName() + ", hasło = "
                  + (new String(u.getPassword())) + "\n");
         }
      }
   }
}