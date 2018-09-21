package dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 Now consider if some obstacles are added to the grids. How many unique paths would there be?


 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Note: m and n will be at most 100.

 Example 1:

 Input:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 Output: 2
 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right*/

// 注意第一行和第一列，一旦遇到有1，后续全初始化为0

public class UniquePathsII63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] resMatrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            if(obstacleGrid[i][0] != 1) resMatrix[i][0] = 1;
            else break;
        }
        for (int j = 0; j < col; j++) {
            if(obstacleGrid[0][j] != 1) resMatrix[0][j] = 1;
            else break;
        }

        for (int i = 1; i < row; i++)
        {
            for (int j = 1; j < col; j++) {
                if(obstacleGrid[i][j] == 1) {
                    resMatrix[i][j] = 0;
                }
                else {
                    resMatrix[i][j] = resMatrix[i-1][j] + resMatrix[i][j-1];
                }
            }
        }

        return resMatrix[row-1][col-1];

    }
}
