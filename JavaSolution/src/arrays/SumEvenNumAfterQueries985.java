package arrays;

import java.util.*;

/**
We have an array A of integers, and an array queries of queries.

For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.

(Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)

Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.

 

Example 1:

Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
Output: [8,6,2,4]
Explanation: 
At the beginning, the array is [1,2,3,4].
After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
1 <= queries.length <= 10000
-10000 <= queries[i][0] <= 10000
0 <= queries[i][1] < A.length
*/

// 先计算出一个初始的even元素sum，然后逐个分析每个query，根据改动位置元素的奇偶性来直接更新sum

class SumEvenNumAfterQueries985 {
	public int[] sumEvenAfterQueries(int[] A, int[][] queries) {

        int[] res = new int[queries.length];
        int sumEven = 0;
        for (int i = 0; i < A.length; i++) {
        	if(A[i] % 2 == 0) {
        		sumEven += A[i];
        	}
        }

        for (int i = 0; i < queries.length; i++) {
        	int[] q = queries[i];
        	int idx = q[1];
        	int inc = q[0];
        	int oldVal = A[idx];
        	A[idx] += inc;


        	if(oldVal % 2 == 0) { // Old value is even

        		if(inc % 2 == 0) {
        			// Still even
        			sumEven += inc;
        		}
        		else {
        			// Become odd
        			sumEven -= oldVal;
        		}
        	}
        	else {
        		if(inc % 2 != 0) {
        			// Become even
        			sumEven += A[idx];
        		}
        		else {
        			// Still odd
        		}
        	}

        	res[i] = sumEven;
        }


        return res;
    }
}