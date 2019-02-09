package bitmanipulation;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

 Example:

 Input:  [1,2,1,3,2,5]
 Output: [3,5]
 Note:

 The order of the result is not important. So in the above example, [5, 3] is also correct.
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

 */

// 走两遍。第一遍还是全计算xor，得到的是两个目标数字的xor结果，然后在这个结果数字中一定存在一个为1的bit（因为两数不同），
// 任意找一个（可以从左到右）为1的bit然后做一个除了改位以外全是0的mask，开始第二遍。
// 根据mask & num 是否为0 将原来的数组分组，每组就成了Single number I 的情况

public class SingleNumberIII260 {
    public int[] singleNumber(int[] nums) {

        int diff = 0;
        // 1st Pass, get the XOR of the two target numbers
        for(int num : nums) {
            diff ^= num;
        }

        // Find not 0 bit in the diff
        int mask = 1;
        for (int i = 0; i < Integer.SIZE; i++) {
            mask = mask << i;
            if((mask & diff) != 0) {
                break;
            }
        }

        // 2nd Pass, separate two numbers using mask
        int[] res = new int[2];
        for (int num : nums) {
            if((num & mask) == 0) {
                res[0] ^= num;
            }
            else {
                res[1] ^= num;
            }
        }

        return res;
    }
}
