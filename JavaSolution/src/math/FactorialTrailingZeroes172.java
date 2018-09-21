package math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.

 Example 1:

 Input: 3
 Output: 0
 Explanation: 3! = 6, no trailing zero.
 Example 2:

 Input: 5
 Output: 1
 Explanation: 5! = 120, one trailing zero.
 Note: Your solution should be in logarithmic time complexity.


 */

// 由于每次乘10就会多一个0，所以要看乘数里有几个10，转化为看有几个2*5，由于2比5多，只看有几个5即可，
// 也要考虑25，125... 所以res = n/5 + n/25 + ....
    
public class FactorialTrailingZeroes172 {

    public int trailingZeroes(int n) {
        int res = 0;
        while(n > 0) {
            res += n / 5;
            n = n / 5;
        }
        return res;
    }
}
