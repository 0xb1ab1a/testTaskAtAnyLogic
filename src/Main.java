import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {

    @Override
    public void run() {
        JFrame frame = new JFrame("Figure property editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Hello world!"), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}
