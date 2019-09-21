package yakir.jfx.clock;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * FXMLController
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 12/22/2018 14:14.
 */
public class FXMLController implements Initializable {

    private static final ZoneId            DEFAULT_ZONE = ZoneOffset.systemDefault();
    private static final DateTimeFormatter DTF          = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                Platform.runLater(() -> label.setText(LocalDateTime.now(DEFAULT_ZONE).format(DTF)));
            }
        }.start();
    }
}
