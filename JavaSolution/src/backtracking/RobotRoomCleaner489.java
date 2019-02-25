package backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a robot cleaner in a room modeled as a grid.

 Each cell in the grid can be empty or blocked.

 The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

 When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

 Design an algorithm to clean the entire room using only the 4 given APIs shown below.

 interface Robot {
 // returns true if next cell is open and robot moves into the cell.
 // returns false if next cell is obstacle and robot stays on the current cell.
 boolean move();

 // Robot will stay on the same cell after calling turnLeft/turnRight.
 // Each turn will be 90 degrees.
 void turnLeft();
 void turnRight();

 // Clean the current cell.
 void clean();
 }
 Example:

 Input:
 room = [
 [1,1,1,1,1,0,1,1],
 [1,1,1,1,1,0,1,1],
 [1,0,1,1,1,1,1,1],
 [0,0,0,1,0,0,0,0],
 [1,1,1,1,1,1,1,1]
 ],
 row = 1,
 col = 3

 Explanation:
 All grids in the room are marked by either 0 or 1.
 0 means the cell is blocked, while 1 means the cell is accessible.
 The robot initially starts at the position of row=1, col=3.
 From the top left corner, its position is one row below and three columns right.
 Notes:

 The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 The robot's initial position will always be in an accessible cell.
 The initial direction of the robot will be facing up.
 All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 Assume all four edges of the grid are all surrounded by wall.
 */

// 与传统dfs不同之处在于，需要手动控制方向，在backtracking时除了位置恢复还要确保方向的不变，
// 通过连续右转两次，move，再连续右转，可以完成回归原位且方向不变

public class RobotRoomCleaner489 {
    interface Robot{
        public boolean move();
        public void turnLeft();
        public void turnRight();
        public void clean();
    }
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public void cleanRoom(Robot robot) {
        
        Set<String> visited = new HashSet<>();
        helper(robot, 0, 0, 0, visited);
    }

    private void helper(Robot robot, int x, int y, int currDir, Set<String> visited) {

        // Clean current cell
        robot.clean();
        visited.add(x + "," + y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[currDir][0];
            int ny = y + dirs[currDir][1];

            if(!visited.contains(nx + "," + ny) && robot.move()) {

                helper(robot, nx, ny, currDir, visited);
                // 回归原方向和位置
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            // 依次转向四个方向
            robot.turnRight();
            currDir++;
            currDir%=4;
        }
    }
}