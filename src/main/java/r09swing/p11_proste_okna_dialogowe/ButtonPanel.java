package r09swing.p11_proste_okna_dialogowe;

import javax.swing.*;

/**
 * Panel z prze��cznikami w ramce z tytu�em
 */
public class ButtonPanel extends JPanel
{
   private ButtonGroup group;

   /**
    * Tworzy panel przycisk�w
    * @param title Tytu� wy�wietlany w obramowaniu
    * @param options Tablica etykiet prze��cznik�w
    */
   public ButtonPanel(String title, String... options)
   {
      setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      group = new ButtonGroup();

      // Utworzenie po jednym prze��czniku dla ka�dej opcji
      for (String option : options)
      {
         JRadioButton b = new JRadioButton(option);
         b.setActionCommand(option);
         add(b);
         group.add(b);
         b.setSelected(option == options[0]);
      }
   }

   /**
    * Pobiera aktualnie wybran� opcj�
    * @return Zwraca etykiet� aktualnie wybranego prze��cznika
    */
   public String getSelection()
   {
      return group.getSelection().getActionCommand();
   }
}

