package framewk.net.httpclient;

import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryHandler;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * HCBuilders
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2019/01/03 18:36.
 */
public class HCBuilders {

    private static final Logger logger = LogManager.getLogger(HCBuilders.class);

    private int     readTimeOut           = 6000;
    private int     connentTimeOut        = 6000;
    private Integer connentRequestTimeout = 6000;
    private Integer retryCount            = 3;
    private Integer maxPerRoute           = 2700;
    private Integer defaultPerRoute       = 100;

    private static class MTrustManager implements X509TrustManager {

        private final X509TrustManager trustManager  = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
            }
        };
        private final TrustStrategy    trustStrategy = new TrustAllStrategy();

        @Override
        public void checkClientTrusted(final X509Certificate[] chain,
                                       final String authType)
                throws CertificateException {
            this.trustManager.checkClientTrusted(chain, authType);
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain,
                                       final String authType)
                throws CertificateException {
            if (!this.trustStrategy.isTrusted(chain, authType)) {
                this.trustManager.checkServerTrusted(chain, authType);
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return this.trustManager.getAcceptedIssuers();
        }

    }

    public final static TrustManager[] TRUST_ALL_CERTS = new X509TrustManager[]{
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs,
                                               String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs,
                                               String authType) {
                }
            }
    };

    private static TrustManager manager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain,
                                       String authType)
                throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain,
                                       String authType)
                throws CertificateException {
        }
    };

    public HttpClientBuilder buildClientBuilder() {

        logger.info("readTimeOut:           [{}]", readTimeOut);
        logger.info("connentTimeOut:        [{}]", connentTimeOut);
        logger.info("connentRequestTimeout: [{}]", connentRequestTimeout);
        logger.info("retryCount:            [{}]", retryCount);
        logger.info("maxPerRoute:           [{}]", maxPerRoute);
        logger.info("defaultPerRoute:       [{}]", defaultPerRoute);

        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
//            sslContext.init(null, new TrustManager[]{manager}, null);
//            sslContext.init(null, new X509TrustManager[]{new MTrustManager()}, null);
            sslContext.init(null, TRUST_ALL_CERTS, null);
            SSLConnectionSocketFactory sslConnectionSocketFactory
                    = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            // 注册http和https请求
            Registry<ConnectionSocketFactory> socketFactoryRegistry
                    = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory)
                    .build();
            // 开始设置连接池
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                    = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 最大连接数
            poolingHttpClientConnectionManager.setMaxTotal(maxPerRoute);
            // 同路由并发数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultPerRoute);
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
//                    .setSSLContext(sslContext)
                    .setConnectionManager(poolingHttpClientConnectionManager)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount)); // 重试次数
            HttpHost proxyHost = new HttpHost("https", "127.0.0.1", 180);
            httpClientBuilder.setRoutePlanner(new DefaultProxyRoutePlanner(proxyHost) {});

            return httpClientBuilder;
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            logger.error("初始化HTTP连接池出错", e);
            return null;
        }
    }
}
