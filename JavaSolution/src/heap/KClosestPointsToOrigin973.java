package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

 (Here, the distance between two points on a plane is the Euclidean distance.)

 You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



 Example 1:

 Input: points = [[1,3],[-2,2]], K = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 Example 2:

 Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 Output: [[3,3],[-2,4]]
 (The answer [[-2,4],[3,3]] would also be accepted.)


 Note:

 1 <= K <= points.length <= 10000
 -10000 < points[i][0] < 10000
 -10000 < points[i][1] < 10000

 */

// 用pq来比较，注意优化pq的size为K (每次超过size的时候poll最大的出来)

public class KClosestPointsToOrigin973 {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int x1 = a[0], x2 = b[0];
            int y1 = a[1], y2 = b[1];

            return (x2 * x2 + y2 * y2) - (x1 * x1 + y1 * y1);  // 按照从大到小compare，为了维护pq的size
        });

        for (int[] p : points) {
            pq.add(p);
            if(pq.size() > K) {
                pq.poll();
            }

        }

        List<int[]> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            res.add(pq.poll());
        }

        return res.toArray(new int[][]{{}});
    }
}
