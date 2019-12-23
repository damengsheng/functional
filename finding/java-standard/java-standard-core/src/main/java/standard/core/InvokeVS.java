package standard.core;

import standard.core.foo.Foobar;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * InvokeVS
 *
 * @author yakir on 2019/11/21 21:13.
 */
public class InvokeVS {

    public static void main(String[] args) {

        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            callables.add(() -> {
                try {
                    return new InvokeVS().invoke0();
                } catch (Throwable throwable) {
                    return "";
                }
            });
            callables.add(() -> {
                try {
                    return new InvokeVS().invoke1();
                } catch (Throwable throwable) {
                    return "";
                }
            });
            callables.add(() -> {
                try {
                    return new InvokeVS().invoke2();
                } catch (Throwable throwable) {
                    return "";
                }
            });
            callables.add(() -> {
                try {
                    return new InvokeVS().invoke3();
                } catch (Throwable throwable) {
                    return "";
                }
            });
            callables.add(() -> {
                try {
                    return new InvokeVS().invoke4();
                } catch (Throwable throwable) {
                    return "";
                }
            });
        }

        ExecutorService      exec    = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> futures = new ArrayList<>();
        try {
            futures = exec.invokeAll(callables);
        } catch (InterruptedException e) {
            //
        }

        Map<String, List<String>> map = new HashMap<>();

        List<String> invoke0 = new ArrayList<>();
        List<String> invoke1 = new ArrayList<>();
        List<String> invoke2 = new ArrayList<>();
        List<String> invoke3 = new ArrayList<>();
        List<String> invoke4 = new ArrayList<>();
        map.put("invoke0", invoke0);
        map.put("invoke1", invoke1);
        map.put("invoke2", invoke2);
        map.put("invoke3", invoke3);
        map.put("invoke4", invoke4);
        for (Future<String> future : futures) {
            try {
                String rt = future.get(10, TimeUnit.MILLISECONDS);
                if (rt.startsWith("invoke0")) {
                    invoke0.add(rt);
                } else if (rt.startsWith("invoke1")) {
                    invoke1.add(rt);
                } else if (rt.startsWith("invoke2")) {
                    invoke2.add(rt);
                } else if (rt.startsWith("invoke3")) {
                    invoke3.add(rt);
                } else if (rt.startsWith("invoke4")) {
                    invoke4.add(rt);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                continue;
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String rt : entry.getValue()) {
                System.out.println(rt);
            }
            System.out.println("\n");
        }

        exec.shutdown();
    }

    public String invoke0() throws Throwable {
        Long   start    = 0L;
        Long   end      = 0L;
        Foobar foobar   = null;
        String input    = "fooInput";
        String fooOuput = null;

        start = System.nanoTime();
        foobar = new Foobar();
        MethodHandles.Lookup lookup1    = MethodHandles.privateLookupIn(Foobar.class, MethodHandles.lookup());
        Field                field1     = Foobar.class.getDeclaredField("foo");
        VarHandle            varHandle1 = lookup1.unreflectVarHandle(field1);
        varHandle1.set(foobar, input);
//        Field     field2     = Foobar.class.getDeclaredField("foo");
        VarHandle varHandle2 = lookup1.unreflectVarHandle(field1);
        fooOuput = (String) varHandle2.get(foobar);
        end = System.nanoTime();
        return String.format("invoke0 %s %s %d %d %d", input, fooOuput, start, end, (end - start) / 10000);
    }

    public String invoke1() throws Throwable {
        Long   start    = 0L;
        Long   end      = 0L;
        Foobar foobar   = null;
        String input    = "fooInput";
        String fooOuput = null;

        start = System.nanoTime();
        foobar = new Foobar();
        MethodHandles.Lookup lookup       = MethodHandles.privateLookupIn(Foobar.class, MethodHandles.lookup());
        MethodHandle         fooSetHandle = lookup.findSetter(Foobar.class, "foo", String.class);
        fooSetHandle.invokeExact(foobar, input);
        MethodHandle fooGetHandle = lookup.findGetter(Foobar.class, "foo", String.class);
        fooOuput = (String) fooGetHandle.invokeExact(foobar);
        end = System.nanoTime();
        return String.format("invoke1 %s %s %d %d %d", input, fooOuput, start, end, (end - start) / 10000);
    }

    public String invoke2() throws Throwable {
        Long   start    = 0L;
        Long   end      = 0L;
        Foobar foobar   = null;
        String input    = "fooInput";
        String fooOuput = null;

        start = System.nanoTime();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        foobar = new Foobar();
        Field field = Foobar.class.getDeclaredField("foo");
        field.setAccessible(true);
        MethodHandle fooSetHandle0 = lookup.unreflectSetter(field);
        fooSetHandle0.invoke(foobar, input);
        field.setAccessible(true);
        MethodHandle fooGetHandle0 = lookup.unreflectGetter(field);
        fooOuput = (String) fooGetHandle0.invoke(field.getDeclaringClass().cast(foobar));
        end = System.nanoTime();
        return String.format("invoke2 %s %s %d %d %d", input, fooOuput, start, end, (end - start) / 10000);
    }

    public String invoke3() throws Throwable {
        Long   start    = 0L;
        Long   end      = 0L;
        Foobar foobar   = null;
        String input    = "fooInput";
        String fooOuput = null;

        start = System.nanoTime();
        foobar = new Foobar();
        MethodHandles.Lookup lookup0         = MethodHandles.lookup();
        MethodType           methodTypeSet   = MethodType.methodType(Foobar.class, String.class);
        MethodHandle         methodHandleSet = lookup0.findVirtual(Foobar.class, "setFoo", methodTypeSet);
        methodHandleSet.invoke(foobar, input);
        MethodType   methodTypeGet   = MethodType.methodType(String.class);
        MethodHandle methodHandleGet = lookup0.findVirtual(Foobar.class, "getFoo", methodTypeGet);
        fooOuput = (String) methodHandleGet.invoke(foobar);
        end = System.nanoTime();
        return String.format("invoke3 %s %s %d %d %d", input, fooOuput, start, end, (end - start) / 10000);
    }

    public String invoke4() throws Throwable {
        Long   start    = 0L;
        Long   end      = 0L;
        Foobar foobar   = null;
        String input    = "fooInput";
        String fooOuput = null;

        start = System.nanoTime();
        foobar = new Foobar();
        Method fooSetMethod = Foobar.class.getMethod("setBar", String.class);
        fooSetMethod.invoke(foobar, input);
        Method fooGetMethod = Foobar.class.getMethod("getBar");
        fooOuput = (String) fooGetMethod.invoke(foobar);
        end = System.nanoTime();
        return String.format("invoke4 %s %s %d %d %d", input, fooOuput, start, end, (end - start) / 10000);
    }
}
