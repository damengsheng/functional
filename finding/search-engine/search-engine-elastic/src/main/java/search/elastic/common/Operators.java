package search.elastic.common;

import search.elastic.builder.HOperatorBuilder;
import search.elastic.builder.LOperatorBuilder;
import search.elastic.operator.HOperator;
import search.elastic.operator.LOperator;
import search.elastic.operator.OperatorFactory;

/**
 * Operators
 *
 * @author yakir on 2019/11/27 19:47.
 */
public class Operators {

    public static LOperator lOperator(ConfigProperties info) {

        return OperatorFactory.operator(new LOperatorBuilder(), info);
    }

    public static HOperator hOperator(ConfigProperties info) {

        return OperatorFactory.operator(new HOperatorBuilder(), info);
    }
}
