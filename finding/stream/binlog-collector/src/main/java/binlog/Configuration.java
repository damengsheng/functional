package binlog;

import java.io.Serializable;

/**
 * Configuration
 *
 * <a herf="https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-configuration-properties.html">Configuration Properties</a>
 * <p>
 * sslMode
 * <ul>
 *   <li>PREFERRED</li>
 *     {"useSSL=true", "requireSSL=true", "verifyServerCertificate=false"}
 *   <li>REQUIRED</li>
 *     {"useSSL=true" AND "verifyServerCertificate=true"}
 *   <li>DISABLED</li>
 *     {"useSSL=true", "requireSSL=false", "verifyServerCertificate=false"}
 * </ul>
 *
 * @author yakir on 2019/11/21 14:23.
 */
public class Configuration implements Serializable {

    private static final long serialVersionUID = 7918950421376580492L;

    private String  user;
    private String  password;
    private String  jdbcUrl;
    private int     connectTimeout           = 0;
    private int     socketTimeout            = 0;
    private int     maxAllowedPacket         = 65535;
    private boolean tcpKeepAlive             = true;
    private String  characterEncoding        = "utf8mb4";
    private String  sslMode                  = "DISABLED";
    private String  logger                   = "com.mysql.cj.log.StandardLogger"; // com.mysql.cj.log.Slf4JLogger
    private boolean logSlowQueries           = true;
    private int     slowQueryThresholdMillis = 2000;
    private boolean enablePacketDebug        = true;
    private Boolean cachePrepStmts           = true;
    private int     prepStmtCacheSize        = 250;
    private int     prepStmtCacheSqlLimit    = 2048;
    private Boolean useServerPrepStmts       = true;
    private Boolean useLocalSessionState     = true;
    private Boolean useLocalTransactionState = true;
    private Boolean rewriteBatchedStatements = true;
    private Boolean cacheResultSetMetadata   = true;
    private Boolean cacheServerConfiguration = true;
    private Boolean elideSetAutoCommits      = true;
    private Boolean maintainTimeStats        = false;

    public static Configuration create(){
        return new Configuration();
    }

    public String getUser() {
        return user;
    }

    public Configuration setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Configuration setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public Configuration setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public Configuration setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public Configuration setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public int getMaxAllowedPacket() {
        return maxAllowedPacket;
    }

    public Configuration setMaxAllowedPacket(int maxAllowedPacket) {
        this.maxAllowedPacket = maxAllowedPacket;
        return this;
    }

    public boolean isTcpKeepAlive() {
        return tcpKeepAlive;
    }

    public Configuration setTcpKeepAlive(boolean tcpKeepAlive) {
        this.tcpKeepAlive = tcpKeepAlive;
        return this;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public Configuration setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
        return this;
    }

    public String getSslMode() {
        return sslMode;
    }

    public Configuration setSslMode(String sslMode) {
        this.sslMode = sslMode;
        return this;
    }

    public String getLogger() {
        return logger;
    }

    public Configuration setLogger(String logger) {
        this.logger = logger;
        return this;
    }

    public boolean isLogSlowQueries() {
        return logSlowQueries;
    }

    public Configuration setLogSlowQueries(boolean logSlowQueries) {
        this.logSlowQueries = logSlowQueries;
        return this;
    }

    public int getSlowQueryThresholdMillis() {
        return slowQueryThresholdMillis;
    }

    public Configuration setSlowQueryThresholdMillis(int slowQueryThresholdMillis) {
        this.slowQueryThresholdMillis = slowQueryThresholdMillis;
        return this;
    }

    public boolean isEnablePacketDebug() {
        return enablePacketDebug;
    }

    public Configuration setEnablePacketDebug(boolean enablePacketDebug) {
        this.enablePacketDebug = enablePacketDebug;
        return this;
    }

    public Boolean getCachePrepStmts() {
        return cachePrepStmts;
    }

    public Configuration setCachePrepStmts(Boolean cachePrepStmts) {
        this.cachePrepStmts = cachePrepStmts;
        return this;
    }

    public int getPrepStmtCacheSize() {
        return prepStmtCacheSize;
    }

    public Configuration setPrepStmtCacheSize(int prepStmtCacheSize) {
        this.prepStmtCacheSize = prepStmtCacheSize;
        return this;
    }

    public int getPrepStmtCacheSqlLimit() {
        return prepStmtCacheSqlLimit;
    }

    public Configuration setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit) {
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
        return this;
    }

    public Boolean getUseServerPrepStmts() {
        return useServerPrepStmts;
    }

    public Configuration setUseServerPrepStmts(Boolean useServerPrepStmts) {
        this.useServerPrepStmts = useServerPrepStmts;
        return this;
    }

    public Boolean getUseLocalSessionState() {
        return useLocalSessionState;
    }

    public Configuration setUseLocalSessionState(Boolean useLocalSessionState) {
        this.useLocalSessionState = useLocalSessionState;
        return this;
    }

    public Boolean getUseLocalTransactionState() {
        return useLocalTransactionState;
    }

    public Configuration setUseLocalTransactionState(Boolean useLocalTransactionState) {
        this.useLocalTransactionState = useLocalTransactionState;
        return this;
    }

    public Boolean getRewriteBatchedStatements() {
        return rewriteBatchedStatements;
    }

    public Configuration setRewriteBatchedStatements(Boolean rewriteBatchedStatements) {
        this.rewriteBatchedStatements = rewriteBatchedStatements;
        return this;
    }

    public Boolean getCacheResultSetMetadata() {
        return cacheResultSetMetadata;
    }

    public Configuration setCacheResultSetMetadata(Boolean cacheResultSetMetadata) {
        this.cacheResultSetMetadata = cacheResultSetMetadata;
        return this;
    }

    public Boolean getCacheServerConfiguration() {
        return cacheServerConfiguration;
    }

    public Configuration setCacheServerConfiguration(Boolean cacheServerConfiguration) {
        this.cacheServerConfiguration = cacheServerConfiguration;
        return this;
    }

    public Boolean getElideSetAutoCommits() {
        return elideSetAutoCommits;
    }

    public Configuration setElideSetAutoCommits(Boolean elideSetAutoCommits) {
        this.elideSetAutoCommits = elideSetAutoCommits;
        return this;
    }

    public Boolean getMaintainTimeStats() {
        return maintainTimeStats;
    }

    public Configuration setMaintainTimeStats(Boolean maintainTimeStats) {
        this.maintainTimeStats = maintainTimeStats;
        return this;
    }
}
