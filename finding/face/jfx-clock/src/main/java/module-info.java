/**
 * jfx.clock
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 10:42.
 */
module jfx.clock {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    uses javafx.application.Application;

    opens yakir.jfx.clock to javafx.fxml;

    exports yakir.jfx.clock;
}