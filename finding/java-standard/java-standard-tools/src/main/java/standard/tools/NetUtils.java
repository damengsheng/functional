package standard.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * NetUtils
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 13:45.
 */
public class NetUtils {

    private static final Logger log = LogManager.getLogger(NetUtils.class);

    public static final String LOCAL_V4 = "127.0.0.1";
    public static final String LOCAL_V6 = "::1";
    public static final int    TIMEOUT  = 5000;

    public static final Integer SERVER_BIO = 8000;
    public static final Integer SERVER_NIO = 8001;

    public static final Integer SERVER_NETTY_ECHO      = 8100;
    public static final Integer SERVER_NETTY_OIO_ECHO  = 8101;
    public static final Integer SERVER_NETTY_SCHEDULED = 8101;


    public static List<String> hostAddress() {

        List<String> ips = new ArrayList<>();
        try {
            for (Enumeration<NetworkInterface> i = NetworkInterface.getNetworkInterfaces(); i.hasMoreElements(); ) {
                NetworkInterface ni = i.nextElement();
                for (Enumeration<InetAddress> allAddress = ni.getInetAddresses(); allAddress.hasMoreElements(); ) {
                    InetAddress address = allAddress.nextElement();
                    if (address.isLoopbackAddress() || address instanceof Inet6Address) {
                        continue;
                    }
                    String hostAddress = address.getHostAddress();
                    ips.add(hostAddress);
                }
            }
        } catch (SocketException exp) {
            log.error("Err", exp);
        }
        return ips;
    }

}
