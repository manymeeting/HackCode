package dp;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed).
 If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 Note:
 nums.length will be between 1 and 20000.
 nums[i] will be between 1 and 65535.
 k will be between 1 and floor(nums.length / 3).

 */

// sample solution : https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/130666/Java-DP-O(n)-solution.-Explanation-inline.
// 比较难，注意各边界index

public class MaximumSumOf3NonOverlappingSubarrays689 {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if(nums == null || nums.length == 0)
        {
            return null;
        }

        int len = nums.length;

        int[] sum = new int[len + 1]; // otherwise sum[i + 1] will be out of bound
        int[] left = new int[len]; // left[i]: the left index starts from which will achieve max sum
        int[] right = new int[len]; // right[i]: the right index starts from which will achieve max sum

        int[] res = new int[3];
        int max = 0;

        // computing sum
        // sum[i] stores the sum from index 0 to i-1
        for(int i = 0; i < len; i++)
        {
            sum[i+1] = sum[i] + nums[i];
        }

        // go from left to right, record start index that has max sum
        int leftMax = sum[k] - sum[0];
        left[k-1] = 0;
        for (int i = k; i < len; i++)
        {
            if(sum[i+1] - sum[i+1-k] > leftMax)
            {
                left[i] = i-k+1;
                leftMax = sum[i+1] - sum[i+1-k];
            }
            else
            {
                left[i] = left[i - 1];
            }
        }

        // go from right to left, ...
        int rightMax = sum[len] - sum[len - k];
        right[len-k] = len - k;
        for (int i=len-k-1; i>=0; i--) {
            if (sum[i+k] - sum[i] > rightMax) {
                right[i] = i;
                rightMax = sum[i+k] - sum[i];
            } else {
                right[i] = right[i+1];
            }
        }

        // now go through the middle part where k<=i<=n-2k
        for (int i=k; i<=len-2*k; i++) {
            int l = left[i-1];
            int r = right[i+k];
            int total = (sum[l+k] - sum[l]) + (sum[i+k] - sum[i]) + (sum[r+k] - sum[r]);
            if (total > max) {
                max = total;
                res[0] = l;
                res[1] = i;
                res[2] = r;
            }
        }
        return res;


    }

}
