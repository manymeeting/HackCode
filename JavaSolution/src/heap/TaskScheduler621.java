package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks,
 there must be at least n intervals that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 Note:
 The number of tasks is in the range [1, 10000].
 The integer n is in the range [0, 100].
 */
// 用pq来排序字符出现频率（不用存ch，因为只要求给出最终所需time），
// 再用一个map<时间点，剩余出现次数>来记录coolDown信息，注意其实不用记录字符和coolDown的关系

// 另一种解法，更直观：直接算出来，size = Math.min((n+1) * 最大freq) + 最大freq的数的个数， task个数)
public class TaskScheduler621 {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;
        Map<Character, Integer> charsCount = new HashMap<>();
        for (char ch : tasks) {
            charsCount.put(ch, charsCount.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        Map<Integer, Integer> coolDown = new HashMap<>();
        for (char ch : charsCount.keySet()) {
            queue.offer(charsCount.get(ch));
        }

        int currTime = 0;
        while(!queue.isEmpty() || !coolDown.isEmpty()) {

            if(coolDown.containsKey(currTime - n - 1)) {
                queue.offer(coolDown.remove(currTime - n - 1));

            }
            if(!queue.isEmpty()) {
                int leftCount = queue.poll() - 1;
                if(leftCount > 0) {
                    coolDown.put(currTime, leftCount);
                }
            }

            currTime++;
        }

        return currTime;
    }

}
