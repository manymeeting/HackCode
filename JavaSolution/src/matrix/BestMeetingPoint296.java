package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 Example:

 Input:

 1 - 0 - 0 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0

 Output: 6

 Explanation: Given three people living at (0,0), (0,4), and (2,2):
 The point (0,2) is an ideal meeting point, as the total travel distance
 of 2+2+2=6 is minimal. So return 6.

 */

// 理论；对于一个包含room index的一维list，使distance sum最小的点在中位数，sum为各数字相对于中位数的差值之和，
// 注意：用了两次循环来得到两个有序的list（mn），而不是一次循环以后再sort（mnlogmn）

public class BestMeetingPoint296 {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rowRoomList = new ArrayList<>();
        List<Integer> colRoomList = new ArrayList<>();

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] == 1)
                {
                    rowRoomList.add(i);
                }
            }
        }

        for (int i = 0; i < grid[0].length; i++)
        {
            for (int j = 0; j < grid.length; j++)
            {
                if(grid[j][i] == 1)
                {
                    colRoomList.add(i);
                }
            }
        }

        int res = 0;
        res += minDistanceFromList(colRoomList) + minDistanceFromList(rowRoomList);
        return res;
    }

    private int minDistanceFromList(List<Integer> list)
    {
        int i = 0, j = list.size()-1;
        int distance = 0;
        while(i < j) {
            distance += list.get(j) - list.get(i);
            i++; j--;
        }
        return distance;
    }


}
