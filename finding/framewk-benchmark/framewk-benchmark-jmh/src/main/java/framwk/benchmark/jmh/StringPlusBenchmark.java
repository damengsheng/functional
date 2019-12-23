package framwk.benchmark.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * StringPlusBenchmark
 *
 * @author yakir on 2019/11/21 16:51.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringPlusBenchmark {

    String        str;
    StringBuilder strBuilder;
    StringBuffer  strBuffer;

    @Setup
    public void setup() throws Exception {
        str = "";
        strBuilder = new StringBuilder("a");
        strBuffer = new StringBuffer("a");
    }

    @Benchmark
    public String stringPlus() {
        return "a" + "b";
    }

    @Benchmark
    public String stringConcat() {
        return "a".concat("b");
    }

    @Benchmark
    public String stringBuilder() {
        return strBuilder.append("b").toString();
    }

    @Benchmark
    public String stringBuffer() {
        return strBuffer.append("b").toString();
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(StringPlusBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();
    }
}