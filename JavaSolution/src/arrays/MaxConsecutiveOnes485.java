package arrays;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.

 Example 1:
 Input: [1,1,0,1,1,1]
 Output: 3
 Explanation: The first two digits or the last three digits are consecutive 1s.
 The maximum number of consecutive 1s is 3.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000

 */
// 注意for循环之后还要判断一次maxSum和sum，因为最后一轮的sum没有比较就达到数组size而终止循环了
public class MaxConsecutiveOnes485 {

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
        {
            int val = nums[i];
            if(val == 0)
            {
                maxSum = maxSum < sum ? sum : maxSum;
                sum = 0;
                continue;
            }
            else {
                sum++;
            }

        }

        if(maxSum < sum)
        {
            maxSum = sum;
        }

        return maxSum;
    }
}
