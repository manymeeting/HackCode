package dp;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Range Sum Query 2D
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 Note:
 You may assume that the matrix does not change.
 There are many calls to sumRegion function.
 You may assume that row1 ≤ row2 and col1 ≤ col2.
 */


// 1.构建一个dp矩阵，dp[i+1][j+1]表示从原点到[i][j]的sum，画图可以看出，dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
// 2.直接返回dp中各数的计算结果 sum(r1c1, r2c2) = dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1];
// 注意dp中index都大1是为了方便处理边界情况，构建dp的时候loop的下标也要从1开始

public class RangeSumQuery2DImmut304 {
    class NumMatrix {

        int[][] dp;
        public NumMatrix(int[][] matrix) {
            // 构建dp矩阵
            if(matrix == null || matrix.length == 0) return;
            int m = matrix.length, n = matrix[0].length;
            dp = new int[m+1][n+1];
            for (int i = 1; i <= m; i++) { // 注意下标从1开始
                for (int j = 1; j <=n; j++) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1];
        }
    }
}
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */