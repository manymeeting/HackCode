package twopointer;

import java.util.*;

/**
The i-th person has weight people[i], and each boat can carry a maximum weight of limit.

Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)

 

Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
Note:

1 <= people.length <= 50000
1 <= people[i] <= limit <= 30000
*/


// 注意一个条件：每个船最多坐两个人
// Greedy策略，先按重量从小到大排序，然后从两端配对，配不上的时候让重的人先走

class BoatsToSavePeople881 {
	public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0; int j = people.length -1;
        int boats = 0;
        while(i <= j) { // i==j也可以，因为最后可能会剩一个人，也要安排船
        	if(people[i] + people[j] <= limit) {
        		i++;
        		j--;
        	}
        	else {
        		j--; // 当装不下两个人时，先装重的人，这样轻的人可能可以和次重的人配对
        	}
        	boats++;
        }

        return boats;
    }
}