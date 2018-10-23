package arrays;

/**
 * A peak element is an element that is greater than its neighbors.

 Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that nums[-1] = nums[n] = -∞.

 Example 1:

 Input: nums = [1,2,3,1]
 Output: 2
 Explanation: 3 is a peak element and your function should return the index number 2.
 Example 2:

 Input: nums = [1,2,1,3,5,6,4]
 Output: 1 or 5
 Explanation: Your function can return either index number 1 where the peak element is 2,
 or index number 5 where the peak element is 6.
 Note:

 Your solution should be in logarithmic complexity.

 */


// 二分查找，用两个mid，比较mid1和mid2位置元素的大小关系，m1<m2则将m2设为左端，反之则将m1设为右端，
// 最后m1就是一个valid的peak位置
    
public class FindPeakElement162 {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while(left < right) {
            int mid1 = left + (right - left) / 2;
            int mid2 = mid1 + 1;
            if(nums[mid1] < nums[mid2]) {
                left = mid2;
            }
            else {
                right = mid1;
            }
        }

        return left;
    }
}
