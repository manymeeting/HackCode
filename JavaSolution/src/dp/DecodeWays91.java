package dp;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 */


// 递归：注意处理0和边界的问题，除此之外根据当前位字符和下一位字符的情况累加可能的数量

public class DecodeWays91 {

    public int numDecodings(String s) {
        // 处理0的问题
        if(s.equals("") || s.charAt(0) == '0') return 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == '0' && s.charAt(i+1) == '0') return 0; // 连续两个0，无解

        }
        return recursive(s, 0);
    }

    private int recursive(String s, int curr) {
        if(curr == s.length()) return 1;

        char ch = s.charAt(curr);

        if(ch == '0') return 0; // 以0开头的数字不能map到字母
        if(curr == s.length() - 1) return 1; // 不能跟前面的curr == s.length()条件合并，因为要先check是不是0

        char chNext = s.charAt(curr+1);

        if(ch == '1' || (ch == '2' && chNext <= '6')) {
            return (recursive(s, curr+1) + recursive(s, curr+2));
        }

        // 其它情况，说明该位置没有多种可能，直接看下一个
        return recursive(s, curr+1);


    }
}
