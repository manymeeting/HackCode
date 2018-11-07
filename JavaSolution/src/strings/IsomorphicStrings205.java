package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 Example 1:

 Input: s = "egg", t = "add"
 Output: true
 Example 2:

 Input: s = "foo", t = "bar"
 Output: false
 Example 3:

 Input: s = "paper", t = "title"
 Output: true
 Note:
 You may assume both s and t have the same length.
 */

// 用一个map，记录s里的ch和t里ch的对应关系，遇到key相同val不同，或者val相同key不同的情况都是false
    
public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character, Character> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char chS = s.charAt(i);
            char chT = t.charAt(i);
            if(charMap.containsKey(chS)) {
                if (chT == charMap.get(chS))
                {
                    continue;
                }
                else return false;
            }
            else
            {
                if(charMap.containsValue(chT)) // 注意此处用了containsValue来判断是否存在该val
                {
                    // 说明val相同key不同
                    return false;
                }
                charMap.put(chS, chT);
            }
        }

        return true;
    }
}
