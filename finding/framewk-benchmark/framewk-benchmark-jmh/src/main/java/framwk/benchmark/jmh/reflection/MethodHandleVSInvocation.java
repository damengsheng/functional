package framwk.benchmark.jmh.reflection;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * MethodHandleVSInvocation
 *
 * @author yakir on 2019/11/21 16:51.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Threads(3)
public class MethodHandleVSInvocation {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(FieldBenchmark.class.getSimpleName())
                .include(InvocationBenchmark.class.getSimpleName())
                .include(LookupBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
