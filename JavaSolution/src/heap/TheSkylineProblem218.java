package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:

 The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 The input list is already sorted in ascending order by the left x position Li.
 The output list must be sorted by the x position.
 There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

// 把这些矩形拆成两个点，一个左上顶点，一个右上顶点。将所有顶点按照横坐标排序后开始遍历，
// 遍历时，通过一个堆来得知当前图形的最高位置。堆顶是所有顶点中最高的点，只要这个点没被移出堆，说明这个最高的矩形还没结束。
// 对于左顶点，我们将其加入堆中。对于右顶点，我们找出堆中其相应的左顶点，然后移出这个左顶点

// 注意这里PriorityQueue的remove方法的使用，直接按照value来从pq中删除

// 复杂度：时间 O(NlogN) 空间 O(N)

public class TheSkylineProblem218 {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        // 拆解矩形，构建顶点的列表 （每个矩形一左一右）
        for (int[] b : buildings) {
            // 左顶点存为负数，右顶点存为正数，在后面直接通过判断正负来确定区间是否结束
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }


        // 根据横坐标对列表排序，相同横坐标的点纵坐标小的排在前面
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // 构建堆，按照纵坐标从大到小排序
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // 将地平线值先加入堆中
        pq.add(0);
        // prev用于记录上次keypoint的高度，用来track是否高度发生变化
        int prev = 0;

        for (int[] h : height) {
            // 将左顶点加入堆中 (取正数)
            if(h[1] < 0) {
                pq.add(-h[1]);
            }
            else {
                // 将右顶点对应的左顶点移去
                pq.remove(h[1]);
            }

            int curr = pq.peek();
            if(curr != prev) {
                // 如果堆的新顶部和上个keypoint高度不一样，则加入一个新的keypoint到结果集
                res.add(new int[]{h[0], curr});
                prev = curr;
            }
        }

        return res;
    }
}
