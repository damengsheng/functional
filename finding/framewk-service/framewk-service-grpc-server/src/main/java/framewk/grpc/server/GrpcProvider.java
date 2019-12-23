package framewk.grpc.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * GrpcProvider
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2018/10/19 13:42.
 */
public class GrpcProvider {

    private static final Logger log = LogManager.getLogger(GrpcProvider.class);

    private static final Integer port = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {

        GrpcServer grpcServer = new GrpcServer()
                .start(port)
                .await();
    }
}