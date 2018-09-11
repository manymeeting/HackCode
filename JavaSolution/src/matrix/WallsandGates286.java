package matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.

 -1 - A wall or an obstacle.
 0 - A gate.
 INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 Example:

 Given the 2D grid:

 INF  -1  0  INF
 INF INF INF  -1
 INF  -1 INF  -1
 0  -1 INF INF
 After running your function, the 2D grid should be:

 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4

 */

// 用BFS+queue来解决，先把所有的0的index放到queue里，然后每次poll一个，如果四个方向相邻的元素是INF则val' = val+1，
// 把当前相邻元素的index放到queue里，直到queue为空

public class WallsandGates286 {
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++)
        {
            for (int j = 0; j < rooms[i].length; j++)
            {
                if(rooms[i][j] == 0) queue.add(new int[]{i, j});
            }
        }

        while(!queue.isEmpty())
        {
            int[] top = queue.poll();
            int row = top[0], col = top[1];
            if(row > 0 && rooms[row-1][col] == Integer.MAX_VALUE)
            {
                rooms[row-1][col] = rooms[row][col]+1;
                queue.add(new int[]{row-1, col});
            }
            if(row < rooms.length - 1&& rooms[row+1][col] == Integer.MAX_VALUE)
            {
                rooms[row+1][col] = rooms[row][col]+1;
                queue.add(new int[]{row+1, col});
            }
            if(col > 0 && rooms[row][col-1] == Integer.MAX_VALUE)
            {
                rooms[row][col-1] = rooms[row][col]+1;
                queue.add(new int[]{row, col-1});
            }
            if(col < rooms[0].length - 1 && rooms[row][col+1] == Integer.MAX_VALUE)
            {
                rooms[row][col+1] = rooms[row][col]+1;
                queue.add(new int[]{row, col+1});
            }
        }
    }
}
