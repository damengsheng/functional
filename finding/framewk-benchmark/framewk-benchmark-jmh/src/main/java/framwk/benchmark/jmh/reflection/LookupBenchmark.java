package framwk.benchmark.jmh.reflection;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * LookupBenchmark
 *
 * @author yakir on 2019/11/21 16:51.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LookupBenchmark {

    private String               name = "method";
    private MethodType           methodType;
    private MethodHandles.Lookup lookup;

    private Class<?> returnType    = void.class;
    private Class<?> declaringType = LookupBenchmark.class;

    void method() {
        /* empty */
    }

    @Setup
    public void setUp() throws Exception {
        lookup = MethodHandles.lookup();
        methodType = MethodType.methodType(void.class);
    }

    @Benchmark
    public Method reflection() throws Exception {
        return declaringType.getDeclaredMethod(name);
    }

    @Benchmark
    public MethodHandle handle() throws Exception {
        return MethodHandles.lookup().findVirtual(declaringType, name, MethodType.methodType(returnType));
    }

    @Benchmark
    public MethodHandle handlePreLookedUp() throws Exception {
        return lookup.findVirtual(declaringType, name, methodType);
    }
}