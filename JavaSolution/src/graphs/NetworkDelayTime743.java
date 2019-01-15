package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There are N network nodes, labelled 1 to N.

 Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

 Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 Note:
 N will be in the range [1, 100].
 K will be in the range [1, N].
 The length of times will be in the range [1, 6000].
 All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

 */

// Graph数据结构总结：
// 1，表示connection关系，没有权重，可用List<List<>>表示，也可以用二维数组int[][]，
// 2，表示connection和edge的权重，可用Map<Map<>>表示

// 标准的Dijkstra算法来找路径长度
public class NetworkDelayTime743 {

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map  = new HashMap<>();
        for (int[] time : times) {
            if(!map.containsKey(time[0])) {
                map.put(time[0], new HashMap<>());
            }
            map.get(time[0]).put(time[1], time[2]);
        }


        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));

        pq.add(new int[]{0, K});
        boolean[] visited = new boolean[N+1];
        int res = 0;

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[1];
            int currDist = curr[0];
            if(visited[currNode]) continue;

            visited[currNode] = true;
            res = currDist;
            N--;

            if(map.containsKey(currNode)) {
                for (int next : map.get(currNode).keySet()) {
                    pq.add(new int[]{currDist + map.get(currNode).get(next), next});
                }
            }
        }

        return N == 0 ? res : -1;

    }

}
