package bitmanipulation;

/**
 *
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

 Note:

 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Example 1:

 Input: [2,2,3,2]
 Output: 3
 Example 2:

 Input: [0,1,0,1,0,1,99]
 Output: 99
 */

// 用一个32位的数组来记录nums里，所有数字中所有bit下1出现的次数，
// 由于都是3个一组，所以逢3清零，最后剩下的1就构成了多余的数


public class SingleNumberII137 {
    public int singleNumber(int[] nums) {
        int intSize = Integer.SIZE;
        int[] count = new int[intSize];

        for (int i = 0; i < nums.length; i++) {
            // 统计bit中1出现的次数
            for (int j = 0; j< intSize; j++) {
                count[j] += (nums[i] >> j) & 1;
                count[j] %= 3; // 到达3就清零（根据题意都是恰好3次）
            }
        }


        int res = 0;
        for (int i = 0; i < intSize; i++) {
            res += (count[i] << i); // 剩下的都是1，左移后可按照二进制对应的十进制数值计算
        }

        return res;
    }
}
