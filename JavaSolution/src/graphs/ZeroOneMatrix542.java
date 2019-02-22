package graphs;

import java.util.ArrayDeque;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

 The distance between two adjacent cells is 1.
 Example 1:
 Input:

 0 0 0
 0 1 0
 0 0 0
 Output:
 0 0 0
 0 1 0
 0 0 0
 Example 2:
 Input:

 0 0 0
 0 1 0
 1 1 1
 Output:
 0 0 0
 0 1 0
 1 2 1
 Note:
 The number of elements of the given matrix will not exceed 10,000.
 There are at least one 0 in the given matrix.
 The cells are adjacent in only four directions: up, down, left and right.
 */


// 初始化时把所有0的cell坐标放到queue里，同时为了方便把非0的点set为最大数，
// 然后用bfs遍历，遇到周围cell的距离小于当前距离+1时就更新距离，同时把更新的坐标加到queue里作为下一层的candidate
    
public class ZeroOneMatrix542 {
    public int[][] updateMatrix(int[][] matrix) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int m = matrix.length;
        int n = matrix[0].length;
        if(m < 1) {
            return new int[][]{{-1}};
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] != 0) {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
                else {
                    q.add(new int[]{i, j});
                }
            }
        }


        // 依次从queue中poll出cell，然后更新四个方向的distance，更新的同时把新的cell放到queue里
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] dir : dirs) {
                int row = curr[0] + dir[0];
                int col = curr[1] + dir[1];
                if(row < 0 || row >= m || col < 0 || col >= n
                        || matrix[row][col] <= matrix[curr[0]][curr[1]] + 1) {
                    continue;
                }

                q.add(new int[]{row, col});
                matrix[row][col] = matrix[curr[0]][curr[1]] + 1;
            }
        }

        return matrix;

    }
}
