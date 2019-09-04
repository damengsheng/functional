package yakir.standard.nio;

import yakir.standard.bio.SocketClientBuilder;
import yakir.utils.Inet;

/**
 * NIOClient
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/09/2019 18:25.
 */
public class NIOClient {

    public static void main(String[] args) {
        int tnu = Integer.valueOf(args[0]);

        for (int i = 0; i < tnu; i++) {
            Thread thread = new Thread(() -> {
                new SocketClientBuilder()
                        .build(Inet.SERVER_NIO)
                        .msg("hello")
                        .response();
            });
            thread.setName("NIOClient-" + i);
            thread.setDaemon(false);
            thread.start();
        }
    }
}
