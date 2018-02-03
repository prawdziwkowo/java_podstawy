package r09swing.p14_okna_wyboru_plikow;

import java.awt.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;

/**
 * Akcesorium wy�wietlaj�ce podgl�d obraz�w
 */
public class ImagePreviewer extends JLabel
{
   /**
    * Tworzy obiekt ImagePreviewer
    * @param chooser okno wyboru plik�w, kt�rego w�asno�� zmienia si�, powoduje zmian� obrazu 
    * w tym podgl�dzie

    */
   public ImagePreviewer(JFileChooser chooser)
   {
      setPreferredSize(new Dimension(100, 100));
      setBorder(BorderFactory.createEtchedBorder());

      chooser.addPropertyChangeListener(new PropertyChangeListener()
         {
            public void propertyChange(PropertyChangeEvent event)
            {
               if (event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
               {
                  // U�ytkownik wybra� inny plik
                  File f = (File) event.getNewValue();
                  if (f == null)
                  {
                     setIcon(null);
                     return;
                  }

                  // Wczytanie obrazu jako ikony
                  ImageIcon icon = new ImageIcon(f.getPath());

                  // Skalowanie obrazu, je�li jest zbyt du�y na ikon�
                  if (icon.getIconWidth() > getWidth()) icon = new ImageIcon(icon.getImage()
                        .getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));

                  setIcon(icon);
               }
            }
         });
   }
}
