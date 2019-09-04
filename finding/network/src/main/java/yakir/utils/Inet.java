package yakir.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Inet
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 13:45.
 */
public class Inet {

    private static final Logger LOGGER = LogManager.getLogger(Inet.class);

    public static final String  LOCAL_V4 = "127.0.0.1";
    public static final String  LOCAL_V6 = "::1";
    public static final Integer TIMEOUT  = 5000;

    public static final Integer SERVER_BIO = 8000;
    public static final Integer SERVER_NIO = 8001;

    public static final Integer SERVER_NETTY_ECHO      = 8100;
    public static final Integer SERVER_NETTY_OIO_ECHO  = 8101;
    public static final Integer SERVER_NETTY_SCHEDULED = 8102;

}
