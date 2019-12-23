package search.elastic.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;
import search.elastic.operator.HOperator;

/**
 * HOperatorBuilder
 *
 * @author yakir on 2019/10/29 11:55.
 */
public class HOperatorBuilder extends AbstractBuilder {

    private static final Logger log = LogManager.getLogger(HOperatorBuilder.class);

    @Override
    public HOperator operator() {

        return new HOperator(new RestHighLevelClient(restClient()));
    }
}
