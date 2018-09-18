package others;

import java.util.*;

/**
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

 Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

 We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

 Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

 Example 1:
 Input:
 pid =  [1, 3, 10, 5]
 ppid = [3, 0, 5, 3]
 kill = 5
 Output: [5,10]
 Explanation:
 3
 /   \
 1     5
 /
 10
 Kill 5 will also kill 10.
 Note:
 The given kill id is guaranteed to be one of the given PIDs.
 n >= 1.
 */

// 思路：删一个以后，可能会连带删多层的process。先用一个map存所有的parent-children映射关系，
// 再用从kill开始，用queue来在map中bfs所有直接和间接的children

public class KillProcess582 {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> pMap = new HashMap<>();
        for (int i = 0; i < pid.size(); i++)
        {
            int child = pid.get(i);
            int parent = ppid.get(i);
            if(pMap.containsKey(parent)) {
                pMap.get(parent).add(child);
            }
            else {
                List<Integer> childrenList = new ArrayList<>();
                childrenList.add(child);
                pMap.put(parent, childrenList);
            }
        }

        List<Integer> res = new ArrayList<>();
        Queue<Integer> killQueue = new LinkedList<>();
        killQueue.add(kill);

        while(!killQueue.isEmpty()) {
            int p = killQueue.poll();
            res.add(p);


            killQueue.addAll(pMap.getOrDefault(p, new ArrayList<>()));
        }

        return res;
    }
}
