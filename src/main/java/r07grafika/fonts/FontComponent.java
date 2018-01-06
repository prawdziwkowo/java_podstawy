package r07grafika.fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FontComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Font sansbold14 = new Font("SansSerif", Font.BOLD, 14);
        g2.setFont(sansbold14);
        g2.drawString("Czciona nie szeryfowana", 0, 20);

        Font sansbolditalic14 = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
        g2.setFont(sansbolditalic14);
        g2.drawString("Czciona nie szeryfowana grubaśna i ukośna", 0, 40);

//        try {
//            URL url = new URL("http://www.prawdzik.pl/Filxgirl.TTF");
//            InputStream in = url.openStream();
//            Font internet = Font.createFont(Font.TRUETYPE_FONT, in);
//            internet.deriveFont(14.0F);
//            g2.setFont(internet);
//            g2.drawString("Czciona z internetów", 0, 60);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (FontFormatException e) {
//            e.printStackTrace();
//        }


        //wyśrodkowanie tekstu
        String message = "Wyśrodkowany tekścik!";
        Font f = new Font("Serif", Font.BOLD, 36);
        g2.setFont(f);
        //sprawdzanie rozmiaru tekstu
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(message, context);

        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;

        //dodanie wydłużenia górnego do y aby sięgnąć linię bazową
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        //rysowanie komunikatu
        g2.drawString(message, (int)x, (int)baseY);
        g2.setPaint(Color.DARK_GRAY);

        //rysowanie linii bazowej
        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

        //rysowanie otaczającego tekst prostokąta
        Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        g2.draw(rect);


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
