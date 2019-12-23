import binlog.Configuration;
import binlog.Connectors;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TestMySQLConn
 *
 * @author yakir on 2019/11/28 21:06.
 */
public class TestMySQLConn {

    public static void main(String[] args) throws SQLException {
        Configuration info = Configuration.create()
                .setUser("yakir")
                .setPassword("qweasd")
                .setJdbcUrl("");
        DataSource dataSource = Connectors.create()
                .hikaricp(info);

        Connection conn = dataSource.getConnection();
    }
}
