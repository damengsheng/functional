package binlog;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Connectors
 *
 * <pre>
 *     jdbc:mysql:replication://[master host][:port],[slave host 1][:port][,[slave host 2][:port]]...[/[database]][?propertyName1=propertyValue1[&propertyName2=propertyValue2]...]
 * </pre>
 *
 * @author yakir on 2019/11/20 14:22.
 */
public class Connectors {

    private Connectors() {

    }

    public static Connectors create() {
        return new Connectors();
    }

    public DataSource hikaricp(Configuration info) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(info.getJdbcUrl()); //数据源
        config.setUsername(info.getUser()); //用户名
        config.setPassword(info.getPassword()); //密码
        config.addDataSourceProperty("cachePrepStmts", info.getCachePrepStmts()); //是否自定义配置，为true时下面两个参数才生效
        config.addDataSourceProperty("prepStmtCacheSize", info.getPrepStmtCacheSize()); //连接池大小默认25，官方推荐250-500
        config.addDataSourceProperty("prepStmtCacheSqlLimit", info.getPrepStmtCacheSqlLimit()); //单条语句最大长度默认256，官方推荐2048
        config.addDataSourceProperty("useServerPrepStmts", info.getUseServerPrepStmts()); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
        config.addDataSourceProperty("useLocalSessionState", info.getUseLocalSessionState());
        config.addDataSourceProperty("useLocalTransactionState", info.getUseLocalTransactionState());
        config.addDataSourceProperty("rewriteBatchedStatements", info.getRewriteBatchedStatements());
        config.addDataSourceProperty("cacheResultSetMetadata", info.getCacheResultSetMetadata());
        config.addDataSourceProperty("cacheServerConfiguration", info.getCacheServerConfiguration());
        config.addDataSourceProperty("elideSetAutoCommits", info.getElideSetAutoCommits());
        config.addDataSourceProperty("maintainTimeStats", info.getMaintainTimeStats());
        config.addDataSourceProperty("sslMode", info.getSslMode());
        return new HikariDataSource(config);
    }
}
