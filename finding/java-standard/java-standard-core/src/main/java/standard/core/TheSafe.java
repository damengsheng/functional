package standard.core;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * TheUnsafe
 *
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 09/21/2019 14:55.
 */
public class TheSafe {

    public TheSafe() {
    }

    public void theSafe(TheSafe theSafe) throws Throwable {

        MethodType methodType = MethodType.methodType(String.class, String.class);
        MethodHandle methodHandle = MethodHandles.publicLookup()
                .findVirtual(TheSafe.class, "hello", methodType);
        Object greeting0 = methodHandle.invoke(theSafe, "yakir");
        String greeting  = greeting0.toString();
        System.out.println(greeting);

        methodType = MethodType.methodType(String.class, String.class);
        methodHandle = MethodHandles.publicLookup()
                .findVirtual(TheSafe.class, "hello", methodType);
        String greeting1 = (String) methodHandle.invokeExact(theSafe, "yakirChen");
        System.out.println(greeting1);
    }

    public String hello(String name) {
        return "hello ".concat(name).concat(" !");
    }

    public static void main(String[] args) throws Throwable {
        TheSafe theSafe = new TheSafe();
        theSafe.theSafe(theSafe);
    }
}