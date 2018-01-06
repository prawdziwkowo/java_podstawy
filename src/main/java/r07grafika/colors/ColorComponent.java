package r07grafika.colors;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ColorComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.BLUE);

        g2.drawString("Kolorowy tekst", 0, 10);

        g2.setPaint(new Color(0, 128, 128));
        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);

        g2.setPaint(Color.RED);
        rect.setFrame(leftX + 1, topY + 1, width - 1, height - 1);
        g2.fill(rect);

        g2.setPaint(new Color(0, 128, 128));
        rect.setFrame(leftX + 10, topY + 10, width - 20, height - 20);
        g2.fill(rect);

        g2.setPaint(new Color(0, 128, 128).brighter().brighter());
        rect.setFrame(leftX + 20, topY + 20, width - 40, height - 40);
        g2.fill(rect);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
