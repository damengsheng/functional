package framwk.benchmark.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Defaults;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Benchmark                    (length)  Mode  Cnt    Score    Error  Units
 * JMHSample.multiThreadBench      10000  avgt   10   19.304 ±  2.758  us/op
 * JMHSample.multiThreadBench     100000  avgt   10   35.281 ±  2.619  us/op
 * JMHSample.multiThreadBench    1000000  avgt   10  113.984 ±  3.755  us/op
 * JMHSample.singleThreadBench     10000  avgt   10    2.796 ±  0.247  us/op
 * JMHSample.singleThreadBench    100000  avgt   10   30.883 ±  4.488  us/op
 * JMHSample.singleThreadBench   1000000  avgt   10  274.902 ± 25.215  us/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHSample {

    @Param({"10000", "100000", "1000000"})
    private int length;

    private int[]      numbers;
    private Calculator singleThreadCalc;
    private Calculator multiThreadCalc;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JMHSample.class.getSimpleName())
                .forks(2)
                .warmupIterations(Defaults.WARMUP_ITERATIONS)
                .measurementIterations(Defaults.MEASUREMENT_ITERATIONS)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void prepare() {
        numbers = IntStream.rangeClosed(1, length).toArray();
        singleThreadCalc = new SinglethreadCalculator();
        multiThreadCalc = new MultithreadCalculator(Runtime.getRuntime().availableProcessors());
    }

    @Benchmark
    public long singleThreadBench() {
        return singleThreadCalc.sum(numbers);
    }

    @Benchmark
    public long multiThreadBench() {
        return multiThreadCalc.sum(numbers);
    }

    @TearDown
    public void shutdown() {
        singleThreadCalc.shutdown();
        multiThreadCalc.shutdown();
    }

}
