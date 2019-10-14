package dp;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Example 1:

 Input: S = "rabbbit", T = "rabbit"
 Output: 3
 Explanation:

 As shown below, there are 3 ways you can generate "rabbit" from S.
 (The caret symbol ^ means the chosen letters)

 rabbbit
 ^^^^ ^^
 rabbbit
 ^^ ^^^^
 rabbbit
 ^^^ ^^^
 Example 2:

 Input: S = "babgbag", T = "bag"
 Output: 5
 Explanation:

 As shown below, there are 5 ways you can generate "bag" from S.
 (The caret symbol ^ means the chosen letters)

 babgbag
 ^^ ^
 babgbag
 ^^    ^
 babgbag
 ^    ^^
 babgbag
 ^  ^^
 babgbag
 ^^^

 */

/**
 * 思路转换：转化为思考从S变到T，有多少种变化方法

 动态规划，定义dp[i][j]为S的子字符串0...i变换到T的子字符串0...j的变换方法。

 如果S[i]==T[j]，那么dp[i][j] = dp[i-1][j-1] + dp[i-1][j]。意思是：如果当前S[i]==T[j]，那么当前这个字母既可以保留也可以抛弃，
 所以变换方法等于保留这个字母的变换方法加上不用这个字母的变换方法。
 如果S[i]!=T[j]，那么dp[i][j] = dp[i-1][j]，意思是如果当前字符不等，那么就只能抛弃当前这个字符。
 递归公式中用到的dp[0][0] = 1，dp[i][0] = 0（把任意一个字符串变换为一个空串只有一个方法）
 */
public class DistinctSubsequences115 {
    public int numDistinct(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();

        if(lenT > lenS) return 0;

        int[][] dp = new int[lenS][lenT];

        for (int j = 0; j < lenT; j++) {

            for (int i = j; i < lenS; i++) {
                boolean flag = s.charAt(i) == t.charAt(j);
                if(j == 0) { // T的第一个字符，单独处理
                    if(i == 0) { // S的第一个字符
                        dp[i][j] = flag ? 1 : 0; // 把S的第一个字符变成T的第一个字符的方法个数，相等就是1，否则就是0
                    }
                    else {
                        dp[i][j] = flag ? dp[i-1][j] + 1 : dp[i-1][j]; //把S的其余字符变成T的第一个字符的方法个数，如果相等就直接在已有的个数上加1，不相等不加
                    }

                }
                else  { // T的其余字符

                    // 如果当前S[i]==T[j]，那么当前这个字母即可以保留也可以抛弃，
                    // 所以变换方法等于保留这个字母的变换方法加上不用这个字母的变换方法。
                    // 如果S[i]!=T[i]，那么dp[i][j] = dp[i-1][j]，意思是如果当前字符不等，那么就只能抛弃当前这个字符。
                    //
                    // dp[i-1][j-1]: 保留S中该字符，直接用i-1子串变到j-1子串的的方法数，因为这种情况下方法数不会增加
                    // dp[i-1][j]: 放弃S中该字符，用i-1子串变到T当前子串的方法数
                    dp[i][j] = flag ? dp[i-1][j-1] + dp[i-1][j] : dp[i-1][j];
                }
            }
        }

        return dp[lenS-1][lenT-1];
    }
}
