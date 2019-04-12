package heap;

import java.util.*;


/**
Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

 

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
 

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9
*/



// greedy思想，先对A排序，然后用heap把B从大到小排，每次用B的当前最大值与A当前最大值比较，
// 如果A大就在对应位置放A的最大值，否则改用A的最小值来充数，把大值留给后面去匹配B的小值
// B: 11,10,4,1
// A: 2,7,11,15
// -> A: 15,11,7,2

// A: 1,4,10,11
// B: 15,11,7,2
// -> A: 1,4,10,11

class AdvantageShuffle870 {
	public int[] advantageCount(int[] A, int[] B) {
        

		Arrays.sort(A);
		// pq元素: int[idx, val]
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			return b[1]-a[1];
		});

       	for (int i = 0; i < B.length; i++) {
       		pq.offer(new int[]{i, B[i]});
       	}

       	int[] res = new int[A.length];
       	int lo = 0; int hi = A.length-1; // two pointer来记录A的当前最小和最大值
       	while(!pq.isEmpty()) {
       		int[] curr = pq.poll();
       		int idx = curr[0];
       		int val = curr[1];
       		if(A[hi] > val) { // 如果A的当前最大>B的当前最大，则用最大去match
       			res[idx] = A[hi];
       			hi--;
       		}
       		else { // 否则，用小的去充数，把大的留给后面去match对方小的
       			res[idx] = A[lo];
       			lo++;
       		}

       	}

       	return res;


    }
}