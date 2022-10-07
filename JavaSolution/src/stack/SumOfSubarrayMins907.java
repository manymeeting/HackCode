package stack;

import java.util.*;

/**
 * 
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444



Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104


思路：属于难题，规律不是很好找，需要仔细列举后才能看出一个可行的算法，此处参照 https://leetcode.com/problems/sum-of-subarray-minimums/discuss/2118729/Very-detailed-stack-explanation-O(n)-or-Images-and-comments
用一个stack来记录从左往右遍历过程中出现的上一个最小的值的index。注意两个占位数值的使用。

关键字：占位数，sentinel，monotonic stack

 */
public class SumOfSubarrayMins907 {


    public int sumSubarrayMins(int[] arr) {

        // A mono increasing stack to store the indexes of the last min values. 
        Deque<Integer> monoIncStack = new ArrayDeque<>();

        long M = (long) 1e9 + 7;
        // Add a sentinel value that is less than the min value in the array, to make sure we can pop things out when an array is like 1,2,3,4,5
        int sentinel = -1;
        monoIncStack.addFirst(sentinel);

        long sum = 0;
        // 注意最后一个index的处理
        for (int i2 = 0; i2 < arr.length + 1; i2++) {
            // Use 0 as the last value because it's greater thant the sentinel but still less than all possible values in the arr.
            int currVal = i2 < arr.length ? arr[i2] : 0;
            while(monoIncStack.peekFirst() != -1 && currVal< arr[monoIncStack.peekFirst()]) {
                int midIndex = monoIncStack.pop();
                int i1 = monoIncStack.peekFirst();

                // 注意mod运算的一个性质：(a + b) % p = (a % p + b % p) % p 
                long valToAdd = (long) arr[midIndex] * (midIndex - i1) * (i2 - midIndex) % M;
                sum += valToAdd;
                sum %= M;
            }

            monoIncStack.addFirst(i2);
        }


        return (int) sum;

    }



    
}



