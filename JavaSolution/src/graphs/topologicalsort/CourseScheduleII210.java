package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

 There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

 Example 1:

 Input: 2, [[1,0]]
 Output: [0,1]
 Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 course 0. So the correct course order is [0,1] .
 Example 2:

 Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 Output: [0,1,2,3] or [0,2,1,3]
 Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 */

// 类似于207，还是有向图的拓扑排序，不过要输出路径

public class CourseScheduleII210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjs = new ArrayList<>();
        int[] incomeLinkCounts = new int[numCourses];

        // 构建adjs
        for (int i = 0; i < numCourses; i++){
            adjs.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            incomeLinkCounts[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }

        // 初始化incoming link数组
        int[] order = new int[numCourses];
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < incomeLinkCounts.length; i++) {
            if (incomeLinkCounts[i] == 0) toVisit.offer(i);
        }

        // 开始遍历图
        int visited = 0;
        while(!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited] = from;
            visited++;
            for (int to : adjs.get(from)) {
                incomeLinkCounts[to]--;
                if(incomeLinkCounts[to] == 0) {
                    toVisit.offer(to);
                }
            }
        }

        // 检查是否存在有效路径
        return visited == incomeLinkCounts.length ? order : new int[0];
    }
}
