package twopointer;

/**
 Your are given an array of positive integers nums.

 Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

 Example 1:
 Input: nums = [10, 5, 2, 6], k = 100
 Output: 8
 Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 Note:

 0 < nums.length <= 50000.
 0 < nums[i] < 1000.
 0 <= k < 10^6.
 */


// 用两个pointer来构造滑动窗口，每次更新product乘上当前数，如果大于k，就从左边开始除，
// 遇到满足的情况，总数就要加上j - i + 1（从i到j所有subArray的个数，举例如下）
// window: [2,5], 加了一个6，则会多出来以下的subArray
//        (6)
//     (2, 6)
//  (5, 2, 6)

public class SubarrProductLessK713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int prod = 1;
        int i = 0; int j = 0;
        int res = 0;

        for (; j < nums.length; j++) {
            prod = prod * nums[j];
            while(prod >= k && i <= j) {
                prod = prod / nums[i];
                i++;
            }

            res += j - i + 1;

        }

        return res;
    }
}
