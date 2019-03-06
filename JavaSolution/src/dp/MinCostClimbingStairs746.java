package dp;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

 Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

 Example 1:
 Input: cost = [10, 15, 20]
 Output: 15
 Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 Example 2:
 Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 Output: 6
 Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 Note:
 cost will have a length in the range [2, 1000].
 Every cost[i] will be an integer in the range [0, 999].

 * */

// 可以用一维dp，但其实可以优化成只保留两个变量，
// 如果是一维dp，则loop到size+1次，然后直接返回末尾值，用两个变量的话最后需要再比较一次

public class MinCostClimbingStairs746 {
    public int minCostClimbingStairs(int[] cost) {

        int twoStepBefore = cost[0];
        int oneStepBefore = cost[1];

        for (int i = 2; i < cost.length; i++) {
            int curr = cost[i] + Math.min(oneStepBefore, twoStepBefore);
            twoStepBefore= oneStepBefore;
            oneStepBefore = curr;

        }
        return Math.min(oneStepBefore, twoStepBefore); // 到最后时还可以在最后两个sum中选择少的
    }
}
