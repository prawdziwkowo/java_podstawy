package r14_watki.p01_ball;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Komponent rysujący piłki
 * @version 1.34 2012-01-26
 * @author Cay Horstmann
 */
public class BallComponent extends JPanel
{
   private static final int DEFAULT_WIDTH = 450;
   private static final int DEFAULT_HEIGHT = 350;

   private java.util.List<Ball> balls = new ArrayList<>();

   /**
    * Dodaje piłkę do komponentu
    * @param b piłka, która ma zostać dodana
    */
   public void add(Ball b)
   {
      balls.add(b);
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); // czyszczenie tła
      Graphics2D g2 = (Graphics2D) g;
      for (Ball b : balls)
      {
         g2.fill(b.getShape());
      }
   }

   public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}
