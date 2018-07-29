package dp;


/**
 *
 * Given a positive integer n, break it into the sum of at least two positive integers
 * and maximize the product of those integers. Return the maximum product you can get.

 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

 Note: You may assume that n is not less than 2 and not larger than 58.


 */

// dp[i] stores the max product from i.
// 把一个数i分为分为 j (j < i) 和 i - j, 每次随着j递增，比较dp[i]和j * (i - j)，一路更新max dp[i]
// 其中，j和i-j要分别和自己的product比较看是否product会比本身更大

// 注意下标起始，给一个初始的dp[1] = 1

public class IntegerBreak343 {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++){

            for (int j = 1; j < i; j++)
            {
                dp[i] = Math.max(dp[i], Math.max(dp[j], j) * Math.max(dp[i- j], i - j));
            }

        }
        return dp[n];
    }
}
