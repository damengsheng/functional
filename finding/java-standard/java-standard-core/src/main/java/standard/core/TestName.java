package standard.core;

/**
 * TestName
 *
 * @author yakir on 2019/12/25 11:20.
 */
public class TestName {

    public static void main(String[] args) {

        String rt = sub("qa");
        System.out.println(rt);

        rt = sub("q");
        System.out.println(rt);

        rt = sub("A");
        System.out.println(rt);

    }

    public static String sub(String valName) {
        String rt = null;
//        if (valName.length() == 1) {
//            rt = valName.substring(0, 1).toUpperCase();
//        } else {
        rt = valName.substring(0, 1).toUpperCase() + valName.substring(1);
//        }
        return rt;
    }
}
