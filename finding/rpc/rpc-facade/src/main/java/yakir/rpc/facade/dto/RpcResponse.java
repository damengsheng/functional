package yakir.rpc.facade.dto;

import yakir.tools.Pair;

import java.io.Serializable;
import java.util.List;

/**
 * RpcResponse
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/03/26 23:10.
 */
public class RpcResponse
        implements Serializable {

    private static final long serialVersionUID = 4111447339710374377L;

    private Integer                    statusCode;
    private String                     body;
    private List<Pair<String, String>> headers;

    public Integer getStatusCode() {
        return statusCode;
    }

    public RpcResponse setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getBody() {
        return body;
    }

    public RpcResponse setBody(String body) {
        this.body = body;
        return this;
    }

    public List<Pair<String, String>> getHeaders() {
        return headers;
    }

    public RpcResponse setHeaders(List<Pair<String, String>> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
               "statusCode=" + statusCode +
               ", body='" + body + '\'' +
               ", headers=" + headers +
               '}';
    }
}
