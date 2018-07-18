package arrays;

/**
 *
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 prove that at least one duplicate number must exist.
 Assume that there is only one duplicate number, find the duplicate one.

 Example 1:

 Input: [1,3,4,2,2]
 Output: 2
 Example 2:

 Input: [3,1,3,4,2]
 Output: 3
 Note:

 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class FindTheDuplicateNumber287 {
    // 把数字放到对应的index上去，遇到重复的直接return
    public int findDuplicate(int[] nums) {
        int i = 0;

        while(i < nums.length)
        {
            if(i != nums[i] && nums[i] < nums.length)
            {
                int num = nums[i];
                if(nums[num] == num)
                {
                    return num;
                }
                swap(nums, i, nums[i]);
            }
            else
            {
                i++;
            }
        }

        return i;

    }

    public void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
