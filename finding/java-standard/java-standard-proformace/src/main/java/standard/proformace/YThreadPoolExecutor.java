package standard.proformace;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * YThreadPoolExecutor
 *
 * @author yakir on 2019/12/17 13:24.
 */
public class YThreadPoolExecutor {

    public static void main(String[] args) {

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.DAYS, queue, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString());
            }
        });

//        for (int i = 0; i < 8; i++) {
//        for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 1; i++) {
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    TimeUnit.HOURS.sleep(1L);
                } catch (InterruptedException e) {
                    System.out.println(e.getCause());
                }
            });
        }
    }
}
