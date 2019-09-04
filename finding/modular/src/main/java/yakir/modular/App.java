package yakir.modular;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yakir.modular.service.Greeting;

import java.util.ServiceLoader;

public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        ServiceLoader.load(Greeting.class)
                .findFirst()
                .map(_parm -> _parm.hello("yakir"))
                .ifPresent(log::info);
    }
}
