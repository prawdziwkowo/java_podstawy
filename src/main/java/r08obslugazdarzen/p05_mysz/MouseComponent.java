package r08obslugazdarzen.p05_mysz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class MouseComponent extends JComponent {
    private static final int SIDELENGTH = 10;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    public MouseComponent() {
        squares = new ArrayList<Rectangle2D>();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    public Rectangle2D find(Point2D p) {
        for (Rectangle2D r : squares) {
            if (r.contains(p)) {
                return r;
            }
        }

        return null;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for (Rectangle2D r : squares) {
            g2.draw(r);
        }
    }

    public void add(Point2D p) {
        current = new Rectangle2D.Double(p.getX() - SIDELENGTH / 2, p.getY() - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
        squares.add(current);
        repaint();
    }

    public void remove(Rectangle2D r) {
        if (r == null) {
            return;
        }
        if (r == current) {
            current = null;
        }

        squares.remove(r);
        repaint();
    }

    //kwadrat z kursorem
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            //dodanie nowego kwadratu jeżeli kursor nie jest w środku
            current = find(event.getPoint());
            if (current == null) {
                add(event.getPoint());
            }
        }

        public void mouseClicked(MouseEvent event) {
            //usuniecie kwadratu po podwójnym kliknięciu
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2) {
                remove(current);
            }
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        public void mouseDragged(MouseEvent event) {
            if (current == null) {
                return;
            }

            current.setFrame(event.getX() - SIDELENGTH / 2, event.getY() - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
            repaint();
        }

        public void mouseMoved(MouseEvent event) {
            //ustawienie kursora jeżeli znajduje się wewnątrz kwadratu
            if (find(event.getPoint()) == null) {
                setCursor(Cursor.getDefaultCursor());
            } else {
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
        }
    }
}
