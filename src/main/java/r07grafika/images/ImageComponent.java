package r07grafika.images;

import javax.swing.*;
import java.awt.*;

public class ImageComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;

    private Image image;

    public ImageComponent() {
        image = new ImageIcon(this.getClass().getResource("/1up-mushroom.png")).getImage();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (image == null) return;

        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        //rysowanie obrazu w lewym górnym rogu
        g.drawImage(image, 0, 0, null);

        //powielenie w obrębie komponentu
        for (int i = 0; i * imageWidth <= getWidth(); i++) {
            for (int j = 0; j * imageHeight <= getHeight(); j++) {
                if (i + j > 0) {
                    g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
