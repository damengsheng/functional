package standard.modular;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import standard.modular.service.Greeting;

import java.util.ServiceLoader;

public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        ServiceLoader.load(Greeting.class)
                .findFirst()
                .map(_params -> _params.hello("yakir"))
                .ifPresent(log::info);
    }
}
