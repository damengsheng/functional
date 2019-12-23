package standard.core;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TheUnsafe
 *
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 09/21/2019 14:55.
 */
public class TheSafe {

    public TheSafe() {
    }

    public void theSafe(TheSafe theSafe) throws Throwable {

        MethodType   methodType   = MethodType.methodType(String.class, String.class);
        MethodHandle methodHandle = MethodHandles.publicLookup().findVirtual(TheSafe.class, "hello", methodType);
        Object       greeting0    = methodHandle.invoke(theSafe, "yakir");
        String       greeting     = greeting0.toString();
        System.out.println(greeting);

        MethodType   methodType1   = MethodType.methodType(String.class, String.class);
        MethodHandle methodHandle1 = MethodHandles.publicLookup().findVirtual(TheSafe.class, "hello", methodType1);
        String       greeting1     = (String) methodHandle1.invokeExact(theSafe, "yakirChen");
        System.out.println(greeting1);
    }

    public String hello(String name) {
        return "hello ".concat(name).concat(" !");
    }

    public List<Map<String, Object>> greeting(String name, String myName, List<Map<String, Object>> data) {


        return data;
    }

    public void theSafeList(TheSafe theSafe) throws Throwable {

        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object>       map  = new HashMap<>();
        map.put("one", 1);
        data.add(map);

        MethodType   methodType   = MethodType.methodType(List.class, String.class, String.class, List.class);
        MethodHandle methodHandle = MethodHandles.publicLookup().findVirtual(TheSafe.class, "greeting", methodType);
        Object       greeting0    = methodHandle.invoke(theSafe, "yakir", "yakirChen", data);
        String       greeting     = greeting0.toString();
        System.out.println(greeting);
    }


    public static void main(String[] args) throws Throwable {
        TheSafe theSafe = new TheSafe();
//        theSafe.theSafe(theSafe);
        theSafe.theSafeList(theSafe);
    }
}