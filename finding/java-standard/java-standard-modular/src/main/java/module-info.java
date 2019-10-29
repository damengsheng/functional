/**
 * house
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 12/26/2018 12:33.
 */
module java.standard.modular {

    requires java.net.http;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    requires java.instrument;

    uses standard.modular.service.Greeting;

    exports standard.modular;

    opens standard.modular;

    provides standard.modular.service.Greeting with standard.modular.service.SayHello;
}
