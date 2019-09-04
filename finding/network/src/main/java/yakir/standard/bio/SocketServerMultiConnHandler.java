package yakir.standard.bio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yakir.utils.Times;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SocketServerMultiConnHandler
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 15:04.
 */
public class SocketServerMultiConnHandler implements Runnable {

    private static final Logger log = LogManager.getLogger(SocketServerMultiConnHandler.class);

    private AtomicInteger counter;
    private Socket        socket;

    public SocketServerMultiConnHandler(AtomicInteger counter,
                                        Socket socket) {
        this.counter = counter;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
             PrintWriter pw = new PrintWriter(socket.getOutputStream())) {

            String request;
            while (true) {
                request = reader.readLine();
                if (null == request || request.isEmpty()) {
                    continue;
                }

                if ("exit".equalsIgnoreCase(request)) {
                    pw.write("ok\n");
                    pw.flush();
                    break;
                } else {
                    String response = Times.nowString()
                            .concat(" ")
                            .concat(Thread.currentThread().getName())
                            .concat(" ")
                            .concat(socket.getRemoteSocketAddress().toString())
                            .concat(" ")
                            .concat(request)
                            .concat(" Success.\n");
                    pw.write(response);
                    pw.flush(); // not autoFlush
                }
            }
        } catch (IOException e) {
            log.error("Handler Error ", e);
        } finally {
            if (null != socket && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("Close Socket Err", e);
                }
            }
            socket = null;
            counter.getAndDecrement();
        }
    }
}
