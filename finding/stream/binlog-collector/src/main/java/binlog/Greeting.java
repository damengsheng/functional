package binlog;

import binlog.network.Channels;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Greeting
 *
 * @author yakir on 2019/11/21 16:13.
 */
public class Greeting {

    public static void main(String[] args) {

        SocketAddress address = new InetSocketAddress("127.0.0.1", 3306);
        Channels.open(address);
    }
}
