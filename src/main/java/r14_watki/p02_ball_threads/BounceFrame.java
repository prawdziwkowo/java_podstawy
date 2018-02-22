package r14_watki.p02_ball_threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private BallComponent component;

    public BounceFrame() {
        component = new BallComponent();
        add(component, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonPanel, "Zamyknij", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    private void addBall() {
        Ball ball = new Ball();
        component.add(ball);
        Runnable r = new BallRunnable(ball, component);
        Thread thread = new Thread(r);
        thread.start();
    }
}
