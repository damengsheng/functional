package search.elastic.operator;

/**
 * IOperator
 *
 * @author yakir on 2019/10/29 10:17.
 */
public interface IOperator<IOperator> {

    Integer DEFAULT_NUMBER_OF_SHARDS   = 2;
    Integer DEFAULT_NUMBER_OF_REPLICAS = 0;

    String STATUS                = "status";
    String NUMBER_OF_SHARDS      = "number_of_shards";
    String NUMBER_OF_REPLICAS    = "number_of_replicas";
    String ACTIVE_PRIMARY_SHARDS = "active_primary_shards";
    String ACTIVE_SHARDS         = "active_shards";
    String RELOCATING_SHARDS     = "relocating_shards";
    String INITIALIZING_SHARDS   = "initializing_shards";
    String UNASSIGNED_SHARDS     = "unassigned_shards";
    String SHARDS                = "shards";

    void shutdown();

    IOperator operator();

}
