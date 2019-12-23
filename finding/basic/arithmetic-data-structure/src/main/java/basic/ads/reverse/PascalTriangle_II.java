package basic.ads.reverse;

/**
 * PascalTriangle II
 *
 * <pre>
 *     递推关系 fn(i - 1, j - 1) + fn(i - 1, j)
 *     基本情况 当 i或j为1时 fn(i,j) = 1
 * </pre>
 *
 * @author yakir on 2019/12/03 15:18.
 */
public class PascalTriangle_II {

    public int[] pascalTriangle(int nums) {

        int[] line = new int[nums + 1];
        // Arrays.fill(line, 0);
        // 这里声明的是基本类型 int[] 数组初始化之后数组元素全部填0
        // 如果声明的是包装类型 Integer[] 数组初始化之后数组元素全部为null需要手动填0
        line[0] = 1;

        for (int i = 1; i <= nums; i++) {
            for (int j = i; j > 0; j--) {
                line[j] = line[j] + line[j - 1];
            }
        }
        return line;
    }

    public String format(int[] lines) {

        String fmt = "";

        int size = lines.length;
        for (int i = 0; i < size; i++) {
            String cdata = String.format("%4d", lines[i]);
            fmt = fmt.concat(cdata);
        }
        return fmt;
    }

    public static void main(String[] args) {

        final int         count          = 10;
        PascalTriangle_II pascalTriangle = new PascalTriangle_II();
        int[]             lines          = pascalTriangle.pascalTriangle(count);
        String            str            = pascalTriangle.format(lines);
        System.out.println(str);
    }

}
