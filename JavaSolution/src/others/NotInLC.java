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


    // 约瑟夫环
    /**
     * 小孩报数喝茶是给1-n一共n个小孩还有个数k，小孩们坐成一圈，然后从1开始报数，谁报到k就从圈里移出去，
     * 下一个人继续从1开始报数，直到最后一个小孩。
     * */

    // Remove 3 consecutive duplicate
    /**
     * 把大于等于三个连续字母给去掉, 问最后剩的字符串
     * 需要用递归的解法，因为一次消除后剩下的str里可能还会有3个连续的。
     * */
    /*
    public class RemoveThree {

        public static String removeConsecutive(String s) {

            for (int i=0,j=0; j<s.length(); j++) {
                // increment j until we see a different char
                if (s.charAt(i) == s.charAt(j)) continue;
                // delete the repeating sequence with more than 3 chars
                if (j-i >= 3) {
                    return removeConsecutive(s.substring(0,i) + s.substring(j));
                }
                else {
                    // search repetition for a new char
                    i = j;
                }
            }
            return s;

        }

        public static void main(String[] args) {

            String input1 = "AABBCCCCDD";
            String input2 = "AABBCCCCBADD";
            System.out.println(input1 + " => " + removeConsecutive(input1));
            System.out.println(input2 + " => " + removeConsecutive(input2));

        }
    }

    // BST中删除Node和Rebalance
    public TreeNode deleteNodeFromBST(TreeNode root, int val) {


        if(root == null) {
            return null;
        }

        if(root.val > val) {
            root.left = deleteNode(root.left, val);
        }
        if(root.val < val) {
            root.right = deleteNode(root.right, val);   
        }
        if(root.val == val) {
            // If is leaf node
            if(root.left == null && root.right == null) {
                return null;
            }

            // If has left child but no right child
            if(root.left != null && root.right == null) {
                return root.left;
            }

            // If has right child but no left child
            if(root.right != null && root.left == null) {
                return root.right;
            }

            // Has both children, find the smallest one in the right subtree
            TreeNode smallestRight = findSmallestRight(root);
            TreeNode smallestRightParent = findParent(smallestRight);
            
            smallestRightParent.left = smallestRight.right;
            smallestRight.right = root.right;
            smallestRight.left = root.left;
            return smallestRight;

        }

    }
    */

}
