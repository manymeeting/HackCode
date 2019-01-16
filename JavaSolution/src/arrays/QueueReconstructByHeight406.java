package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

 Note:
 The number of people is less than 1,100.


 Example

 Input:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 Output:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


 */

// 每次挑出身高最高的人来排序，之后这组人的k值就是他们最终的k值（因为剩下的都是比他们低的），
// 以此类推，把第二高的组排序后按k值插入第一高的组里或组外

// 注意下面实现的技巧，用一个lambda实现了多步排序，同时用指定index的add来自动调整插入后的list，最后list转为Array
public class QueueReconstructByHeight406 {
    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> res = new ArrayList<>();

        for (int[] p : people) {
            res.add(p[1], p);
        }

        return res.toArray(new int[0][0]);
    }
}
