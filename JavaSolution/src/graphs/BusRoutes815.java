package graphs;

import java.util.*;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

 We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

 Example:
 Input:
 routes = [[1, 2, 7], [3, 6, 7]]
 S = 1
 T = 6
 Output: 2
 Explanation:
 The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 Note:

 1 <= routes.length <= 500.
 1 <= routes[i].length <= 500.
 0 <= routes[i][j] < 10 ^ 6.
 * */


// BFS图中找最短路径，将每个车站作为node，通过routes来建立各车站的连接关系

public class BusRoutes815 {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>(); // 车站：经过车站的buses
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        int res = 0;

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                List<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                map.put(routes[i][j], buses); // in case the original one didn't exist
            }
        }

        queue.offer(S);
        while (!queue.isEmpty()) {
            res++;

            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int currStation = queue.poll();
                List<Integer> buses = map.get(currStation);
                for (int bus : buses) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == T) return res;
                        queue.offer(routes[bus][j]); // 添加新node
                    }
                }
            }

        }
        return -1;
    }
}
