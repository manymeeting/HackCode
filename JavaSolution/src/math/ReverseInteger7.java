package math;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.

 Example 1:

 Input: 123
 Output: 321
 Example 2:

 Input: -123
 Output: -321
 Example 3:

 Input: 120
 Output: 21
 Note:
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.


 */

// 初始化res为long类型，res = res * 10 + x % 10; x = x / 10;
    
public class ReverseInteger7 {
    public int reverse(int x) {

        long res = 0;
        while(x != 0){
            res = res * 10 + x % 10;
            if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
            x = x / 10;
        }

        return (int) res;

    }
}
