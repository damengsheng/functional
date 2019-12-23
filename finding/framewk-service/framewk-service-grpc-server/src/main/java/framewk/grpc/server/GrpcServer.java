package framewk.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * GrpcServer
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2018/10/19 16:09.
 */
public class GrpcServer {

    private static final Logger logger = LogManager.getLogger(GrpcServer.class);

    private Server server;

    public GrpcServer start(Integer port) throws IOException {

        server = ServerBuilder.forPort(port)
                .addService(new GreetingService())
                .build()
                .start();
        logger.info("GRPC Server Started Listening on [{}]", port);
        return startAwaitThread();
    }

    private void shutdown() {
        if (null != server) {
            server.shutdown();
        }
    }

    public GrpcServer await() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
        return this;
    }

    private GrpcServer startAwaitThread() {
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(() -> {
                            logger.info("*** shutting down gRPC server since JVM is shutting down");
                            GrpcServer.this.shutdown();
                            logger.warn("*** server shut down");
                        }));
        return this;
    }
}
