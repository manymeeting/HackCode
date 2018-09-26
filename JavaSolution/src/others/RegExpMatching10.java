package others;

/**
 *
 Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 Example 3:

 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".
 Example 4:

 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 Example 5:

 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false
 */

// dp思路，分以下情况，
//1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
//2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
//3, If p.charAt(j) == '*':
//   here are two sub conditions:
//               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
//               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
//                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
//                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
//                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
// 递归思路相对更直观
public class RegExpMatching10 {

    // 递归解法（复杂度2^n, 因为，
    // 假设P全是a*a*a*这样组成，s = aaaaaaaa 而s的每一个字符都有2种可能：与当前的a*匹配,或者与下一个a*匹配）
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*') {  // second char is '*'
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p);
            }
            return false;
        } else {                                     // second char is not '*'
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
    }

    // DP解法
    /**
    public boolean isMatch(String s, String p) {

        if(s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        // base case
        dp[0][0] = true;

        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '*' && dp[0][i-1])
            {
                dp[0][i+1] = true;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)){
                    dp[i+1][j+1] = dp[i][j];
                }
                if(p.charAt(j) == '*') {
                    if(p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1]; //in this case, a* only counts as empty
                    }
                    else  {
                        dp[i+1][j+1] = (dp[i+1][j] //in this case, a* counts as single a
                                || dp[i][j+1] //in this case, a* counts as multiple a
                                || dp[i+1][j-1]); //in this case, a* counts as empty
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }*/
}
