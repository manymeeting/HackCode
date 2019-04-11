package binarysearch;

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

// followup：如果有重复，需要找两边
public class SearchInRotatedSortedArr33 {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int left = 0; int right = nums.length-1;

        while(left <= right) // 不确定是否存在，用<=，每次用mid来比较
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

/**
 * ///////////////////////////////////////////////////////////
 /////33. Search in Rotated Sorted Array
 /////////////////////////////////////////
 ////[4,5,6,7,8,9,10,2,3]
 //O(logn) O(1)
 //如果nums[start] <= nums[mid] 证明左半部分是有序的 那么左边所有数字的范围就确定了
 //如果nums[start] > nums[mid] 证明右半部分是有序的 那么右边所有数字的范围就确定了
 class Solution {
 class Solution {
 public int search(int[] nums, int target) {
 int start = 0;
 int end = nums.length - 1;
 while (start <= end) {
 int mid = start + (end - start) / 2;
 if (nums[mid] == target) {
 return mid;
 }
 if (nums[start] <= nums[mid]) { //这里为什么要取等于 [3,1] 找1
 if (target >= nums[start] && target < nums[mid]) {
 end = mid - 1;
 } else {
 start = mid + 1;
 }
 } else {
 if (target <= nums[end] && target > nums[mid]) {
 start = mid + 1;
 } else {
 end = mid - 1;
 }
 }
 }
 return -1;
 }
 }

 ////有重复
 public class Solution {
 public boolean search(int[] nums, int target) {
 return helper(nums, 0, nums.length - 1, target);
 }

 public boolean helper(int[] nums, int min, int max, int target){
 int mid = min + (max - min) / 2;
 // 不符合min <= max则返回假
 if(min > max){
 return false;
 }
 if(nums[mid] == target){
 return true;
 }
 boolean left = false, right = false;
 // 如果左边是有序的
 if(nums[min] <= nums[mid]){
 // 看目标是否在左边有序数列中
 if(nums[min] <= target && target < nums[mid]){
 left = helper(nums, min, mid - 1, target);
 } else {
 left = helper(nums, mid + 1, max, target);
 }
 }
 // 如果右边也是有序的
 if(nums[mid] <= nums[max]){
 // 看目标是否在右边有序数列中
 if(nums[mid] < target && target <= nums[max]){
 right = helper(nums, mid + 1, max, target);
 } else {
 right = helper(nums, min, mid - 1, target);
 }
 }
 // 左右两边有一个包含目标就返回真
 return left || right;
 }
 }
 */