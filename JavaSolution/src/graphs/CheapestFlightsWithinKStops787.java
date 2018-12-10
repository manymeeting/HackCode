package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

 Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

 Example 1:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 1
 Output: 200
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 Example 2:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 Note:

 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.
 */

// Dijkstra算法的变种，不用保存visited的点，因为这道题求的并不严格是最短路径，而是满足某种条件的最短路径。考虑这种情况：
// 从a -> d有两条路径，要求stops < 2，a -> b -> c -> d，以及a -> b -> d。
// 可能出现的一种情况是，path1实际上是最短的，
// 此时b、c已经标记为visited，但是由于stops数量超过限制，d没有得到更新；
// 而a -> b ->d这条路径，由于b已经visited过了，所以不会被放入队列。
// 所以只需要判断当前节点所在路径上的节点数是否还小于K即可，这样就不会陷入死循环。

public class CheapestFlightsWithinKStops787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            prices.put(i, new HashMap<>());
        }
        for (int[] f : flights) {
            prices.get(f[0]).put(f[1], f[2]);
        }

        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> a.price - b.price);
        pq.add(new Tuple(src, 0, -1));

        while(!pq.isEmpty()) {
            Tuple tuple = pq.poll();
            if(tuple.city == dst) {
                return tuple.price;
            }
            if(tuple.stops == K) {
                continue;
            }

            for (Map.Entry<Integer, Integer> next : prices.get(tuple.city).entrySet()) {
                pq.offer(new Tuple(next.getKey(), tuple.price + next.getValue(),
                        tuple.stops + 1));
            }
        }
        return -1;
    }

    class Tuple {
        int city;
        int price;
        int stops;

        public Tuple(int c, int p, int s) {
            city = c;
            price = p;
            stops = s;
        }
    }
}
