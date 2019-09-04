package yakir.standard.nio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yakir.utils.Inet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIOServerSocketBuilder
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/09/2019 18:28.
 */
public class NIOServerSocketBuilder {

    private static final Logger log = LogManager.getLogger(NIOServerSocketBuilder.class);

    private boolean             accept = true;
    private ServerSocketChannel serverChannel;

    public NIOServerSocketBuilder build() throws IOException {

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket      ssocket  = serverChannel.socket();
        InetSocketAddress inetSddr = new InetSocketAddress(Inet.SERVER_NIO);
        ssocket.bind(inetSddr);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hello !".getBytes());

        while (accept) {
            selector.select();
            Set<SelectionKey>      readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter      = readyKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel       client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        log.info("Accept Connection From Client {}", client);
                        if (key.isWritable()) {
//                        SocketChannel client = key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            while (buffer.hasRemaining()) {
                                if (client.write(buffer) == 0) {
                                    break;
                                }
                            }
                            client.close();
                        }
                    }
                } catch (IOException exp) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cexp) {

                    }
                }
            }
        }
        return this;
    }
}
