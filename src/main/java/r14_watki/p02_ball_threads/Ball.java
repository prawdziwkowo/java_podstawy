package r14_watki.p02_ball_threads;

import java.awt.geom.*;

/**
 * Piłka, która porusza się i odbija od krawędzi prostokąta
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class Ball
{
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    /**
     * Przesuwa piłkę do następnego położenia, odwracając kierunek, jeśli piłka uderzy w krawędź
     */
    public void move(Rectangle2D bounds)
    {
        x += dx;
        y += dy;
        if (x < bounds.getMinX())
        {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + XSIZE >= bounds.getMaxX())
        {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY())
        {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bounds.getMaxY())
        {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    /**
     * Ustawia piłkę w jej aktualnym położeniu
     */
    public Ellipse2D getShape()
    {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
}
