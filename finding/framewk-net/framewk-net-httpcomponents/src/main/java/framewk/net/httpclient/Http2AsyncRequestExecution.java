package framewk.net.httpclient;

import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpConnection;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.Message;
import org.apache.hc.core5.http.nio.AsyncClientEndpoint;
import org.apache.hc.core5.http.nio.entity.StringAsyncEntityConsumer;
import org.apache.hc.core5.http.nio.support.BasicRequestProducer;
import org.apache.hc.core5.http.nio.support.BasicResponseConsumer;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.http2.config.H2Config;
import org.apache.hc.core5.http2.frame.RawFrame;
import org.apache.hc.core5.http2.impl.nio.H2StreamListener;
import org.apache.hc.core5.http2.impl.nio.bootstrap.H2AsyncRequester;
import org.apache.hc.core5.http2.impl.nio.bootstrap.H2RequesterBootstrap;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Http2AsyncRequestExecution
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2019/01/03 20:44.
 */
public class Http2AsyncRequestExecution {

    private static final Logger logger = LogManager.getLogger(Http2AsyncRequestExecution.class);

    public void execHttp2Request()
            throws InterruptedException, ExecutionException {
        H2Config h2Config = H2Config.custom()
                .setPushEnabled(false)
                .build();

        H2AsyncRequester h2AsyncRequester = H2RequesterBootstrap.bootstrap()
                .setH2Config(h2Config)
                .setVersionPolicy(HttpVersionPolicy.FORCE_HTTP_2)
                .setStreamListener(new H2StreamListener() {
                    @Override
                    public void onHeaderInput(HttpConnection connection, int streamId, List<? extends Header> headers) {
                        headers.forEach(header -> {
                            logger.info("{} ({}) {}",
                                    connection.getRemoteAddress(), streamId, header);
                        });
                    }

                    @Override
                    public void onHeaderOutput(HttpConnection connection, int streamId, List<? extends Header> headers) {
                        headers.forEach(header -> {
                            logger.info("{} ({}) {}",
                                    connection.getRemoteAddress(), streamId, header);
                        });
                    }

                    @Override
                    public void onFrameInput(HttpConnection connection, int streamId, RawFrame frame) {
                    }

                    @Override
                    public void onFrameOutput(HttpConnection connection, int streamId, RawFrame frame) {
                    }

                    @Override
                    public void onInputFlowControl(HttpConnection connection, int streamId, int delta, int actualSize) {
                    }

                    @Override
                    public void onOutputFlowControl(HttpConnection connection, int streamId, int delta, int actualSize) {
                    }
                })
                .create();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("http requester shutting down .");
            h2AsyncRequester.close(CloseMode.GRACEFUL);
        }));

        h2AsyncRequester.start();

        HttpHost target      = new HttpHost("nghttp2.org");
        String[] requestUris = {"/httpbin/ip", "/httpbin/user-agent", "/httpbin/headers"};

        CountDownLatch latch = new CountDownLatch(requestUris.length);
        for (String requestUri : requestUris) {
            Future<AsyncClientEndpoint> future         = h2AsyncRequester.connect(target, Timeout.ofSeconds(5));
            AsyncClientEndpoint         clientEndpoint = future.get();
            clientEndpoint.execute(
                    new BasicRequestProducer("GET", target, requestUri),
                    new BasicResponseConsumer<>(new StringAsyncEntityConsumer()),
                    new FutureCallback<Message<HttpResponse, String>>() {
                        @Override
                        public void completed(final Message<HttpResponse, String> message) {
                            clientEndpoint.releaseAndReuse();
                            HttpResponse response = message.getHead();
                            String       body     = message.getBody();
                            logger.info(requestUri + "->" + response.getCode());
                            logger.info(body);
                            latch.countDown();
                        }

                        @Override
                        public void failed(final Exception ex) {
                            clientEndpoint.releaseAndDiscard();
                            logger.info(requestUri + "->" + ex);
                            latch.countDown();
                        }

                        @Override
                        public void cancelled() {
                            clientEndpoint.releaseAndDiscard();
                            logger.info(requestUri + " cancelled");
                            latch.countDown();
                        }
                    });
        }

        latch.await();
        logger.info("Shutting down I/O reactor");
        h2AsyncRequester.initiateShutdown();
    }

    public static void main(String[] args) {

        try {
            new Http2AsyncRequestExecution().execHttp2Request();
        } catch (ExecutionException | InterruptedException e) {
            logger.error("h2 exec error ", e);
        }
    }
}
