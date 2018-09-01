package matrix;

/**
 * Given a non-empty 2D array grid of 0's and 1's,
 * an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.

 Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

 Example 1:
 [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 Example 2:
 [[0,0,0,0,0,0,0,0]]
 Given the above grid, return 0.
 Note: The length of each dimension in the given grid does not exceed 50.
 */

// 遍历matrix，遇到1的时候进入dfs，更新max值
public class MaxAreaOfIsland695 {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        if(grid.length == 0) return 0;
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] == 1)
                {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int i, int j)
    {
        if(i >= grid.length || j >= grid[0].length || i < 0 || j < 0)
        {
            return 0;
        }
        if(grid[i][j] == 0) return 0;

        grid[i][j] = 0;
        return 1 + dfs(grid, i+1, j) + dfs(grid, i, j-1) + dfs(grid, i, j+1) + dfs(grid, i-1, j);
    }

}
