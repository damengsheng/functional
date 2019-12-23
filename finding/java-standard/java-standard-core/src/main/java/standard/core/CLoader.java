package standard.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * CLoader
 * <p>
 * 1. jdk8: -XX:+TraceClassUnloading
 * 2. jdk9+: -Xlog:class+unload=info
 * </p>
 *
 * @author yakir on 2019/11/19 19:40.
 */
public class CLoader {

    public static void main(String[] args) throws Exception {

        ClassLoaderB loaderB = new ClassLoaderB("CLB");
        loaderB.setPath("/Users/yakir/Developer/think/functional/finding/java-standard/java-standard-core/src/main/java/");
        Class<?> clazz  = loaderB.loadClass("standard.core.TheSafe");
        Object   object = clazz.getDeclaredConstructor().newInstance();
        System.out.println(object);

        System.out.println("-----------------");
        loaderB = null;
        clazz = null;
        object = null;

        System.gc();

        // System.gc();
        while (true) {
            TimeUnit.MINUTES.sleep(1);
        }
    }

    private static class ClassLoaderA extends ClassLoader {}

    private static class ClassLoaderB extends ClassLoader {

        private String classLoaderName;

        //类的扩展名
        private final String fileExtension = ".class";

        private String path;

        public void setPath(String path) {
            this.path = path;
        }

        public ClassLoaderB(String classLoaderName) {
            super();
            this.classLoaderName = classLoaderName;
        }

        public ClassLoaderB(String classLoaderName, ClassLoader parent) {
            super(parent);
            this.classLoaderName = classLoaderName;
        }

        @Override
        protected Class<?> findClass(String className) throws ClassNotFoundException {
            System.out.println("findClass invoked: " + className);
            System.out.println("class loader name: " + this.classLoaderName);
            byte[] data = this.loadClassDate(className);
            return this.defineClass(className, data, 0, data.length);
        }

        private byte[] loadClassDate(String name) {
            byte[] data = null;
            try (InputStream is = new FileInputStream(new File(this.path + name.replace(".", "/") + this.fileExtension));
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                int ch = 0;
                while ((ch = is.read()) != -1) {
                    baos.write(ch);
                }
                data = baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
    }
}
