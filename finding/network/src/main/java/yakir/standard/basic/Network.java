package yakir.standard.basic;

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

    private static String mac() {

        try {
            InetAddress      inetAddr     = InetAddress.getLocalHost();
            NetworkInterface netInterface = NetworkInterface.getByInetAddress(inetAddr);
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
