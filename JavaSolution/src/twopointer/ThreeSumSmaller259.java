package twopointer;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

 Example:

 Input: nums = [-2,0,1,3], and target = 2
 Output: 2
 Explanation: Because there are two triplets which sums are less than 2:
 [-2,0,1]
 [-2,0,3]
 Follow up: Could you solve it in O(n2) runtime?
 */

// 先sort，然后从左端开始遍历，对每一个i，取left为i+1,right为最右端，如果nums[i]+左值+右值 < target，则说明left到right之间的组合都满足条件

public class ThreeSumSmaller259 {
    public int threeSumSmaller(int[] nums, int target) {

        int count = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1; int right = nums.length - 1;
            while (left < right) {
                if(nums[i] + nums[left] + nums[right] < target) {
                    count += right - left; // 只要i不变，right取从left到right之间的值时都满足<target
                    left++; // 向前移动left
                }
                else {
                    right--;
                }
            }
        }

        return count;
    }
}
