package basic.ads.reverse;

/**
 * ReverseString 字符串翻转
 *
 * @author yakir on 2019/11/22 17:40.
 */
public class ReverseString {

    /**
     * 字符数组翻转
     *
     * @param input  输入
     * @param output 输出
     * @param pos    当前Position
     */
    public void charsReverse(char[] input, char[] output, int pos) {

        if (null == input || input.length <= 0 || pos >= input.length)
            return;

        charsReverse(input, output, pos + 1);
        output[input.length - pos - 1] = input[pos];
    }

    public static void main(String[] args) {
        char[] input  = "yakirChen".toCharArray();
        char[] output = new char[input.length];
        new ReverseString().charsReverse(input, output, 0);
        System.out.println(new String(output));
    }

}
