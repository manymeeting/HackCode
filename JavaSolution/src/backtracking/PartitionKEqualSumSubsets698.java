package backtracking;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.
 */

// backtracking思路，用一个array来记录visited状态，
// 每个subset的targetSum为总sum/k，每次递归更新当前subset的currSum

public class PartitionKEqualSumSubsets698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;

        if(sum == 0 || sum % k != 0) return false;

        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, visited, 0, sum / k, 0, k);
    }

    private boolean canPartition(int[] nums, boolean[] visited,
                                 int currSum, int targetSum, int startIdx, int k) {

        if (currSum > targetSum) return false; // 加速

        // 边界条件
        if(k == 0) return true;

        if (currSum == targetSum) {
            // 找到一组，保留visited的状态（避免set元素重叠），清零当前sum，目标partition数-1，进行下一轮递归
            return canPartition(nums, visited, 0, targetSum,
                    0, k - 1);
        }


        for (int i = startIdx; i < nums.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(canPartition(nums, visited, currSum + nums[i], targetSum, i+1, k)) {
                    return true;
                }
                visited[i] = false;
            }

        }

        return false;
    }


}
