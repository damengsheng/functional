package yakir.face.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Clock
 *
 * <pre>
 *     mvn clean compile exec:java -Dexec.mainClass=yakir.face.swing.Clock
 * </pre>
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/07 16:54.
 */
public class Clock {

    private static Color bgcolor = new Color(33, 33, 33);

    private static void createFrame() {

        JFrame frame = new JFrame("Swing Clock");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(bgcolor);
        frame.setLayout(new GridLayout(1, 1));

        JLabel    helloLabel = new ClockLabel();
        Container container  = frame.getContentPane();
        container.setBackground(bgcolor);
        container.setPreferredSize(new Dimension(260, 40));
        container.add(helloLabel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Clock::createFrame);
    }
}
