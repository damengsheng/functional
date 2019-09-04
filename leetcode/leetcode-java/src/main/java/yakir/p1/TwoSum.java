package yakir.p1;

import java.util.HashMap;
import java.util.Map;

/**
 * TwoSum
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/05/20 20:25.
 */
public class TwoSum {

    public static void main(String[] args) {

        int[] intArray = {1, 6, 2, 3, 8, 5, 0, 9};
        new Solution().twoSum(intArray, 11);
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int                   len = nums.length;
            Map<Integer, Integer> map = new HashMap<>((int) ((float) len / 0.75) + 1);
            for (int i = 0; i < len; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(complement), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("");
        }
    }
}


