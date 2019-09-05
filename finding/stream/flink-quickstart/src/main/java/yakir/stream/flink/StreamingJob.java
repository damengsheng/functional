package yakir.stream.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * StreamingJob
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/05 22:01.
 * @link http://flink.apache.org/docs/latest/apis/streaming/index.html
 */
public class StreamingJob {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.execute("Flink Streaming Java API Skeleton");
    }
}
