package strings;

import java.util.Arrays;

/**Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:

 Input: s = "anagram", t = "nagaram"
 Output: true
 Example 2:

 Input: s = "rat", t = "car"
 Output: false
 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?

 * */

// int[26]存字符频率，直接比较即可
public class ValidAnagram242 {

    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length())
        {
            return false;
        }

        int[] chArrS = new int[26];
        int[] chArrT = new int[26];

        for (char ch : s.toCharArray())
        {
            chArrS[ch-'a']++;
        }

        for (char ch : t.toCharArray())
        {
            chArrT[ch-'a']++;
        }

        if(Arrays.equals(chArrS, chArrT))
        {
            return true;
        }

        return false;

    }
}
