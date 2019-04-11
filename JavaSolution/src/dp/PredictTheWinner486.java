package dp;

import java.util.*;
/**

Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

*/

// DP二维数组解法：dp[i,j]=Max( nums[i]−dp[i+1][j], nums[j]−dp[i][j−1] )
// 推导思路：可以看出给定一个区间[l, r]，目标值只会取决于子区间[l+1, r]和[l, r-1]的目标值
class PredictTheWinner486 {
	public boolean PredictTheWinner(int[] nums) {
        
        // 长度为int[nums.length+1][nums.length]，因为下面的循环中，r最大能取到nums最后一位，而同时前面会访问dp[l+1]，所以表示left的部分要多留一位防止越界
        int[][] dp = new int[nums.length][nums.length]; 

        for (int l = nums.length; l >= 0; l--) {
        	for (int r = l+1; r < nums.length; r++) {
        		int left = nums[l] - dp[l+1][r];
        		int right = nums[r] - dp[l][r-1];
        		dp[l][r] = Math.max(left, right);
        	}
        	
        }

        return dp[0][nums.length-1] >= 0;
    }

}


// 递归解法：每次取left和right，分别减去下一轮递归的返回值，然后返回大的值 （因为assume each player plays to maximize his score.），看最后是否>=0，是则说明先pick的人一定会赢

// class PredictTheWinner486 {
// 	public boolean PredictTheWinner(int[] nums) {
//         return pick(nums, 0, nums.length-1) >= 0;
//     }

//     private int pick(int[] nums, int i, int j) {
//     	if(i == j) return nums[i];
//     	int left = nums[i] - pick(nums, i + 1, j);
//     	int right = nums[j] - pick(nums, i, j - 1);

//     	return Math.max(left, right);
//     }
// }