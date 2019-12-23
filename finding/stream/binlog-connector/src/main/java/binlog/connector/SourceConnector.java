package binlog.connector;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * SourceConnector
 *
 * @author yakir on 2019/11/20 13:53.
 */
public class SourceConnector {

    private static final Logger log = LogManager.getLogger(SourceConnector.class);

    public static void main(String[] args) {

        String user = "yakir";
        String pwd  = "qweasd";

        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, user, pwd);

        client.registerEventListener(log::info);

        try {
            client.connect();
        } catch (IOException e) {
            log.error("Connect Err ", e);
        }
    }
}
