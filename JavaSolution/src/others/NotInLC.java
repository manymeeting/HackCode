package others;

public class NotInLC {

    // Sparse vector dot product
    /**
     * Suppose we have very large sparse vectors, which contains a lot of zeros and double .

     find a data structure to store them
     get the dot product of them
     */

    /*
    *
    a = [(1,2),(2,3),(100,5)]
    b = [(0,5),(1,1),(100,6)]

    i = 0; j = 0
    result = 0
    while i < len(a) and j < len(b):
        if a[i][0] == b[j][0]:
            result += a[i][1] * b[j][1]
            i += 1
            j += 1
        elif a[i][0] < b[j][0]:
            i += 1
        else:
            j += 1
    print(result)

    **/


    // K Closest Points
    // https://yeqiuquan.blogspot.com/2017/03/lintcode-612-k-closest-points.html
    /**
     * Description
     Given some points and a point origin in two dimensional space, find k points out of the some points which are nearest to origin.
     Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.


     Example
     Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
     return [[1,1],[2,5],[4,4]]


     思路
     根据题意，我们维护一个大小为K的max-heap。一个一个把point放进去，如果容量超了，就把最大的踢掉。这样heap里永远是最小的K个。（注意不是min-heap，自己举个例子就明白了。如果heap是[3, 4, 5]满了又来了2怎么办？当然是把5踢了，所以是max-heap。）
     Comparator写的时候根据题意，如果距离相等，就比x轴，如果还相等，就比y轴。
     最后把max-heap里面这K个points倒出来就是最近的K个。
     另外算距离的时候不用开根，因为我们只比大小，所以勾方股方相加的数就够了可以比了。

     */

    /*
    public class Solution {

         * @param points a list of points
         * @param origin a point
         * @param k an integer
         * @return the k closest points


        public Point globalOrigin = null;
        public Point[] kClosest(Point[] points, Point origin, int k) {
            // Write your code here

            globalOrigin = origin;
            PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new Comparator<Point>() {
                public int compare(Point a, Point b) {
                    int diff = getDistance(b, globalOrigin) - getDistance(a, globalOrigin);
                    if(diff == 0) {
                        diff = b.x - a.x;
                    }
                    if (diff == 0) {
                        diff = b.y - a.y;
                    }
                    return diff;
                }
            });

            for (Point pt : points) {
                pq.add(pt);
                if (pq.size() > k)
                    pq.poll();
            }


            Point[] result = new Point[k];
            while (k - 1 >= 0) {
                result[--k] = pq.poll();
            }

            return result;
        }

        public int getDistance(Point a, Point b) {
            return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        }
    }
     */


    // Continuous sum equals target
    /**
     * 一个数组，没有负数，可能有重复数，return是否有连续的数加起来等于target
     * */
    /*
    * 解法一：用set记录preSum，看target-sum是否在之前出现过
    * 解法二：一遍loop，看到preSum大于target时，用另外一个pointer从头开始减，如果减的结果为target则说明找到
    * **/
}
