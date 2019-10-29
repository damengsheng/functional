package standard.swing;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ClockLabel extends JLabel implements ActionListener {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ClockLabel() {
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Source Code Pro", Font.PLAIN, 20));
        this.setHorizontalAlignment(SwingConstants.CENTER);

        Timer timer = new Timer(0, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.setText(LocalDateTime.now(ZoneOffset.systemDefault()).format(DTF));
    }
}