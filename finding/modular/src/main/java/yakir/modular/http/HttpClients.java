package yakir.modular.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * HttpClients
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/08/17 16:46.
 */
public class HttpClients implements Clients<HttpClient> {

    private static final Logger log = LogManager.getLogger(HttpClients.class);

    private static SSLSocketFactory trustedFactory;
    private static HostnameVerifier trustedVerifier;

    private SSLSocketFactory getTrustedFactory() {

        if (null == trustedFactory) {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() { return null; }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) { }

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) { }
                    }
            };
            try {
                SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
                sslContext.init(null, trustAllCerts, new SecureRandom());
                trustedFactory = sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                log.error(e);
            }
        }

        return trustedFactory;
    }

    private static HostnameVerifier getTrustedVerifier() {
        if (null == trustedVerifier) {
            trustedVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
        }

        return trustedVerifier;
    }

    public HttpRequest get(final String url) {
        return HttpRequest.newBuilder(URI.create(url))
                .GET()
                .build();
    }

    public HttpRequest get(final String url, Map<?, ?> params) {


        return HttpRequest.newBuilder(URI.create(url))
                .GET()
                .build();
    }

}
