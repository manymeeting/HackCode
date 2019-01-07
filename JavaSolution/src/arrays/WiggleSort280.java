package arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

 Example:

 Input: nums = [3,5,2,1,6,4]
 Output: One possible answer is [3,5,1,6,2,4]
 */

// 根据题意可以总结出：当i为奇数时，nums[i] >= nums[i - 1]，当i为偶数时，nums[i] <= nums[i - 1]，
// 所以只要对每个数字，根据其奇偶性，跟其对应的条件比较，如果不符合就和前面的数交换位置即可

public class WiggleSort280 {
    public void wiggleSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            if((i % 2 == 0 && nums[i] > nums[i-1]) || (i % 2 == 1 && nums[i] < nums[i-1])) {
                swap(nums, i, i-1);
            }
        }

    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
