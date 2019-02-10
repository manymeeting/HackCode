package dp;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

 Example 1:
 Input:
 A: [1,2,3,2,1]
 B: [3,2,1,4,7]
 Output: 3
 Explanation:
 The repeated subarray with maximum length is [3, 2, 1].
 Note:
 1 <= len(A), len(B) <= 1000
 0 <= A[i], B[i] < 100
 */

// dp思路：用dp[i][j]表示从A的i位和B的j位开始的最长的sub array，则一旦A[i] == B[j]，就有dp[i][j] = dp[i+1][j+1] + 1

public class MaxLenOfRepeatedSubarr718 {
    public int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] mem = new int[m+1][n+1]; // 从尾部开始，因此多留一个space
        int res = Integer.MIN_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if(A[i] == B[j]) {
                    mem[i][j] = mem[i+1][j+1] + 1;
                    res = Math.max(res, mem[i][j]);
                }
            }
        }

        return res == Integer.MIN_VALUE ? 0 : res;
    }
}
