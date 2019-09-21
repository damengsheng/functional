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

    uses yakir.modular.service.Greeting;

    exports yakir.modular;

    opens yakir.modular;

    provides yakir.modular.service.Greeting with yakir.modular.service.SayHello;
}
