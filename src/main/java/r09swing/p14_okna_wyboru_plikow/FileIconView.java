package r09swing.p14_okna_wyboru_plikow;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

/**
 * Widok plik�w wy�wietlaj�cy ikon� obok wszystkich plik�w zaakceptowanych przez filtr
 */
public class FileIconView extends FileView
{
   private FileFilter filter;
   private Icon icon;

   /**
    * Tworzy obiekt FileIconView
    * @param aFilter filtr plik�w � wszystkie pliki zaakceptowane przez ten filtr b�d� mia�y ikon�.
    * @param anIcon � ikona wy�wietlana obok wszystkich zaakceptowanych plik�w
    */
   public FileIconView(FileFilter aFilter, Icon anIcon)
   {
      filter = aFilter;
      icon = anIcon;
   }

   public Icon getIcon(File f)
   {
      if (!f.isDirectory() && filter.accept(f)) return icon;
      else return null;
   }
}
