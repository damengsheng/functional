package framewk.dubbo.service;

import framewk.dubbo.facde.Greeting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DubboProviderConfig
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2019/02/15 12:52.
 */
public class HelloService implements Greeting {

    private static final Logger log = LogManager.getLogger(HelloService.class);

    @Override
    public String hello(String name) {
        return "Hello ".concat(name).concat(" !");
    }
}
