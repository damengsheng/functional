package standard.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * BytesConvert
 *
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 2019/10/30 20:20.
 */
public class BytesConvert {

    public static void main(String[] args) {
        int           i  = 0;
        StringBuilder sb = new StringBuilder();
        try (InputStream is = new BufferedInputStream(new FileInputStream(new File("/Volumes/sm/logs/hprof/bytes.csv")))) {
            int      len    = is.available();
            byte[]   bytes  = new byte[len];
            int      readRt = is.read(bytes);
            String   str    = new String(bytes, StandardCharsets.UTF_8);
            String[] list   = str.split(",");
            for (String s : list) {
                byte b = Byte.parseByte(s);
                char c = (char) b;
                sb.append(c);
                i++;
                if (i > 3000) {
                    break;
                }
            }
        } catch (IOException e) {

        }
        System.out.println(sb.toString());
        System.out.println("hello end");
    }
}
