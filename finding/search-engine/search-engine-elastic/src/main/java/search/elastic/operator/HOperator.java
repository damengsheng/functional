package search.elastic.operator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Optional;

/**
 * HOperator
 *
 * @author yakir on 2019/11/13 17:35.
 */
public class HOperator extends AbstractOperator<RestHighLevelClient, HOperator> {

    private static final Logger log = LogManager.getLogger(HOperator.class);

    public HOperator(RestHighLevelClient highLevelClient) {
        this.client = highLevelClient;
    }

    public String sql(String sql) {

        return sql;
    }

    @Override
    public HOperator operator() {
        return this;
    }

    @Override
    public void shutdown() {
        Optional.ofNullable(this.client)
                .ifPresent(_client -> {
                    try {
                        _client.close();
                    } catch (IOException e) {
                        // do nothing ...
                    }
                });
    }
}
