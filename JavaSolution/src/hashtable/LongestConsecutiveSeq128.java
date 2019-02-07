package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 Your algorithm should run in O(n) complexity.

 Example:

 Input: [100, 4, 200, 1, 3, 2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

 */

// 用一个map来记录以x为一个端点的连续序列的长度，
// 遍历一遍数组，对每个数n，在map里查看n+1和n-1是否是其它已有连续数列的端点，然后把n连接上，更新两个端点的值（连续序列长度），
// 同时不断更新max长度即可

public class LongestConsecutiveSeq128 {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {

            if(!map.containsKey(num)) {

                int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;

                int sum = left + right + 1;
                map.put(num, sum);

                res = Math.max(res, sum);
                
                // 更新新sequence的左右两端点表示的值
                map.put(num - left, sum);
                map.put(num + right, sum);
            }
            else {
                continue;
            }
        }

        return res;
    }
}
