package math;

/**
 *
 Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

 Example:

 Input:
 [1,2,3]

 Output:
 3

 Explanation:
 Only three moves are needed (remember each move increments two elements):

 [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
// 数学推导：假设x为经过移动后的每个数字的值，sum + m * (n - 1) = x * n，同时因为x = minNum + m，
// 得到 sum - minNum * n = m

public class MinMovesToEqualArrEle453 {
    public int minMoves(int[] nums) {

        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums)
        {
            sum += num;
            min = Math.min(min, num);
        }

        return sum - min * nums.length;
    }
}
