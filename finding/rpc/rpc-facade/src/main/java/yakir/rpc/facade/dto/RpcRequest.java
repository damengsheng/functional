package yakir.rpc.facade.dto;

import yakir.tools.Pair;

import java.io.Serializable;
import java.util.List;

/**
 * RpcRequest
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/03/26 23:10.
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -6518586429430557158L;

    private String                     url;
    private String                     method;
    private String                     body;
    private List<Pair<String, String>> headers;

    public String getUrl() {
        return url;
    }

    public RpcRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public RpcRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getBody() {
        return body;
    }

    public RpcRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public List<Pair<String, String>> getHeaders() {
        return headers;
    }

    public RpcRequest setHeaders(List<Pair<String, String>> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
               "url='" + url + '\'' +
               ", method='" + method + '\'' +
               ", body='" + body + '\'' +
               ", headers=" + headers +
               '}';
    }
}
