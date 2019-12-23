import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Hello {


    public static void main(String[] args) {

//        ExecutorService exec = Executors.newFixedThreadPool(2, new ThreadFactory() {
//
//            AtomicInteger ai = new AtomicInteger(0);
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "hold-" + ai.getAndIncrement());
//            }
//        });
//
//        for (; ; ) {
//            exec.submit(new Hold());
////            exec.submit(new HoldForever());
//            try {
//                TimeUnit.SECONDS.sleep(5L);
//            } catch (InterruptedException e) {
//                // ignore
//            }
//        }


        for (int i = 0; i < 1000; i++) {
            new Hello().createMap();

            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                // ignore
            }
        }

    }

    public void createMap() {

        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new HashMap<>());
        }

    }

    private static class Hold implements Runnable {

        @Override
        public void run() {
            try {
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < 10; i++) {
                    map.put(i, i);
                }
                TimeUnit.SECONDS.sleep(10L);
                System.out.println(map.size());
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    private static class HoldForever implements Runnable {

        @Override
        public void run() {
            for (; ; ) ;
        }
    }
}