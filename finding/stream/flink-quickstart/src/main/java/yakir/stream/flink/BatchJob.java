package yakir.stream.flink;

import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * StreamingJob
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/05 22:01.
 * @link http://flink.apache.org/docs/latest/apis/batch/examples.html
 */
public class BatchJob {

    public static void main(String[] args) throws Exception {

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        env.execute("Flink Batch Java API Skeleton");
    }
}
