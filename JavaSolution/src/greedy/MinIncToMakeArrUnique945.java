package greedy;

import java.util.*;
/**
Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.

Return the least number of moves to make every value in A unique.

 

Example 1:

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].
Example 2:

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 

Note:

0 <= A.length <= 40000
0 <= A[i] < 40000
*/


// 先从小到大排序，然后用Greedy思想，每次只要见到前一个比后一个大，就increase后一个数到前一个的值+1，同时更新inc数量
class MinIncToMakeArrUnique945 {
    public int minIncrementForUnique(int[] A) {
    	if(A.length < 1) return 0;
        Arrays.sort(A);
        // 1,1,2,2,3,7
        int inc = 0;
        for (int i = 0; i < A.length - 1; i++) {
        	if(A[i] < A[i+1]) {
        		continue;
        	}

        	inc += A[i] - A[i+1] + 1;
        	A[i+1] = A[i] + 1;
        }

        return inc;
    }
}