package r09swing.p15_okna_wyboru_kolorow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Panel z przyciskami uruchamiaj�cymi trzy typy okien
 */
public class ColorChooserPanel extends JPanel
{
   public ColorChooserPanel()
   {
      JButton modalButton = new JButton("Modalne");
      modalButton.addActionListener(new ModalListener());
      add(modalButton);

      JButton modelessButton = new JButton("Niemodalne");
      modelessButton.addActionListener(new ModelessListener());
      add(modelessButton);

      JButton immediateButton = new JButton("Bezpośrednie");
      immediateButton.addActionListener(new ImmediateListener());
      add(immediateButton);
   }

   /**
    * Ten s�uchacz wy�wietla okno modalne.
    */
   private class ModalListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         Color defaultColor = getBackground();
         Color selected = JColorChooser.showDialog(ColorChooserPanel.this, "Ustaw kolor tła",
               defaultColor);
         if (selected != null) setBackground(selected);
      }
   }

   /**
    * Ten s�uchacz wy�wietla okno niemodalne. Kolor t�a panelu zmienia si� po
    * klikni�ciu przycisku OK.
    */
   private class ModelessListener implements ActionListener
   {
      private JDialog dialog;
      private JColorChooser chooser;

      public ModelessListener()
      {
         chooser = new JColorChooser();
         dialog = JColorChooser.createDialog(ColorChooserPanel.this, "Kolor tła",
               false /* niemodalne */, chooser, new ActionListener()   // S�uchacz przycisku OK
                  {
                     public void actionPerformed(ActionEvent event)
                     {
                        setBackground(chooser.getColor());
                     }
                  }, null /* Brak s�uchacza dla przycisku Cancel. */);
      }

      public void actionPerformed(ActionEvent event)
      {
         chooser.setColor(getBackground());
         dialog.setVisible(true);
      }
   }

   /**
    * Ten s�uchacz wy�wietla okno niemodalne. Kolor t�a panelu zmienia si� bezpo�rednio 
    * po wybraniu przez u�ytkownika koloru.
    */
   private class ImmediateListener implements ActionListener
   {
      private JDialog dialog;
      private JColorChooser chooser;

      public ImmediateListener()
      {
         chooser = new JColorChooser();
         chooser.getSelectionModel().addChangeListener(new ChangeListener()
            {
               public void stateChanged(ChangeEvent event)
               {
                  setBackground(chooser.getColor());
               }
            });

         dialog = new JDialog((Frame) null, false /* niemodalne */);
         dialog.add(chooser);
         dialog.pack();
      }

      public void actionPerformed(ActionEvent event)
      {
         chooser.setColor(getBackground());
         dialog.setVisible(true);
      }
   }
}