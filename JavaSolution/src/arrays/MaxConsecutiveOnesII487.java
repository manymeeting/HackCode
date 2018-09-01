package arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

 Example 1:
 Input: [1,0,1,1,0]
 Output: 4
 Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 After flipping, the maximum number of consecutive 1s is 4.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000
 Follow up:
 What if the input numbers come in one by one as an infinite stream? In other words,
 you can't store all numbers coming from the stream as it's too large to hold in memory.
 Could you solve it efficiently?
 */

// 维护一个滑动窗口，[l, h] that contains at most maxFlipNumber zero
// 通过queue来存储值为0的index，这样就不用访问之前的input stream，可以处理大数据流的情况
public class MaxConsecutiveOnesII487 {
    public int findMaxConsecutiveOnes(int[] nums) {

        Queue<Integer> zeroIndices = new LinkedList<>();

        int max = 0;
        int maxFlipNumber = 1;
        for(int l=0, h=0; h < nums.length; h++)
        {
            if(nums[h] == 0)
            {
                zeroIndices.offer(h);
            }
            if(zeroIndices.size() > maxFlipNumber)
            {
                l = zeroIndices.poll() + 1;
            }
            max = Math.max(max, h - l + 1);

        }
        return max;
    }
}
