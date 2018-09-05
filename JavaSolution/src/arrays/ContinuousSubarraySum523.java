package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous subarray of size
 * at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 Note:
 The length of the array won't exceed 10,000.
 You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

 */

// 一个presum数组来存到每一的sum，然后两层循环来看presum[j] - presum[i]是不是k的倍数，
// 注意 0 % k 的情况，此时结果是k不是0，要单独提前check
public class ContinuousSubarraySum523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        int[] preSum = new int[nums.length+1];

        for (int i = 1; i <= nums.length; i++)
        {
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i+2; j <= nums.length; j++)
            {
                if(k == 0)
                {
                    if(preSum[j] - preSum[i] == 0) return true;
                }
                else if((preSum[j] - preSum[i] ) % k == 0) return true;
            }
        }
        return false;
    }
}
