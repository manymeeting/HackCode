package heap;

import java.util.*;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order
 * and an integer k.
 * 
 * Define a pair (u, v) which consists of one element from the first array and
 * one element from the second array.
 * 
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3 Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6] Example 2:
 * 
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2 Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence:
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3] Example 3:
 * 
 * Input: nums1 = [1,2], nums2 = [3], k = 3 Output: [[1,3],[2,3]] Explanation:
 * All possible pairs are returned from the sequence: [1,3],[2,3]
 * 
 * 
    ============================
    关键点：相当于merge k-sorted list，用一个pq来放candidates，每次poll出来一个以后放入相邻的candidates。
    最直观的放candidate方法时放(i+1, j)和(i, j+1)，这时要做一些pq的去重处理
    优化：实际上不用放(i, j+1)，对应下图的table，其实每次poll出一个pair后只要放下方的pair即可。
    为什么不放右边的pair？因为右上角的pair更小而且已经在q中，等到右上角的被poll出来后才会需要往下走。
    +----+-------------+-------------+-----------+-----------+
    |    | 2           | 9           | 10        | 15        |
    +----+-------------+-------------+-----------+-----------+
    | 1  | (已poll)1+2 | (已poll)1+9 | (q中)1+10 | (q中)1+15 |
    +----+-------------+-------------+-----------+-----------+
    | 7  | (已poll)7+2 | (q中)7+9    | 7+10      | 7+15      |
    +----+-------------+-------------+-----------+-----------+
    | 11 | 11+2        | 11+9        | 11+10     | 11+15     |
    +----+-------------+-------------+-----------+-----------+
    | 16 | 16+2        | 16+9        | 16+10     | 16+15     |
    +----+-------------+-------------+-----------+-----------+
 * 
 */

public class FindKPairsWithSmallestSums373 {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // min queue, sorted by pair sum
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        List<int[]> res = new ArrayList<>();
        int N1 = nums1.length, N2 = nums2.length;
        if (N1 == 0 || N2 == 0)
            return res; // no pairs to form, just return an empty res list
        // 先把第一'行'的pairs全加进去。
        for (int i = 0; i < Math.min(N1, k); i++)
            q.offer(new int[] { nums1[i], nums2[0], 0 });
        // poll一个再加一个potential进去，注意加potential时只用看'下'方的元素。
        for (int i = 0; i < Math.min(N1 * N2, k); i++) {
            // get the best pair and put into res
            int[] cur = q.poll();
            res.add(new int[] { cur[0], cur[1] });
            // Next better pair could with be A: {after(num1), num2} or B: {num1.
            // after(num2)}
            // 参考上面的note关于为什么只放B
            if (cur[2] < N2 - 1) { // still at least one elem after num2 in array nums2
                int idx = cur[2] + 1;
                q.offer(new int[] { cur[0], nums2[idx], idx });
            }
        }
        return res;
    }
}
