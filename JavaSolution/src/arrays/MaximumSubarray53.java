package arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 */

// O（n)解法: 用一个currSum[i]记录到i位的最大连续subarr的sum，然后可以推导出
// currSum[i] = nums[i] + (currSum[i-1] > 0 ? currSum[i-1] : 0)，
// 如果前面sum小于零就从i开始，否则就接着往上加

public class MaximumSubarray53 {

    public int maxSubArray(int[] nums) {
        int[] currSum = new int[nums.length]; // currSum[i] means the maximum subarray sum ending with A[i];

        currSum[0] = nums[0];
        int maxSum = currSum[0];

        for (int i = 1; i < nums.length; i++) {
            currSum[i] = nums[i] + (currSum[i-1] > 0 ? currSum[i-1] : 0);
            maxSum = Math.max(maxSum, currSum[i]);
        }

        return maxSum;
    }

}
