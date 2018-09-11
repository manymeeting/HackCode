package strings;

import java.util.Arrays;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 Note: You may assume the string contain only lowercase letters.
 */

// 用int[26]来处理，注意：不能用int[128]和int[ch-'0']，因为'0'的ascii不是0（0对应的是一个control），所以得到的index有偏差
public class FirstUniqueChInAString387 {

    public int firstUniqChar(String s) {
        int[] countMap = new int[26];
        for (char ch : s.toCharArray())
        {
            countMap[ch-'a']++;
        }
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if(countMap[ch-'a'] == 1)
            {
                return i;
            }
        }

        return -1;
    }
}
