package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

 Example 1:

 Input: S = "loveleetcode", C = 'e'
 Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]


 Note:

 S string length is in [1, 10000].
 C is a single character, and guaranteed to be in string S.
 All letters in S and C are lowercase.

 */


// 先遍历一遍，记录目标字符所有出现的index，然后遍历index，同时维护一个preIdx，对每一个数组元素计算到preIdx和当前idx的距离最小值，
// 注意最后要处理剩下的部分（目标字符最后一次出现后的右边部分）

public class ShortestDistancetoAChar821 {
    public int[] shortestToChar(String S, char C) {
        List<Integer> charIndices = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == C) {
                charIndices.add(i);
            }
        }
        int[] res = new int[S.length()];

        int start = 0;
        int prevIdx = charIndices.get(0); // prevIdx初始为第一个idx

        for (int idx : charIndices) {
            for (int i = start; i <= idx; i++) {
                // 取到preIdx和idx的最小距离
                res[i] = Math.max(Math.abs(i - prevIdx), Math.abs(i - idx));
            }
            start = idx + 1;
            prevIdx = idx;
        }

        // 处理剩下的部分（最后出现的右边）
        for (int i = prevIdx; i < S.length(); i++) {
            res[i] = Math.abs(i - prevIdx);
        }

        return res;
    }
}
