package arrays;

import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].

 Example:

 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
 Could you solve it with constant space complexity? (The output array does not count as extra space
 for the purpose of space complexity analysis.)

 */

// 解法一，两个数组：对于某一个数字，如果我们知道其前面所有数字的乘积，同时也知道后面所有的数乘积，那么二者相乘就是我们要的结果

// 解法二，用一个数组：对上面的方法进行空间上的优化，由于最终的结果都是要乘到结果res中，所以我们可以不用单独的数组来保存乘积，而是直接累积到res中，
// 我们先从前面遍历一遍，将乘积的累积存入res中，然后从后面开始遍历，用到一个临时变量right，初始化为1，然后每次不断累积，最终得到正确结果

// 解释：http://www.cnblogs.com/grandyang/p/4650187.html
public class ProductofArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;

        for (int i = 1; i < len; i++)
        {
            res[i] = res[i-1] * nums[i - 1];
        }
        int right = 1;

        for (int i = len - 1; i >= 0; i--)
        {
            res[i] *= right;
            right *= nums[i];
        }

        return res;
    }

}
