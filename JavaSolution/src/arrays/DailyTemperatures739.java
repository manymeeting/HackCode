package arrays;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

 For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

 Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].


 */


// 用stack存之之前一个较低温度的index，遇到更高的温度就用while从stack里往出pop，直到栈顶对应温度<=当前温度

public class DailyTemperatures739 {

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();

        int[] res = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            int currTemp = temperatures[i];
            while(!stack.isEmpty() && currTemp > temperatures[stack.peek()]) {
                int lastIdx = stack.pop();
                res[lastIdx] = i - lastIdx;
            }
            stack.push(i);
        }

        return res;
    }
}
