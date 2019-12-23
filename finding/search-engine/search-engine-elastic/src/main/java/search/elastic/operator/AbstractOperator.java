package search.elastic.operator;

/**
 * AbstractOperator
 *
 * @author yakir on 2019/11/27 18:29.
 */
abstract
public class AbstractOperator<CLIENT, OPTERATOR> implements IOperator<OPTERATOR> {

    protected CLIENT client;

}
