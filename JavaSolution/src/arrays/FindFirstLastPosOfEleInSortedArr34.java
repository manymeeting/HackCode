package arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 Example 1:

 Input: nums = [5,7,7,8,8,10], target = 8
 Output: [3,4]
 Example 2:

 Input: nums = [5,7,7,8,8,10], target = 6
 Output: [-1,-1]

 */

// 用两次binary search，注意第二次的index操作，mid = i + (j - i)/2 + 1（index不会越界，只是在2选1中偏向右边），同时与target比较时用<=
// 技巧：二分查找中，用mid = i + (j - i) / 2;而不是 ( i + j ) / 2，可以防止int越界
public class FindFirstLastPosOfEleInSortedArr34 {
    public int[] searchRange(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int[] res = new int[]{-1,-1};
        if(nums.length == 0) return res;

        while(i  < j)
        {
            int mid = i + (j - i) / 2;
            if(nums[mid] < target) i = mid + 1;
            else j = mid;

        }
        if(nums[i]!=target) return res; // 如果没找到target，直接返回
        res[0] = i;

        j = nums.length - 1;
        while(i < j) {
            int mid = i + (j - i)/2 + 1;
            if (nums[mid] <= target) i = mid;
            else j = mid - 1;
        }

        res[1] = j;
        return res;

    }

}
