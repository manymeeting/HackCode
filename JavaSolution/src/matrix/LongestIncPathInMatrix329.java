package matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer matrix, find the length of the longest increasing path.

 From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

 Example 1:

 Input: nums =
 [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 Output: 4
 Explanation: The longest increasing path is [1, 2, 6, 9].
 Example 2:

 Input: nums =
 [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 Output: 4
 Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

 */

// 用dfs+cache来处理，注意dfs的写法，通过1+helper(...) 来递增len

public class LongestIncPathInMatrix329 {
    private int[][] cache;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0; // matrix一定要先check第一维是否为空

        // cache每一个位置存的是从该位置出发最长的递增序列len
        cache = new int[matrix.length][matrix[0].length];

        int maxPathLen = 0;
        for (int i = 0; i < matrix.length; i++ ) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxPathLen = Math.max(maxPathLen, helper(matrix, -1, i, j));
            }
        }

        return maxPathLen;
    }


    private int helper(int[][] matrix, int prev, int i, int j) {
        if(i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }
        int curr = matrix[i][j];
        if(curr <= prev) return 0;

        if (cache[i][j] != 0) return cache[i][j];

        int a = 1 + helper(matrix, curr, i, j + 1);
        int b = 1 + helper(matrix, curr, i, j - 1);
        int c = 1 + helper(matrix, curr, i - 1, j);
        int d = 1 + helper(matrix, curr, i + 1, j);

        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        cache[i][j] = max;

        return max;
    }

}
