package math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.

 The 'closest' is defined as absolute difference minimized between two integers.

 Example 1:
 Input: "123"
 Output: "121"
 Note:
 The input n is a positive integer represented by string, whose length will not exceed 18.
 If there is a tie, return the smaller one as answer.
 */

// 一共有五种可能性，算出来每一种然后取diff最小的
// 解释：
//
//1：比n多1位的数，例如对于99而言，距离它最近的回文数就是101；
//
//2：比n少1位的数，例如对于101， 距离它最近的回文数就是99；
//
//3：和n位数一样，并且其后一半是n的前一半的翻转的数。这样的数又可以分为3种：
//  1）中间数和n相同的，例如123的最近回文数就是121；
//  2）中间数比n大1，例如189的最近回文数就是191；
//  3）中间数比n小1，例如920的最近回文数就是919。


public class FindClosestPalindrome564 {
    public String nearestPalindromic(String n) {
       int len = n.length();
       boolean isEvenLen = len % 2 == 0;
       int leftEndIdx = isEvenLen ? len / 2 - 1 : len / 2;
       long left = Long.parseLong(n.substring(0, leftEndIdx+1));

       // 举例：12345
       List<Long> candidates = new ArrayList<>();
       candidates.add(getPalindromeByLeft(left, isEvenLen)); // 12321
       candidates.add(getPalindromeByLeft(left + 1, isEvenLen)); // 12421
       candidates.add(getPalindromeByLeft(left - 1, isEvenLen)); // 12221
       candidates.add((long)Math.pow(10, len - 1) - 1); // 9999
       candidates.add((long)Math.pow(10, len) + 1); // 100001

       long minDiff = Long.MAX_VALUE;
       long nLong = Long.parseLong(n);
       long res = 0;
       for (long candi : candidates) {
           if(candi == nLong) continue; // Target must be different
           long candiDiff = Math.abs(candi - nLong);
           if(candiDiff < minDiff) {
               minDiff = candiDiff;
               res = candi;
           }
           else if(candiDiff == minDiff) {
               // A tie, return the smaller one
               res = Math.min(candi, res);
           }
       }

       return String.valueOf(res);

    }


    // Left: 123, isEvenLen: true -> 12321
    private long getPalindromeByLeft(long left, boolean isEvenLen) {
        long res = left;

        if(!isEvenLen) left = left / 10;
        while(left > 0) {
            res = res * 10;
            res += left % 10;
            left = left / 10;
        }

        return res;
    }
}
