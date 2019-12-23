package framewk.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Provider
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 10/15/2018 15:36.
 */
public class DubboApp {

    private static final Logger logger = LoggerFactory.getLogger(DubboApp.class);

    public static void main(String[] args) throws Exception {

        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("dubbo.application.logger", "log4j2");
        System.setProperty("io.netty.tryReflectionSetAccessible", "true");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-service.xml");
        context.start();

        System.in.read();

    }
}