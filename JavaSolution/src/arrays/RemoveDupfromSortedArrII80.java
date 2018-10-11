package arrays;


/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example 1:

 Given nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

 It doesn't matter what you leave beyond the returned length.
 Example 2:

 Given nums = [0,0,1,1,1,1,2,3,3],

 Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

 It doesn't matter what values are set beyond the returned length.
 Clarification:

 Confused why the returned value is an integer but your answer is an array?

 Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

 Internally you can think of this:

 // nums is passed in by reference. (i.e., without making a copy)
 int len = removeDuplicates(nums);

 // any modification to nums in your function would be known by the caller.
 // using the length returned by your function, it prints the first len elements.
 for (int i = 0; i < len; i++) {
 print(nums[i]);
 }

 */

// 一个通用的解法，如果最多可以重复k次，则用两个pointer，初始从k开始，
// 每次移动i，遇到nums[j - k] != nums[i]时，用i位的元素覆盖j位的，
// 效果是当超过k个重复时，j会停在第k个重复的位置，然后下一轮会被i（j+1）位置的元素覆盖，
// 循环结束后返回j就是最终目标长度

public class RemoveDupfromSortedArrII80 {
    public int removeDuplicates(int[] nums) {
        int k = 2; // 可以重复的次数
        if(nums.length < k) return nums.length;

        int i = k, j = k;
        for (; i < nums.length; i++) {
            if(nums[j - k] != nums[i]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;

    }
}
