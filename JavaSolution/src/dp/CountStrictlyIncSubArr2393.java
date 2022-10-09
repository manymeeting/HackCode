package dp;

import java.util.Arrays;

/**
 * 
 * You are given an array nums consisting of positive integers.

Return the number of subarrays of nums that are in strictly increasing order.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [1,3,5,4,4,6]
Output: 10
Explanation: The strictly increasing subarrays are the following:
- Subarrays of length 1: [1], [3], [5], [4], [4], [6].
- Subarrays of length 2: [1,3], [3,5], [4,6].
- Subarrays of length 3: [1,3,5].
The total number of subarrays is 6 + 3 + 1 = 10.
Example 2:

Input: nums = [1,2,3,4,5]
Output: 15
Explanation: Every subarray is strictly increasing. There are 15 possible subarrays that we can take.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106

思路：一个简单的1维dp求和。
 */
public class CountStrictlyIncSubArr2393 {
    
    public long countSubarrays(int[] nums) {
        
        long[] dp = new long[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1;
            }
            else {
                dp[i] = 1;
            }
        }

        return Arrays.stream(dp).sum();
    }
}
