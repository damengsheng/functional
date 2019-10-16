package yakir.standard.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Network
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 14:06.
 */
public class Network {

    private static final Logger log = LogManager.getLogger(Network.class);

    private static String mac() {

        try {
            InetAddress localHost    = InetAddress.getLocalHost();
            String      hostName     = localHost.getHostName();
            String      localAddress = localHost.getHostAddress();
            log.info("LocalHostName: [{}] LocalAddress: [{}]", hostName, localAddress);

            NetworkInterface netInterface = NetworkInterface.getByInetAddress(localHost);
            byte[]           macAddr      = netInterface.getHardwareAddress();
            String           mac          = "";
            int              macLen       = macAddr.length;
            for (int i = 0; i < macLen; i++) {
                mac = mac.concat(String.format("%02X%s", macAddr[i], (i < macLen - 1) ? "-" : ""));
            }
            return mac;
        } catch (UnknownHostException | SocketException e) {
            return "";
        }
    }

    public static void main(String[] args) {
        String macStr = Network.mac();
        System.out.println(macStr);
    }

}
