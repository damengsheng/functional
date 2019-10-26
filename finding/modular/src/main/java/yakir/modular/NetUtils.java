package yakir.modular;

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
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 2019/10/18 11:50.
 */
public class NetUtils {

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

        }
        return ips;
    }
}
