package hashtable;

import java.util.HashMap;
import java.util.List;

/**
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks.
 * The bricks have the same height but different width. You want to draw a vertical line from the
 * top to the bottom and cross the least bricks.

 The brick wall is represented by a list of rows. Each row is a list of integers representing the
 width of each brick in this row from left to right.

 If your line go through the edge of a brick, then the brick is not considered as crossed. You
 need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

 You cannot draw a line just along one of the two vertical edges of the wall, in which case the
 line will obviously cross no bricks.

 Example:
 Input:
 [[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
 Output: 2
 */

//转化为，找宽度sum对应的最大count，遍历每个list，每次加上当前的val，同时在map里更新当前sum的count，更新最大count
//最后return size-最大count，即为crossed bricks数量
//注意由于不能走到最后一个元素，否则size全一致，在遍历每个list时要到 < size()-1 为止
public class BrickWall554 {
    public int leastBricks(List<List<Integer>> wall) {

        if(wall.size() == 0 || wall == null)
        {
            return 0;
        }

        HashMap<Integer, Integer> widthMap = new HashMap<>();
        int maxCount = 0;

        for (List<Integer> line : wall)
        {

            int width = 0;

            // use size() - 1 to avoid draw a line at the end
            for (int i = 0; i < line.size() - 1; i++)
            {
                width += line.get(i);
                int count = 0;
                if(widthMap.containsKey(width))
                {
                    count = widthMap.get(width) + 1;
                }
                else
                {
                    count = 1;
                }
                widthMap.put(width, count);
                maxCount = Math.max(maxCount, count);
            }
        }

        return wall.size() - maxCount;

    }
}
