package standard.agent;

/**
 * Log
 *
 * @author yakir on 2019/11/16 17:13.
 */
public class Log {

    private static final String PREFIX = "\033[";
    private static final String RESET  = "\033[0m";

    private static String colorful(String msg, int colorCode) {
        return PREFIX + colorCode + "m" + msg + RESET;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            String msg =colorful("yakirChen   ", i);
            System.out.println(msg);
        }
    }
}
