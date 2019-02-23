package twopointer;

import java.util.HashMap;
import java.util.Map;

// 用two pointer来构造一个滑动窗口，用map来记录出现的count，distinct个数大于指定值时就左端移动，否则移动右端

public class LgstSubstrWithAtMost2DistinctCh159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int end = 0;
        int count = 0;
        int maxLen = 0;


        while(end < s.length()) {
            char ch = s.charAt(end);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if(map.get(ch) == 1) {
                // new ch
                count++;
            }

            end++;

            while(count > 2) { // distinct个数大于指定值，左端向右移动
                char chStart = s.charAt(begin);
                map.put(chStart, map.get(chStart) - 1);
                if(map.get(chStart) == 0) {
                    count--;
                }
                begin++;
            }

            maxLen = Math.max(maxLen, end - begin);
        }

        return maxLen;
    }
}
