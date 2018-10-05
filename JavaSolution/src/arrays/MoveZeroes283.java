package arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:

 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]
 Note:

 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 */

// 维护一个insertposition，遇到非0元素就把pos+1，一遍loop过后，insertposition之后全设为0即可
public class MoveZeroes283 {
    public void moveZeroes(int[] nums) {
        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }

        while(insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }
}
