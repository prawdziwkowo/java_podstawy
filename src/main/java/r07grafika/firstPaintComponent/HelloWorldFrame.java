package r07grafika.firstPaintComponent;

import javax.swing.*;

public class HelloWorldFrame extends JFrame {
    public HelloWorldFrame() {
        add(new HelloWorldPanel());
        pack();
    }
}
