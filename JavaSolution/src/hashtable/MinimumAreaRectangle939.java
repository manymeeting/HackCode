package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

 If there isn't any rectangle, return 0.



 Example 1:

 Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 Output: 4
 Example 2:

 Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 Output: 2


 Note:

 1 <= points.length <= 500
 0 <= points[i][0] <= 40000
 0 <= points[i][1] <= 40000
 All points are distinct.

 */

// 用map来记录每个点所在纵向直线上的其它点的y坐标，n^2遍历所有点，
// 通过查看map来判断能否根据当前两点能在map里再找到其它两点形成长方形，若可以则更新min area

public class MinimumAreaRectangle939 {

    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if(!map.containsKey(point[0])) {
                map.put(point[0], new ArrayList<>());
            }
            map.get(point[0]).add(point[1]);
        }


        int minArea = Integer.MAX_VALUE;

        for (int[] x : points) {
            for (int[] y : points) {
                if((x[0] == y[0]) || (x[1] == y[1])) {
                    continue;
                }
                else {
                    // 判断第二个点的y坐标是否能在第一个点所在纵向直线上找到，同时第一个点的y坐标是否存在于第二个点的纵向直线上
                    // （是否存在另外两个点，拼在一起能够形成长方形）
                    if(map.get(x[0]).contains(y[1]) && map.get(y[0]).contains(x[1])){
                        minArea = Math.min(minArea, Math.abs(y[0] - x[0]) * Math.abs(y[1] - x[1])); // 注意用abs
                    }
                }

            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }

}
