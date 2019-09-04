package yakir.standard.nio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * NIOServer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/09/2019 18:19.
 */
public class NIOServer {

    private static final Logger log = LogManager.getLogger(NIOServer.class);

    public static void main(String[] args) {
        try {
            new NIOServerSocketBuilder().build();
        } catch (IOException e) {
            log.error("Error ", e);
        }
    }
}
