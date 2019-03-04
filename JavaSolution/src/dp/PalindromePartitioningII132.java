package dp;

/**
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 Example:

 Input: "aab"
 Output: 1
 Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

 */

// 先构建一个二维数组来存从i到j能否形成一个palindrome，
// 然后two pointer遍历string，初始化当前位置的最小cut数为当前遍历到的长度（每个字母都cut），
// 如果在dp里找到能构成palin的就缩小当前cut数，最后末尾的cut值就是结果

public class PalindromePartitioningII132 {
    public int minCut(String s) {

        if(s == null || s.length() == 0) return 0;

        int n = s.length();

        // build the dp matrix to hold the palindrome information
        // dp[i][j] represents whether s[i] to s[j] can form a palindrome
        boolean[][] dp = buildPalindromeMatrix(s);

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            // by default we need j cut from s[0] to s[i]
            int cut = i;

            for (int j = 0; j <= i; j++) {
                if(dp[j][i]) {
                    // s[j] to s[i] is a palindrome
                    // try to update the cut with res[j - 1]
                    cut = Math.min(cut, j == 0 ? 0 : res[j-1] + 1);
                }
            }

            res[i] = cut;
        }

        return res[n-1];
    }


    // dp[i][j] 表示从s[i]到s[j]是否能形成palindrome
    private boolean[][] buildPalindromeMatrix(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >=0; i--) {
            for (int j = i; j < len; j++) {
                if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp;
    }
}
