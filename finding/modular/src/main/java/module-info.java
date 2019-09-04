import yakir.modular.service.Greeting;
import yakir.modular.service.SayHello;

/**
 * house
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 12/26/2018 12:33.
 */
module modular {

    requires java.net.http;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    requires java.instrument;

    uses Greeting;

    exports yakir.modular;

    opens yakir.modular;

    provides Greeting with SayHello;
}
