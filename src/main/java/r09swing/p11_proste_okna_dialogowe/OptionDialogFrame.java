package r09swing.p11_proste_okna_dialogowe;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * Ramka zawierająca ustawienia dotyczące wyboru różnych okien dialogowych opcji
 */
public class OptionDialogFrame extends JFrame
{
   private ButtonPanel typePanel;
   private ButtonPanel messagePanel;
   private ButtonPanel messageTypePanel;
   private ButtonPanel optionTypePanel;
   private ButtonPanel optionsPanel;
   private ButtonPanel inputPanel;
   private String messageString = "Komunikat";
   private Icon messageIcon = new ImageIcon("blue-ball.gif");
   private Object messageObject = new Date();
   private Component messageComponent = new SampleComponent();

   public OptionDialogFrame()
   {
      JPanel gridPanel = new JPanel();
      gridPanel.setLayout(new GridLayout(2, 3));

      typePanel = new ButtonPanel("Typ", "Message", "Confirm", "Option", "Input");
      messageTypePanel = new ButtonPanel("Typ komunikatu", "ERROR_MESSAGE", "INFORMATION_MESSAGE",
            "WARNING_MESSAGE", "QUESTION_MESSAGE", "PLAIN_MESSAGE");
      messagePanel = new ButtonPanel("Komunikat", "String", "Icon", "Component", "Other", "Object[]");
      optionTypePanel = new ButtonPanel("Potwierdzenie", "DEFAULT_OPTION", "YES_NO_OPTION",
            "YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION");
      optionsPanel = new ButtonPanel("Opcja", "String[]", "Icon[]", "Object[]");
      inputPanel = new ButtonPanel("Dane wejściowe", "Text field", "Combo box");

      gridPanel.add(typePanel);
      gridPanel.add(messageTypePanel);
      gridPanel.add(messagePanel);
      gridPanel.add(optionTypePanel);
      gridPanel.add(optionsPanel);
      gridPanel.add(inputPanel);

      // Dodanie panelu z przyciskiem Pokaż

      JPanel showPanel = new JPanel();
      JButton showButton = new JButton("Show");
      showButton.addActionListener(new ShowAction());
      showPanel.add(showButton);

      add(gridPanel, BorderLayout.CENTER);
      add(showPanel, BorderLayout.SOUTH);
      pack();
   }

   /**
    * Pobiera aktualnie wybrany komunikat.
    * @return łańcuch, ikona, komponent lub tablica obiektów, w zależności od wyboru w panelu Komunikat
    */
   public Object getMessage()
   {
      String s = messagePanel.getSelection();
      if (s.equals("String")) return messageString;
      else if (s.equals("Icon")) return messageIcon;
      else if (s.equals("Component")) return messageComponent;
      else if (s.equals("Object[]")) return new Object[] { messageString, messageIcon,
            messageComponent, messageObject };
      else if (s.equals("Other")) return messageObject;
      else return null;
   }

   /**
    * Pobiera aktualnie wybrane opcje.
    * @return tablica łańcuchów, ikon lub obiektów, w zależności od wyboru w panelu Opcja
    */
   public Object[] getOptions()
   {
      String s = optionsPanel.getSelection();
      if (s.equals("String[]")) return new String[] { "Yellow", "Blue", "Red" };
      else if (s.equals("Icon[]")) return new Icon[] { new ImageIcon("yellow-ball.gif"),
            new ImageIcon("blue-ball.gif"), new ImageIcon("red-ball.gif") };
      else if (s.equals("Object[]")) return new Object[] { messageString, messageIcon,
            messageComponent, messageObject };
      else return null;
   }

   /**
    * Pobiera wybrany komunikat lub typ opcji.
    * @param panel Typ komunikatu lub panel Potwierdzenie
    * @return wybrana stała XXX_MESSAGE lub XXX_OPTION z klasy JOptionPane
    */
   public int getType(ButtonPanel panel)
   {
      String s = panel.getSelection();
      try
      {
         return JOptionPane.class.getField(s).getInt(null);
      }
      catch (Exception e)
      {
         return -1;
      }
   }

   /**
    * Słuchacz akcji przycisku Pokaż wyświetla okno dialogowe potwierdzenia, danych wejściowych, komunikatu lub opcji,
    * w zależności od wyboru typu panelu.

    */
   private class ShowAction implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         if (typePanel.getSelection().equals("Confirm")) JOptionPane.showConfirmDialog(
               OptionDialogFrame.this, getMessage(), "Title", getType(optionTypePanel),
               getType(messageTypePanel));
         else if (typePanel.getSelection().equals("Input"))
         {
            if (inputPanel.getSelection().equals("Text field")) JOptionPane.showInputDialog(
                  OptionDialogFrame.this, getMessage(), "Title", getType(messageTypePanel));
            else JOptionPane.showInputDialog(OptionDialogFrame.this, getMessage(), "Title",
                  getType(messageTypePanel), null, new String[] { "Yellow", "Blue", "Red" },
                  "Blue");
         }
         else if (typePanel.getSelection().equals("Message")) JOptionPane.showMessageDialog(
               OptionDialogFrame.this, getMessage(), "Title", getType(messageTypePanel));
         else if (typePanel.getSelection().equals("Option")) JOptionPane.showOptionDialog(
               OptionDialogFrame.this, getMessage(), "Title", getType(optionTypePanel),
               getType(messageTypePanel), null, getOptions(), getOptions()[0]);
      }
   }
}

/**
 * Komponent z pomalowaną powierzchnią
 */

class SampleComponent extends JComponent
{
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
      g2.setPaint(Color.YELLOW);
      g2.fill(rect);
      g2.setPaint(Color.BLUE);
      g2.draw(rect);
   }

   public Dimension getPreferredSize()
   {
      return new Dimension(10, 10);
   }
}