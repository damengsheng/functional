package binlog.network;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.util.Optional;

/**
 * Channels
 *
 * @author yakir on 2019/11/29 14:05.
 */
public class Channels {

    private static final Logger log = LogManager.getLogger(Channels.class);

    public static Optional<Channel> open(SocketAddress address) {

        try {
            return SocketChannelManager.open(address);
        } catch (InterruptedException e) {
            log.error("Open Channel Err ");
            return Optional.empty();
        }
    }
}
