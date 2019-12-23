package search.elastic.operator;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Optional;

/**
 * LOperator
 *
 * @author yakir on 2019/11/13 17:35.
 */
public class LOperator extends AbstractOperator<RestClient, LOperator> {

    private static final Logger log = LogManager.getLogger(LOperator.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public LOperator(RestClient restClient) {
        this.client = restClient;
    }

    public String sql(String sql) {

        ObjectNode queryNode = mapper.createObjectNode();
        queryNode.put("query", sql);

        Request request = new Request(HttpPost.METHOD_NAME, "/_sql");
        request.addParameter("format", "json");
        request.setJsonEntity(queryNode.toString());
        Response response = null;
        try {
            response = client.performRequest(request);
        } catch (IOException e) {
            log.error("Request Err SQL: [{}]", sql, e);
        }

        Optional.ofNullable(response)
                .map(_response -> _response.getEntity())
                .ifPresent(_entity -> {
                    try {
                        String    str         = EntityUtils.toString(_entity, StandardCharsets.UTF_8);
                        TreeNode  tree        = mapper.readTree(str);
                        ArrayNode columnsNode = null;
                        if (tree.isObject() && null != (columnsNode = (ArrayNode) tree.get("columns"))) {
                            Iterator<JsonNode> iterator = columnsNode.elements();
                            for (; iterator.hasNext(); ) {
                                JsonNode column = iterator.next();
                                log.info("Column {}", column.toString());
                            }
                        }

                        ArrayNode rowsNode = null;
                        if (tree.isObject() && null != (rowsNode = (ArrayNode) tree.get("rows"))) {
                            Iterator<JsonNode> rows = rowsNode.iterator();
                            for (; rows.hasNext(); ) {
                                JsonNode row = rows.next();
                                log.info("Row {}", row.toString());
                            }
                        }
                    } catch (IOException | ParseException e) {
                    }
                })
        ;

        return sql;
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

    @Override
    public LOperator operator() {
        return this;
    }
}