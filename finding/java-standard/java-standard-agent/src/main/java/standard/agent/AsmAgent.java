package standard.agent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AsmAgent
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/06 11:56.
 */
public class AsmAgent {

    private static final Logger log = LogManager.getLogger(AsmAgent.class);

    public static void premain(String args, Instrumentation inst) {

        log.info("[Agent] In premain method");
        transformClass(args, inst);

    }

    public static void agentmain(String args, Instrumentation inst) {

        log.info("[Agent] In agentmain method");
        transformClass(args, inst);

    }

    private static void transformClass(String args, Instrumentation inst) {

        List<String[]> clzMethodList = Arrays.stream(Optional.ofNullable(args).map(_args -> _args.split(",")).orElse(new String[]{}))
                .peek(_arg -> log.info("Agent Arg [{}]", _arg))
                .map(_arg -> _arg.split(":"))
                .collect(Collectors.toList());

        String className  = clzMethodList.get(0)[0];
        String methodName = clzMethodList.get(0)[1];

        Class<?>    clz     = null;
        ClassLoader cloader = null;

        try {
            clz     = Class.forName(className);
            cloader = clz.getClassLoader();
            transform(clz, methodName, cloader, inst);
        } catch (ClassNotFoundException e) {
            log.error("Error: ", e);
        }

        for (Class<?> clazz : inst.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                clz     = clazz;
                cloader = clz.getClassLoader();
                transform(clz, methodName, cloader, inst);
                return;
            }
        }

        throw new RuntimeException("Failed to find class [".concat(className).concat("]"));

    }

    private static void transform(Class<?> clz, String methodName, ClassLoader cloader, Instrumentation instrumentation) {

        AsmTransformer asmTransformer = new AsmTransformer(clz.getName(), methodName, cloader);
        instrumentation.addTransformer(asmTransformer);
        try {
            instrumentation.retransformClasses(clz);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(String.format("Transform Failed For Class %s", clz.getName()), e);
        }
    }

}
