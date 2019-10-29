package yakir.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * AsmTransformer
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/06 11:49.
 */
public class AsmTransformer implements ClassFileTransformer {

    private static final Logger log = LogManager.getLogger(AsmTransformer.class);

    private String      className;
    private String      methodName;
    private ClassLoader cloader;

    public AsmTransformer(String className, String methodName, ClassLoader cloader) {
        this.className  = className;
        this.methodName = methodName;
        this.cloader    = cloader;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        log.info("Transforming {}", className);

        byte[] byteCodes      = classfileBuffer;
        String finalClassName = this.className.replaceAll("\\.", "/");
        if (!finalClassName.equals(className)) {
            return byteCodes;
        }

        if (className.equals(finalClassName) && loader.equals(cloader)) {
            log.info("[Agent] Transforming class {}", className);

            try {
                ClassPool cpool = ClassPool.getDefault();
                CtClass   cc    = cpool.get(className);
                CtMethod  m     = cc.getDeclaredMethod(methodName);
                m.addLocalVariable("start", CtClass.longType);
                m.insertBefore("start = System.currentTimeMillis();");

                m.addLocalVariable("end", CtClass.longType);
                m.addLocalVariable("opTime", CtClass.longType);

                String endBlock = "end = System.currentTimeMillis();\n"
                        .concat("opTime = (end - start) / 1000;\n")
                        .concat("log.info(\"[Application] Operation Completed in {} Seconds\", opTime);\n");
                m.insertAfter(endBlock);

                byteCodes = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                log.info("Exception: ", e.fillInStackTrace());
            }
        }

        return byteCodes;
    }
}
