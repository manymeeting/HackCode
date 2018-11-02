package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */

// 用两个index来构造一个sliding window，一个int[128]充当map来记录ch出现次数，
// 每次移动right，遇到已有的ch后，left向右移动，直到达到之前相同ch的下一个位置

public class LgestSubstrWithoutRepeatCh3 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] map = new int[128];
        int maxLen = 1;

        int left = 0, right = 0;
        while(right < s.length()) {
            char currCh = s.charAt(right);

            if(map[currCh] == 0) {
                map[currCh]++;
                maxLen = Math.max(maxLen, right - left + 1);
            }
            else {
                while(s.charAt(left) != currCh) {
                    map[s.charAt(left)]--;
                    left++;
                }
                left++; //移动到之前相同ch的下一个位置，pww... -> w...
            }

            right++;
        }

        return maxLen;
    }
}
