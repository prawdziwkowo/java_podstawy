package r09swing.p10_gridbaglayout;

import javax.swing.*;
import java.awt.*;

/**
 * @version 1.13 2007-06-12
 * @author Cay Horstmann
 */
public class Draw
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               FontFrame frame = new FontFrame();
               frame.setTitle("GridBagLayout");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}