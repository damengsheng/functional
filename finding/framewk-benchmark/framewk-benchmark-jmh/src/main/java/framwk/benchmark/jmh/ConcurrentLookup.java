package framwk.benchmark.jmh;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * ConcurrentLookup
 *
 * @author yakir on 2019/11/27 13:13.
 */
public class ConcurrentLookup {

    public static void main(String[] args) throws InterruptedException {

        final AtomicInteger        ai     = new AtomicInteger(0);
        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        List<Callable<Foo>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(() -> {
                try {
                    MethodType mtype = MethodType.methodType(Foo.class, String.class);
                    return (Foo) lookup.findVirtual(Foo.class, "foo", mtype).invokeExact(new Foo(), "foo" + ai.getAndIncrement());
                } catch (Throwable throwable) {
                    return null;
                }
            });
        }

        ExecutorService   exec    = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Foo>> futures = exec.invokeAll(list);

        Iterator<Future<Foo>> iterator = futures.iterator();
        for (; iterator.hasNext(); ) {
            Future<Foo> future = iterator.next();
            try {
                Foo foo = future.get(100, TimeUnit.MILLISECONDS);
                System.out.println(foo.foo());
            } catch (ExecutionException | TimeoutException e) {
                continue;
            }
        }
    }

    private static class Foo {

        private String foo;

        public Foo foo(String foo) {
            this.foo = foo;
            return this;
        }

        public String foo() {
            return foo;
        }
    }

}
