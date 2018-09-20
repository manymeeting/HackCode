package math;

/**
 * Write a program to find the n-th ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example:

 Input: n = 10
 Output: 12
 Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 Note:

 1 is typically treated as an ugly number.
 n does not exceed 1690.
 */

// 规律：every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.
// 采用合并有序数组的思想，从2，3，5三个队列中每次选最小的加入结果数组

public class UglyNumberII264 {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;

        int idx2 = 0, idx3 = 0, idx5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for (int i = 1; i < n; i++) { // 从1开始

            int min = Math.min(factor2, Math.min(factor3, factor5));
            ugly[i] = min;

            if(min == factor2) {
                factor2 = 2 * ugly[++idx2];
            }
            if(min == factor3) {
                factor3 = 3 * ugly[++idx3];
            }
            if(min == factor5) {
                factor5 = 5 * ugly[++idx5];
            }
        }

        return ugly[n-1];
    }
}
