package basic.ads.reverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PascalTriangle
 * <p>
 * 帕斯卡三角[杨辉三角]（Pascal's Triangle）
 *
 * <pre>
 *                      1
 *                    1   1
 *                  1   2   1
 *                1   3   3   1
 *              1   4   6   4   1
 *            1   5  10  10   5   1
 *          1   6  15  20  15   6   1
 *        1   7  21  35  35  21   7   1
 *      1   8  28  56  70  56  28   8   1
 *    1   9  36  84 126 126  84  36   9   1
 *
 * </pre>
 *
 * <pre>
 *     递推关系 fn(i - 1, j - 1) + fn(i - 1, j)
 *     基本情况 当 i或j为1时 fn(i,j) = 1
 * </pre>
 *
 * @author yakir on 2019/12/03 15:18.
 */
public class PascalTriangle {

    private static int fn(int i, int j) {

        if (j == 1 || i == j) {
            return 1;
        } else {
            return fn(i - 1, j - 1) + fn(i - 1, j);
        }
    }

    public List<List<Integer>> pascalTriangle(int nums) {

        List<List<Integer>> lines = new ArrayList<>();

        if (nums < 1) {
            return lines;
        }

        for (int i = 1; i <= nums; i++) {

            List<Integer> line = new ArrayList<>();

            for (int j = 1; j <= i; j++) {
                int num = fn(i, j);
                line.add(num);
            }
            lines.add(line);
        }

        return lines;
    }

    public String format(List<List<Integer>> lines) {

        String fmt = "";

        int size = lines.size();
        for (int i = 0; i < size; i++) {

            String spaces = Optional.of((size - i) * 2)
                    .filter(_space -> _space > 0)
                    .map(_space -> String.format("%" + _space + "s", " "))
                    .orElse("");

            fmt = fmt.concat(spaces);

            List<Integer> line = lines.get(i);
            for (Integer integer : line) {
                String cdata = String.format("%4d", integer);
                fmt = fmt.concat(cdata);
            }
            fmt = fmt.concat("\n");
        }
        return fmt;
    }

    public static void main(String[] args) {

        final int           count          = 10;
        PascalTriangle      pascalTriangle = new PascalTriangle();
        List<List<Integer>> lines          = pascalTriangle.pascalTriangle(count);
        String              str            = pascalTriangle.format(lines);
        System.out.println(str);

    }

}
