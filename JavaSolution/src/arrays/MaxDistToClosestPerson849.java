package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

 There is at least one empty seat, and at least one person sitting.

 Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

 Return that maximum distance to closest person.

 Example 1:

 Input: [1,0,0,0,1,0,1]
 Output: 2
 Explanation:
 If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 If Alex sits in any other open seat, the closest person has distance 1.
 Thus, the maximum distance to the closest person is 2.
 Example 2:

 Input: [1,0,0,0]
 Output: 3
 Explanation:
 If Alex sits in the last seat, the closest person is 3 seats away.
 This is the maximum distance possible, so the answer is 3.
 Note:

 1 <= seats.length <= 20000
 seats contains only 0s or 1s, at least one 0, and at least one 1.

 */

// 记录有人的位置，然后比较两个人之间的距离，以及最边上两人到两端的距离

public class MaxDistToClosestPerson849 {
    public int maxDistToClosest(int[] seats) {
        List<Integer> people = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if(seats[i] == 1) people.add(i);
        }

        int maxDist = Math.max(people.get(0), seats.length - 1 - people.get(people.size()-1));
        for (int i = 0; i < people.size() - 1; i++) {
            int dist = people.get(i + 1) - people.get(i);

            maxDist = Math.max(maxDist, dist / 2);
        }

        return maxDist;

    }
}
