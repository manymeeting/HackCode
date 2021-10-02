package twopointer;

/**
 * Given an integer array nums and two integers left and right, return the
 * number of contiguous non-empty subarrays such that the value of the maximum
 * array element in that subarray is in the range [left, right].
 * 
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,1,4,3], left = 2, right = 3 Output: 3 Explanation: There are
 * three subarrays that meet the requirements: [2], [2, 1], [3]. Example 2:
 * 
 * Input: nums = [2,9,2,5,6], left = 2, right = 8 Output: 7
 * 
 */

// 转换成从1到left和从1到right的满足条件的subarry数量之差，然后就是subarray叠加的计算方式，
// pointer移动一位相当于给之前所有的subarray都创建了一个新的subarray
public class NumOfSubarrWBoundedMax795 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }
    int count(int[] nums, int bound) {
        int ans = 0, cnt = 0;
        for (int x : nums) {
            cnt = x <= bound ? cnt + 1 : 0;
            ans += cnt;
        }
        return ans;
    }
}
