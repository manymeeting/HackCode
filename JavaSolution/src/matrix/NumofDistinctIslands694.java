package matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

 Example 1:
 11000
 11000
 00011
 00011
 Given the above grid map, return 1.
 Example 2:
 11011
 10000
 00001
 11011
 Given the above grid map, return 3.

 Notice that:
 11
 1
 and
 1
 11
 are considered different island shapes, because we do not consider reflection / rotation.
 Note: The length of each dimension in the given grid does not exceed 50.
 */

// 用dfs来遍历，每次调用递归前用sb.append记录当前的方向，同时用一个set来存放所有已知的形状
public class NumofDistinctIslands694 {
    private boolean visited[][];
    public int numDistinctIslands(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        Set<String> distinctIslands = new HashSet<>();
        StringBuilder currShape; // 每个作为起点的点都要new一个新的sb

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]){
                    currShape = new StringBuilder("s"); // start
                    buildIslandShape(currShape, i, j, grid);
                    distinctIslands.add(currShape.toString());
                }

            }
        }

        for (String str : distinctIslands){
            System.out.println(str);
        }
        return distinctIslands.size();
    }

    private void buildIslandShape(StringBuilder currShape, int x, int y, int[][] grid) {
        if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length
                || visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;

        buildIslandShape(currShape.append("d"), x + 1, y, grid);
        buildIslandShape(currShape.append("u"), x - 1, y, grid);
        buildIslandShape(currShape.append("r"), x, y + 1, grid);
        buildIslandShape(currShape.append("l"), x, y - 1, grid);

        // currShape.append("_"); 不必要
    }
}
