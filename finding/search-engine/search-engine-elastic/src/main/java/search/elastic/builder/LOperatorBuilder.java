package search.elastic.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import search.elastic.operator.LOperator;

/**
 * LOperatorBuilder
 *
 * @author yakir on 2019/10/29 11:55.
 */
public class LOperatorBuilder extends AbstractBuilder {

    private static final Logger log = LogManager.getLogger(LOperatorBuilder.class);

    @Override
    public LOperator operator() {

        return new LOperator(restClient().build());
    }
}
