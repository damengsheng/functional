package search.elastic.builder;

import org.elasticsearch.client.RestClientBuilder;
import search.elastic.common.ConfigProperties;
import search.elastic.operator.IOperator;

/**
 * IBuilder
 *
 * @author yakir on 2019/10/29 10:17.
 */
public interface IBuilder {

    IBuilder info(ConfigProperties info);

    IBuilder nodes();

    RestClientBuilder restClient();

    <OPT extends IOperator> OPT operator();

}
