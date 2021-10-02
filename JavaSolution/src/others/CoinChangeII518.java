package others;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 Note: You can assume that

 0 <= amount <= 5000
 1 <= coin <= 5000
 the number of coins is less than 500
 the answer is guaranteed to fit into signed 32-bit integer


 Example 1:

 Input: amount = 5, coins = [1, 2, 5]
 Output: 4
 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1


 Example 2:

 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.


 Example 3:

 Input: amount = 10, coins = [10]
 Output: 1

 */
public class CoinChangeII518 {
//    public int change(int amount, int[] coins) {
//
//        Set<String> set = new HashSet<>();
//        int res = recursion(set, amount, coins, 0, new ArrayList<>());
//        return res;
//    }

    // using dp (背包问题，need more reading)
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }

    // 不加set来去重的话会出现 1+1+1+2 和 1+1+2+1 这样的重复
    public int recursion(Set<String> set, int amount, int[] coins, int tempSum, List<Integer> tempList)
    {
        if(tempSum == amount)
        {
            List<Integer> newList = new ArrayList<>(tempList);
            Collections.sort(newList);
            String newRes = newList.stream().map(i -> i.toString())
                    .collect(Collectors.joining(","));
            if (set.add(newRes)) {
                //System.out.println(newRes);
                return 1;
            }
            return 0;
        }
        if(tempSum > amount) return 0;

        int cnt = 0;
        for (int i = 0; i < coins.length; i++)
        {
            int coin = coins[i];
            tempSum += coin;
            tempList.add(coin);
            cnt += recursion(set, amount, coins, tempSum, tempList);
            tempSum -= coin;
            tempList.remove(tempList.size()-1);
        }

        return cnt;
    }

}
