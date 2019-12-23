package search.elastic.builder;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import search.elastic.common.ConfigProperties;
import search.elastic.operator.IOperator;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * AbstractBuilder
 *
 * @author yakir on 2019/11/27 18:29.
 */
abstract
public class AbstractBuilder implements IBuilder {

    protected ConfigProperties info;
    protected HttpHost[]       nodes;

    public IBuilder info(ConfigProperties info) {
        this.info = info;
        return this;
    }

    @Override
    public IBuilder nodes() {

        String nodesReplace = Optional.ofNullable(info)
                .map(_info -> _info.getNodes())
                .filter(_nodes -> !_nodes.isBlank())
                .map(_nodes -> _nodes.replace(" ", ""))
                .map(_nodes -> _nodes.replace("\r", ""))
                .map(_nodes -> _nodes.replace("\r\n", ""))
                .map(_nodes -> _nodes.replace("\n", ""))
                .filter(_nodes -> !_nodes.isBlank())
                .orElseThrow(IllegalArgumentException::new);

        this.nodes = Pattern.compile(",").splitAsStream(nodesReplace)
                .map(_node -> _node.split(":"))
                .map(_pair -> new HttpHost(_pair[0], Integer.parseInt(_pair[1]), Optional.ofNullable(info.getProtocol()).orElse("http")))
                .toArray(HttpHost[]::new);
        return this;
    }

    @Override
    public RestClientBuilder restClient() {

        RestClientBuilder.RequestConfigCallback requestConfigCallback = (RequestConfig.Builder requestConfigBuilder)
                ->
        {
            requestConfigBuilder.setConnectTimeout(Optional.ofNullable(info.getConnTimeout()).orElse(-1))
                    .setSocketTimeout(Optional.ofNullable(info.getSoTimeout()).orElse(-1))
                    .setConnectionRequestTimeout(Optional.ofNullable(info.getConnRequestTimeout()).orElse(-1));
            return requestConfigBuilder;
        };

        RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = (HttpAsyncClientBuilder httpClientBuilder)
                ->
        {
            IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                    .setIoThreadCount(Optional.ofNullable(info.getIoThreadCount()).orElseGet(() -> Runtime.getRuntime().availableProcessors()))
                    .build();
            httpClientBuilder.setMaxConnTotal(Optional.ofNullable(info.getMaxConnTotal()).orElse(10))
                    .setMaxConnPerRoute(Optional.ofNullable(info.getMaxConnPerRoute()).orElse(10))
                    .setDefaultIOReactorConfig(ioReactorConfig);

            if ((null != info.getAuthName() && !info.getAuthName().isBlank()) || (null != info.getAuthPwd() && !info.getAuthPwd().isBlank())) {
                final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
                basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(info.getAuthName(), info.getAuthPwd()));
                httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
            }
            return httpClientBuilder;
        };

        nodes = Optional.ofNullable(nodes)
                .filter(_nodes -> _nodes.length > 0)
                .orElseThrow(IllegalArgumentException::new);
        RestClientBuilder restClientBuilder = RestClient.builder(nodes)
                .setHttpClientConfigCallback(httpClientConfigCallback)
                .setRequestConfigCallback(requestConfigCallback)
                .setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);

        return restClientBuilder;
    }

    public abstract <OPT extends IOperator> OPT operator();

}
