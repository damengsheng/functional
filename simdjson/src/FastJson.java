import com.alibaba.fastjson.JSON;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FastJson
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 02/15/2019 19:09.
 */
public class FastJson {

    public static void main(String[] args)
            throws InterruptedException {

        String          fileName = args[0];
        FastJson        fastjson = new FastJson();
        ExecutorService exec     = Executors.newFixedThreadPool(12);
        for (int i = 0; i <= 4096; i++) {
            exec.execute(() -> fastjson.exec(fileName, false));
        }

        exec.awaitTermination(30, TimeUnit.SECONDS);
        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                fastjson.exec(fileName, true);
                break;
            }
        }
    }

    public void exec(String fileName,
                     Boolean isShow) {

        Long start, end = null;
        start = System.currentTimeMillis();
        try (InputStream is = new BufferedInputStream(new FileInputStream(new File(fileName)))) {
            HashMap obj = JSON.parseObject(is, HashMap.class);
            end = System.currentTimeMillis();
            int size = obj.size();
            if (isShow) {
                System.out.println("==========================");
                System.out.printf("start %d end %d total %d\n", start, end, end - start);
                System.out.println("==========================");
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}