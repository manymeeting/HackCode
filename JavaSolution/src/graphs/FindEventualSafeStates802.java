package graphs;

import java.util.ArrayList;
import java.util.List;

/***
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

 Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

 Which nodes are eventually safe?  Return them as an array in sorted order.

 The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

 Example:
 Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 Output: [2,4,5,6]
 Here is a diagram of the above graph.
 */

// 用一个array来记录每个node的状态，然后用dfs更新每个node的状态，注意enum的使用（相比于直接用int更清楚）

public class FindEventualSafeStates802 {
    enum State {
        UNSAFE,
        SAFE
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        State[] states = new State[graph.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < graph.length; i++){
            if(isSafe(graph, states, i)) {
                res.add(i);
            }
        }

        return res;
    }


    private boolean isSafe(int[][] graph, State[] states, int state ) {
        if(states[state] != null) {
            return states[state].equals(State.SAFE);
        }

        states[state] = State.UNSAFE; // 遍历next之前先mark当前node为unsafe，表示已visited

        for (int next : graph[state]) {
            if(!isSafe(graph, states, next)) { // 只要有一个next是unsafe，当前node就是unsafe

                return false;
            }
        }
        states[state] = State.SAFE;
        return true;
    }
}
