package yakir.jfx.clock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JFXClock
 *
 * <pre>
 *    mvn clean compile -DskipTests -U exec:java
 *    jdk13 && mvn clean compile exec:java -Dexec.mainClass=yakir.jfx.clock.JFXClock
 *    jdk13 && mvn clean javafx:run
 * </pre>
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 09/06/2018 12:01.
 */
public class JFXClock extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Parent root  = FXMLLoader.load(getResource("scene.fxml"));
        Scene  scene = new Scene(root);
        scene.getStylesheets()
                .add(getResource("styles.css").toExternalForm());
        stage.setTitle("OpenJFX Clock");
        stage.setScene(scene);
        stage.show();
    }

    private URL getResource(String resource) {
        return JFXClock.class.getResource(resource);
    }

    public static void main(String[] args) {
        launch();
    }
}
