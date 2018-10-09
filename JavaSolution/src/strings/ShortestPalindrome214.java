package strings;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 Example 1:

 Input: "aacecaaa"
 Output: "aaacecaaa"
 Example 2:

 Input: "abcd"
 Output: "dcbabcd"

 */

// 解释：注意for循环，i会走完一直到0，所以j至少会+1，
// 然后s.substring(0, j)会包括目前最长的回文，所以对这一部分进行递归，得到中间这部分最短的回文，
// 最后再加上reverse过的后半部分和后半部分，就得到目标结果

public class ShortestPalindrome214 {
    public String shortestPalindrome(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }
}
