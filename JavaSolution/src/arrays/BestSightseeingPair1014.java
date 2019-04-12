package arrays;

import java.util.*;


/**
Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.

The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 

Example 1:

Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 

Note:

2 <= A.length <= 50000
1 <= A[i] <= 1000
*/

// 用update on the fly的思想，通过每一步在best的基础上减1来一遍loop得到结果
class BestSightseeingPair1014 {
	public int maxScoreSightseeingPair(int[] A) {
		int res = 0;
		int best = 0;
		for (int a : A ) {
			res = Math.max(res, a + best);
			best = Math.max(best, a) - 1;
		}

		return res;     
    }
}