package math;

/**
 * Implement int sqrt(int x).

 Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

 Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

 Example 1:

 Input: 4
 Output: 2
 Example 2:

 Input: 8
 Output: 2
 Explanation: The square root of 8 is 2.82842..., and since
 the decimal part is truncated, 2 is returned.
 */

// 用二分法来查找，找到 mid <= x / mid 并且 mid + 1 > x / ( mid + 1 )的mid，就是最终结果

public class SqrtX69 {
    public int mySqrt(int x) {

        if(x == 0) return 0; // 注意特殊情况判断

        int left = 1;
        int right = Integer.MAX_VALUE;

        int mid = 0;
        while(left < right) {
            mid = left + (right - left) / 2;
            if(mid > x / mid) { // 不能用mid * mid > x来判断，会超过int上限
                right = mid;
            }
            else {
                if( mid + 1 > x / ( mid + 1 ) ) { // 同上，要用除法
                    return mid;
                }
                left = mid + 1;
            }
        }

        return mid;
    }
}
