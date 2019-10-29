/**
 * java openjfx clock
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 10:42.
 */
module java.standard.jfx {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    uses javafx.application.Application;

    opens standard.jfx to javafx.fxml;

    exports standard.jfx;
}