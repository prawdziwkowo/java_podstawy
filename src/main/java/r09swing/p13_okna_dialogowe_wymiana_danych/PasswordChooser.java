package r09swing.p13_okna_dialogowe_wymiana_danych;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Elementy s?u??ce do podania has?a, kt?re wida? w oknie dialogowym
 */
public class PasswordChooser extends JPanel
{
   private JTextField username;
   private JPasswordField password;
   private JButton okButton;
   private boolean ok;
   private JDialog dialog;

   public PasswordChooser()
   {
      setLayout(new BorderLayout());

      // Utworzenie panelu z polami nazwy u?ytkownika i has?a

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(2, 2));
      panel.add(new JLabel("User name:"));
      panel.add(username = new JTextField(""));
      panel.add(new JLabel("Password:"));
      panel.add(password = new JPasswordField(""));
      add(panel, BorderLayout.CENTER);

      // Utworzenie przycisk?w OK i Anuluj, kt?re zamykaj? okno dialogowe

      okButton = new JButton("Ok");
      okButton.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               ok = true;
               dialog.setVisible(false);
            }
         });

      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               dialog.setVisible(false);
            }
         });

      // Dodawanie przycisk?w w pobli?u po?udniowej kraw?dzi

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(okButton);
      buttonPanel.add(cancelButton);
      add(buttonPanel, BorderLayout.SOUTH);
   }

   /**
    * Ustawia warto?ci domy?lne okna dialogowego
    * @param u domy?lne informacje u?ytkownika
    */
   public void setUser(User u)
   {
      username.setText(u.getName());
   }

   /**
    * Pobiera dane podane w oknie dialogowym
    * @return a obiekt typu User, kt?rego stan reprezentuje dane wprowadzone w oknie dialogowym
    */
   public User getUser()
   {
      return new User(username.getText(), password.getPassword());
   }

   /**
    * Wy?wietla panel z elementami przyjmuj?cymi dane od u?ytkownika w oknie dialogowym
    * @param parent komponent w ramce nadrz?dnej lub warto?? null
    * @param title tytu? okna dialogowego
    */
   public boolean showDialog(Component parent, String title)
   {
      ok = false;

      // Lokalizacja ramki nadrz?dnej

      Frame owner = null;
      if (parent instanceof Frame) owner = (Frame) parent;
      else owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

      // Je?li jest to pierwszy raz lub zmieni? si? u?ytkownik, utworzenie nowego okna dialogowego

      if (dialog == null || dialog.getOwner() != owner)
      {
         dialog = new JDialog(owner, true);
         dialog.add(this);
         dialog.getRootPane().setDefaultButton(okButton);
         dialog.pack();
      }

      // Ustawienie tytu?u i wy?wietlenie okna dialogowego

      dialog.setTitle(title);
      dialog.setVisible(true);
      return ok;
   }
}
