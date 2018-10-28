package arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

 Example 1:
 Input: [1,2,1]
 Output: [2,-1,2]
 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number;
 The second 1's next greater number needs to search circularly, which is also 2.
 Note: The length of given array won't exceed 10000.

 */

// 类似于next greater ele1， 用一个stack来记录descending序列的indices，因为是circular数组，所以要循环到2*len，
// 遇到stack顶端index对应的数小于当前数时，就while+pop，同时把当前数set到结果数组里

public class NextGreaterEleII503 {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1); // 初始化，全 -1
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n * 2; i++) {
            int idx = i % n;
            while(!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                res[stack.pop()] = nums[idx];

            }

            if(i < n) {
                // push index for the 1st round
                stack.push(i);
            }
        }

        return res;
    }
}
