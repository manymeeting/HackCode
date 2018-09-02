package strings;


/**
 * Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings
 even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 Note:
 The input string length won't exceed 1000.

 */

// 对每一位，看两种情况，奇数长或偶数长，写一个函数用while来一边扩展一边check，满足条件时类变量count++
public class PalindromicSubstrings647 {

    int count = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++)
        {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1);
        }

        return this.count;
    }

    private void extendPalindrome(String s, int start, int end)
    {

        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end))
        {
            this.count++;
            start--;
            end++;
        }
    }

}
