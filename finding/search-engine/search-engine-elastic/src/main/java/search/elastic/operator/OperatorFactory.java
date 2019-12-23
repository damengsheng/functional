package search.elastic.operator;

import search.elastic.builder.IBuilder;
import search.elastic.common.ConfigProperties;

/**
 * OperatorFactory
 *
 * @author yakir on 2019/10/29 14:07.
 */
public class OperatorFactory {

    public static <HL extends IOperator> HL operator(IBuilder builder, ConfigProperties info) {

        return builder.info(info)
                .nodes()
                .operator();
    }
}
