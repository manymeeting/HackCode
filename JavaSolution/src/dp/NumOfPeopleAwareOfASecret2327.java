package dp;

/**
 * 
 * On day 1, one person discovers a secret.

You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.

Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: n = 6, delay = 2, forget = 4
Output: 5
Explanation:
Day 1: Suppose the first person is named A. (1 person)
Day 2: A is the only person who knows the secret. (1 person)
Day 3: A shares the secret with a new person, B. (2 people)
Day 4: A shares the secret with a new person, C. (3 people)
Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
Example 2:

Input: n = 4, delay = 1, forget = 3
Output: 6
Explanation:
Day 1: The first person is named A. (1 person)
Day 2: A shares the secret with B. (2 people)
Day 3: A and B share the secret with 2 new people, C and D. (4 people)
Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)


people who knows the secret [1, 2, 3, 4, 5]


Constraints:

2 <= n <= 1000
1 <= delay < forget <= n

*/


public class NumOfPeopleAwareOfASecret2327 {
    
    public int peopleAwareOfSecret(int n, int delay, int forget) {

        // dp[i] stores the number of people who start to know the secret at the ith day.
        int dp[] = new int[n];
        dp[0] = 1;

        for (int i = delay; i < n; i++) {
            // People who still remember & People who are out of the delay window.
            if (i - forget + 1 < 0) {
                dp[i] = arraySum(dp, 0, i - delay + 1);
            }
            else {
                dp[i] = arraySum(dp, i - forget + 1, i - delay + 1);
            }
        }

        return arraySum(dp, n - forget, n);
    }

    
    // Calculates the array sum between start(inclusive) and end (exclusive)
    private int arraySum(int[] arr, int start, int end) {
        int res = 0;
        for (int i = start; i < end; i++) {
            res = modAdd(res, arr[i]);
        }
        return res;
    }

    private int modAdd(int a, int b) {
        long M = (long) 1e9 + 7;
        return (int) (((a % M) + (b % M)) % M);
    }
}
