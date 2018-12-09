package others;

import java.util.PriorityQueue;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



 Example 1:

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 Output: 12

 Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

 Example 2:

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (3, 2)

 Output: -1

 Explanation: There is no way for the ball to stop at the destination.



 Note:

 There is only one ball and one destination in the maze.
 Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */


// Dijkstra算法（BFS求图两端点的最短路径）：https://www.cnblogs.com/godfray/p/4077146.html
// 一般来说最短路径之类的问题都用bfs解决，
// 维护一个distance的matrix来记录每个点到起始点的最短dist，同时用每个node来表示每次移动后起始的点，
// 用pq来每次选择当前距离起始点最短的点作为新起始点，直到遇到destination为止，得到的路径就是最短路径

public class TheMazeII505 {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    class Node{
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze.length == 0 || maze[0].length == 0) return -1;
        int m = maze.length, n = maze[0].length;

        int[][] distance = new int[m][n]; // 存储每个点的最小路径
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.dist - n2.dist;
        });

        pq.offer(new Node(start[0], start[1], 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.x][node.y]) continue;

            if(node.x == destination[0] && node.y == destination[1]) {
                return node.dist; // 已到达终点，返回终点的distance即为起点到终点的最短距离
            }

            visited[node.x][node.y] = true;

            for (int[] dir : dirs) {
                int x = node.x;
                int y = node.y;
                int dist = node.dist;
                // 移动直到遇到障碍
                while(x+dir[0] >= 0 && x+dir[0] < m && y+dir[1] >= 0 && y+dir[1] < n && maze[x+dir[0]][y+dir[1]] != 1) {
                    x += dir[0];
                    y += dir[1];
                    dist++;
                }
                if (visited[x][y]) continue; // 有可能遇到visited（曾经以该点为起始点），直接忽略
                if(distance[x][y] == 0 || distance[x][y] > dist) {
                    // 初始化该点dist或更新成更短的dist
                    distance[x][y] = dist;
                    pq.offer(new Node(x, y, dist));
                }
            }
        }

        return -1;
    }
}
