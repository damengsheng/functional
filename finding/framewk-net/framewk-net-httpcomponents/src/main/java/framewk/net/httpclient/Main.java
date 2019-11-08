package framewk.net.httpclient;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Main
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2019/01/03 18:50.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

//        HCBuilders hcbuilders = new HCBuilders();
//        CloseableHttpClient httpClient = hcbuilders.buildClientBuilder().build();
        String         arg    = args[0];
        String[]       php    = arg.split(":");
        String         phost  = php[0];
        String         pport  = php[1];
        final HttpHost target = new HttpHost("https", "account.chsi.com.cn", 443);
        final HttpHost proxy  = new HttpHost("http", phost, Integer.parseInt(pport));

        final RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
//        final HttpGet request = new HttpGet("/get");
        HttpGet request = new HttpGet("/account/preregister.action?from=archive");
        request.setConfig(config);

//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpClient httpClient = new HCBuilders().buildClientBuilder().build();
             CloseableHttpResponse response = httpClient.execute(target, request)) {
            System.out.println("----------------------------------------");
            System.out.println(response.getCode() + " " + response.getReasonPhrase());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException | ParseException e) {
            logger.error("execute get ", e);
        }
    }
}
