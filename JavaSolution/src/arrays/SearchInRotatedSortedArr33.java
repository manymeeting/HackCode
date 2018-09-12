package arrays;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.

 Your algorithm's runtime complexity must be in the order of O(log n).

 Example 1:

 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4
 Example 2:

 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1
 */

// 写出几个例子，会看出规律：如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的，
// 因此只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了

public class SearchInRotatedSortedArr33 {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int left = 0; int right = nums.length-1;

        while(left <= right)
        {
            int mid = (right + left) / 2;

            if(nums[mid] == target) return mid;
            if(nums[mid] < nums[right])
            {
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;

            }
            else
            {
                if(nums[left] <= target && nums[mid] > target) right = mid;
                else left = mid + 1;
            }
        }

        return -1;
    }
}
