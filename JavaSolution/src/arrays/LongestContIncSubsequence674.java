package arrays;

/**
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

 Example 1:
 Input: [1,3,5,4,7]
 Output: 3
 Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 Even though [1,3,5,7] is also an increasing subsequence,
 it's not a continuous one where 5 and 7 are separated by 4.
 Example 2:
 Input: [2,2,2,2,2]
 Output: 1
 Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 Note: Length of the array will not exceed 10,000.
 */

// cnt，res都从1开始，遇到连续递增的取++cnt和max的最大值，否则cnt=1

public class LongestContIncSubsequence674 {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length <= 1) return nums.length;

        int res = 1, cnt = 1;
        for (int i = 1; i < nums.length; i++)
        {
            if(nums[i] > nums[i-1])
            {
                res = Math.max(res, ++cnt);
            }
            else
            {
                cnt = 1;
            }
        }

        return res;
    }
}
