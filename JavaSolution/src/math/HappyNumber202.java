package math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example:

 Input: 19
 Output: true
 Explanation:
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1

 */

// 理论背景：a loop must exist if not happy，
// 因此写一个do while循环，用set来存已有的sum，sum为1时返回true，否则遇到重复的话为false

public class HappyNumber202 {

    public boolean isHappy(int n) {
        int digitsSum = n;
        Set<Integer> sumSet = new HashSet<>();
        do {
            String nStr = String.valueOf(digitsSum);
            digitsSum = 0;
            for (char ch: nStr.toCharArray())
            {
                digitsSum += (ch - '0') * (ch - '0');
            }

            if(digitsSum == 1) return true;
        }
        while (sumSet.add(digitsSum));

        return false;
    }
}
