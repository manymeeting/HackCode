package binarysearch;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Output: true
 Example 2:

 Input: 14
 Output: false
 */

// 用二分法尝试，low从4，high为num / 2，注意使用long类型

public class ValidPerfectSquare367 {
    public boolean isPerfectSquare(int num) {
        if(num == 1) return true; // 1也是perfect num
        if(num < 4) return false;
        long low = 2; // 注意要用long，防止int越界
        long high = num / 2;

        while(low < high) {
            long mid = low + (high - low) / 2;
            if(mid * mid < num) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }

        return low * low == num;
    }


}
