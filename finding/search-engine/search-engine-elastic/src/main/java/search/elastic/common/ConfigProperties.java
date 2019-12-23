package search.elastic.common;

/**
 * ConfigProperties
 *
 * @author yakir on 2019/11/13 17:40.
 */
public class ConfigProperties {

    private String  cluster;
    private String  protocol;
    private String  authName;
    private String  authPwd;
    private Integer ioThreadCount;
    private Integer soTimeout;
    private Integer connTimeout;
    private Integer connRequestTimeout;
    private Integer maxConnPerRoute;
    private Integer maxConnTotal;
    private String  nodes;

    public String getCluster() {
        return cluster;
    }

    public ConfigProperties setCluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public ConfigProperties setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getAuthName() {
        return authName;
    }

    public ConfigProperties setAuthName(String authName) {
        this.authName = authName;
        return this;
    }

    public String getAuthPwd() {
        return authPwd;
    }

    public ConfigProperties setAuthPwd(String authPwd) {
        this.authPwd = authPwd;
        return this;
    }

    public Integer getIoThreadCount() {
        return ioThreadCount;
    }

    public ConfigProperties setIoThreadCount(Integer ioThreadCount) {
        this.ioThreadCount = ioThreadCount;
        return this;
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public ConfigProperties setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
        return this;
    }

    public Integer getConnTimeout() {
        return connTimeout;
    }

    public ConfigProperties setConnTimeout(Integer connTimeout) {
        this.connTimeout = connTimeout;
        return this;
    }

    public Integer getConnRequestTimeout() {
        return connRequestTimeout;
    }

    public ConfigProperties setConnRequestTimeout(Integer connRequestTimeout) {
        this.connRequestTimeout = connRequestTimeout;
        return this;
    }

    public Integer getMaxConnPerRoute() {
        return maxConnPerRoute;
    }

    public ConfigProperties setMaxConnPerRoute(Integer maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
        return this;
    }

    public Integer getMaxConnTotal() {
        return maxConnTotal;
    }

    public ConfigProperties setMaxConnTotal(Integer maxConnTotal) {
        this.maxConnTotal = maxConnTotal;
        return this;
    }

    public String getNodes() {
        return nodes;
    }

    public ConfigProperties setNodes(String nodes) {
        this.nodes = nodes;
        return this;
    }
}
