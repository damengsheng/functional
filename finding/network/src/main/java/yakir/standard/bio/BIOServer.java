package yakir.standard.bio;

/**
 * BIOServer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 18:31.
 */
public class BIOServer {

    public static void main(String[] args) {
        new SocketServerBuilder()
                .buildServer()
                .action();
    }
}
