package dp;

import java.util.Arrays;

/**
 *
 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 Example 1:

 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.
 Example 2:

 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.

 */

// DP思路：一个数x可分解为一个任意数a加上一个平方数b * b，即x = a + b * b。
// 因此，能组成目标整数x最少的平方数个数，就是能组成a最少的平方数个数加上1。
// dp[0] = 0, dp[i]=min(dp[i],dp[i − j * j]+1)

public class PerfectSquares279 {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];

    }
}
