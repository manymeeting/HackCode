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

// 解释：基本上采用暴力解法，i和j从两边开始对比ch，再维护一个end位置，从末尾开始，遇到不一样的就向前挪一位，
// 最后end的位置就是当前str中包含的最大的回文末尾（因为只能在前面加，所以这个回文一定是从0位置开始），
// 然后把后面部分reverse一下加到前面即可

public class ShortestPalindrome214 {
    public String shortestPalindrome(String s) {
        int i = 0;
        int end = s.toCharArray().length - 1;

        // end is the end index of the longest existing palindrome.
        int j = end;

        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i ++;
                j --;
            } else {
                i = 0;
                end --;
                j = end;
            }
        }
        StringBuilder temp = new StringBuilder(s.substring(end + 1));
        String res = temp.reverse().toString() + s;
        return res;
    }
}
