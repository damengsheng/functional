package standard.proformace;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayListAllocateMemory
 *
 * @author yakir on 2019/11/18 13:19.
 */
public class ArrayListAllocateMemory {

    private static void allocateMemory() {

        List<byte[]> list = new ArrayList<>();
        int          size = 1024 * 1024 * 480;
        int          len  = size / (20 * 1024);
        for (int i = 0; i < len; i++) {
            try {
                byte[] bytes = new byte[20 * 1024];
                list.add(bytes);
            } catch (OutOfMemoryError e) {

            }
        }

    }
}
