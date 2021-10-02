package heap;

import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.

 */

// 可以看成是matrix形式的merge k sorted list，第一行是各个list的head
// 为了拿到同一列的下一个元素，要记录每个元素的xy坐标，可以自定义一个class


public class KthSmlstEleInASortedMatrix378 {
    class Tuple{
        public int x;
        public int y;
        public int val;
        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        int n = matrix[0].length;
        for (int i = 0; i < matrix[0].length; i++) {
            pq.add(new Tuple(0, i, matrix[0][i]));
        }


        for (int i = 0; i < k - 1; i ++ ){
            Tuple curr = pq.poll();
            if(curr.x < n - 1) { // 防止越界
                pq.add(new Tuple(curr.x + 1, curr.y, matrix[curr.x + 1][curr.y]));
            }

        }

        return pq.poll().val;

    }
}
