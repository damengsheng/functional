import org.junit.jupiter.api.Test;
import search.elastic.common.ConfigProperties;
import search.elastic.common.Operators;
import search.elastic.operator.HOperator;
import search.elastic.operator.LOperator;

/**
 * TestElasticClientBuild
 *
 * @author yakir on 2019/11/27 21:43.
 */
public class TestElasticClientBuild {

    @Test
    public void testClientBuild() {

        ConfigProperties info = new ConfigProperties();
        info.setNodes("127.0.0.1:9200");

        HOperator hOperator = Operators.hOperator(info);
        LOperator lOperator = Operators.lOperator(info);

    }

    @Test
    public void testSQLRequest() {

        String sql = "Select * from \"master-pro_shushi_himalaya_item-ppp-tmodel_itemsearch\"";

        ConfigProperties info = new ConfigProperties();
        info.setNodes("127.0.0.1:9200");
        LOperator lOperator = Operators.lOperator(info);
        lOperator.sql(sql);
    }
}
