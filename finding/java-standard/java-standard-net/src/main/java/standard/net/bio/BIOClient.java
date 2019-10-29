package standard.net.bio;

import standard.tools.NetUtils;

/**
 * BIOServer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 18:31.
 */
public class BIOClient {

    public static void main(String[] args) {

        int tnu = Integer.valueOf(args[0]);

        for (int i = 0; i < tnu; i++) {
            Thread thread = new Thread(() -> {
                new SocketClientBuilder()
                        .build(NetUtils.SERVER_BIO)
                        .msg("hello")
                        .response();
            });
            thread.setName("BIOClient-" + i);
            thread.setDaemon(false);
            thread.start();
        }
    }
}
