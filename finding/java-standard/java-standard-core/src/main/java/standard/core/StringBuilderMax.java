package standard.core;

/**
 * StringBuilderMax
 *
 * @author yakir on 2019/12/23 21:45.
 */
public class StringBuilderMax {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sb.append("a");
        }
        System.out.println(sb.toString());

    }
}
