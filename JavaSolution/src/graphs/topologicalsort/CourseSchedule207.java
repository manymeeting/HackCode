package graphs.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:

 Input: 2, [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.
 Example 2:

 Input: 2, [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.
 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.

 */

// 算法模板：有向图的拓扑排序，
// 1.构建节点连接关系，和一个数组记录每个点的input degree，
// 2.把degree为0的点放到queue里（bfs遍历），
// 3.依次从queue中取出点，然后把相邻点的degree减一，如果degree为0则加入queue，同时记录一共往queue中加入过多少点
// 4.结束后比较往queue中加入过的点的个数与numCourses，如果相同则有一条可行的path

public class CourseSchedule207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // A data structure for adjacency
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // A data structure for degree of each node (number of coming edges)
        int[] degrees = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            adj.get(edge[0]).add(edge[1]);
            degrees[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < degrees.length; i++) {
            if(degrees[i] == 0) queue.add(i);
        }

        int reachedCourse = queue.size();

        while(queue.size() > 0) {
            int currNode = queue.poll();
            List<Integer> neighbors = adj.get(currNode);
            for (int neighbor : neighbors) {
                degrees[neighbor]--;
                if(degrees[neighbor] == 0) {
                    queue.add(neighbor);
                    reachedCourse++;
                }
            }
        }


        return reachedCourse == numCourses;
    }
}
