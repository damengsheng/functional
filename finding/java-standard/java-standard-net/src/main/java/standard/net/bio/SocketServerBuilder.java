package standard.net.bio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import standard.tools.NetUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * SocketServerBuilder
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 13:25.
 */
public class SocketServerBuilder {

    private static final Logger log = LogManager.getLogger(SocketServerBuilder.class);

    private final AtomicInteger                   COUNTER       = new AtomicInteger(0);
    private final ThreadGroup                     GROUP         = new ThreadGroup("SocketServerHandler");
    private final Thread.UncaughtExceptionHandler THREAD_CAUGHT = (Thread t, Throwable e) -> {
        log.error("Thread[{}] Exp", t.getName(), e);
    };

    private boolean      accept     = true;
    private int          connectMax = 10; // 最大连接数, 超出连接数refuse(forbidden)
    private ServerSocket socketServer;

    public SocketServerBuilder buildServer() {

        try {
            socketServer = new ServerSocket(NetUtils.SERVER_BIO);
        } catch (IOException e) {
            log.error("Create ServerSocket Err", e);
        }

        log.info("Socket Server Listening {}...", NetUtils.SERVER_BIO);
        return this;
    }

    public void action() {
        while (accept) {
            try {
                Socket socket = socketServer.accept();
                socket.setSoTimeout(NetUtils.TIMEOUT);
                socket.setOOBInline(true);
                socket.setTcpNoDelay(true);
                int count = COUNTER.getAndIncrement();
                log.debug("Client Count {}", count);
                // 超负荷链接拒绝
                if (count >= connectMax) {
                    log.warn("max count {} existed {}", connectMax, count);
//                    socket.shutdownInput();
//                    socket.shutdownOutput();
                    if (!socket.isClosed())
                        try {
                            socket.close();
                        } catch (IOException exp) {
                        }
                    socket = null;
                    COUNTER.getAndDecrement();
                    continue;
                }

                InetAddress inetAddress = socket.getInetAddress();
                String      tName       = inetAddress.getHostAddress();

                Thread thread = new Thread(GROUP,
                        new SocketServerMultiConnHandler(COUNTER, socket));
                thread.setName("BIOServer-".concat(tName));
                thread.setDaemon(false);
                thread.setUncaughtExceptionHandler(THREAD_CAUGHT);
                thread.start();
            } catch (IOException e) {
                log.error("Socket Server Accept Err", e);
            }
        }
    }

    public void stop() {
        this.accept = false;
    }
}
