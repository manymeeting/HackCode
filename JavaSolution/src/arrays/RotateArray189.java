package arrays;


/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.

 Example 1:

 Input: [1,2,3,4,5,6,7] and k = 3
 Output: [5,6,7,1,2,3,4]
 Explanation:
 rotate 1 steps to the right: [7,1,2,3,4,5,6]
 rotate 2 steps to the right: [6,7,1,2,3,4,5]
 rotate 3 steps to the right: [5,6,7,1,2,3,4]
 Example 2:

 Input: [-1,-100,3,99] and k = 2
 Output: [3,99,-1,-100]
 Explanation:
 rotate 1 steps to the right: [99,-1,-100,3]
 rotate 2 steps to the right: [3,99,-1,-100]
 Note:

 Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 Could you do it in-place with O(1) extra space?
 */

// 写一个shift函数每次调用即可，要注意不是swap
public class RotateArray189 {
    public void rotate(int[] nums, int k) {

        for (int j = 0; j < k; j++)
        {
            shiftRight(nums);
        }
    }

    private void shiftRight(int[] nums)
    {
        int last = nums[nums.length-1];
        int before = nums[0];
        int curr = 0;
        for (int i = 1; i < nums.length; i++)
        {
            curr = nums[i];
            nums[i] = before;
            before = curr;
        }
        nums[0] = last;
    }


}

