package datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.

 FreqStack has two functions:

 push(int x), which pushes an integer x onto the stack.
 pop(), which removes and returns the most frequent element in the stack.
 If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.


 Example 1:

 Input:
 ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 Output: [null,null,null,null,null,null,null,5,7,5,4]
 Explanation:
 After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

 pop() -> returns 5, as 5 is the most frequent.
 The stack becomes [5,7,5,7,4].

 pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 The stack becomes [5,7,5,4].

 pop() -> returns 5.
 The stack becomes [5,7,4].

 pop() -> returns 4.
 The stack becomes [5,7].


 Note:

 Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

 */

// push和pop均为常数时间的解法：维护两个map，一个记录x和对应的freq，另一个是出现freq次的所有元素构成的stack，
// 这样同样的元素会出现在多个stack里，比如如果pop之后出现3次的元素没有了，就可以开始从出现2次的stack中pop。

public class MaximumFrequencyStack895 {

    private Map<Integer, Integer> freq; // <元素，对应频率>
    private Map<Integer, Stack<Integer>> stackMap; // <频率，出现频率>=当前频率的元素构成的stack>
    private int maxFreq; // 当前所有元素中最大的频率

    public MaximumFrequencyStack895() {
        freq = new HashMap<>();
        stackMap = new HashMap<>();
    }

    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);

        maxFreq = Math.max(f, maxFreq);

        if(!stackMap.containsKey(f)) {
            stackMap.put(f, new Stack<>());
        }

        stackMap.get(f).push(x);
    }

    public int pop() {
        int x = stackMap.get(maxFreq).pop();
        freq.put(x, freq.get(x) - 1);

        if(stackMap.get(maxFreq).size() == 0) {
            maxFreq--;
        }

        return x;
    }
}
