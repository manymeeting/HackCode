package graphs;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

 Note:

 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.
 Example 1:

 Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 Example 2:

 Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 But it is larger in lexical order.

 */

// 图问题，先构造graph（一个Map<String, List<String>>），然后对每个entry的value排序（保证字典顺序），
// 再通过递归来每次从当前机场的目的地list中remove第一个，并把当前的机场名加到结果list中（加在头部，保证了最终的顺序）

public class ReconstructItinerary332 {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new LinkedList<>();
        if (tickets == null || tickets.length == 0) return res;

        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            if(!graph.containsKey(ticket[0])) {
                // create entry
                List<String> newPath = new ArrayList<>();
                newPath.add(ticket[1]);
                graph.put(ticket[0], newPath);
            }
            else {
               // add destination
               graph.get(ticket[0]).add(ticket[1]);
            }
        }

        // sort each path
        for (List<String> paths : graph.values()) {
            Collections.sort(paths);
        }

        buildItinerary(res, "JFK", graph);

        return res;
    }

    private void buildItinerary(List<String> res, String curr, Map<String, List<String>> graph) {
        while(graph.containsKey(curr) && !graph.get(curr).isEmpty()) {
            String next = graph.get(curr).remove(0);
            buildItinerary(res, next, graph);

        }
        // 注意要加在头部
        res.add(0, curr);
    }
}
