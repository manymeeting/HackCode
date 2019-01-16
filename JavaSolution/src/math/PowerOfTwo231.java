package math;

/**
 * Given an integer, write a function to determine if it is a power of two.

 Example 1:

 Input: 1
 Output: true
 Explanation: 20 = 1
 Example 2:

 Input: 16
 Output: true
 Explanation: 24 = 16
 Example 3:

 Input: 218
 Output: false
 */


// 用一个BitCount的技巧，如果是2的power则bit中只有一个1，改操作可在常数时间内完成
public class PowerOfTwo231 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
