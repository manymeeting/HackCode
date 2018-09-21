package others;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

 Each person may dislike some other people, and they should not go into the same group.

 Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

 Return true if and only if it is possible to split everyone into two groups in this way.



 Example 1:

 Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 Output: true
 Explanation: group1 [1,4], group2 [2,3]
 Example 2:

 Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 Output: false
 Example 3:

 Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 Output: false


 Note:

 1 <= N <= 2000
 0 <= dislikes.length <= 10000
 1 <= dislikes[i][j] <= N
 dislikes[i][0] < dislikes[i][1]
 There does not exist i != j for which dislikes[i] == dislikes[j].

 */

// 图染色问题，先构建一个matrix包含所有dislike的关系，然后构建一个int数组，代表每个人的颜色，用dfs（或bfs）开始染色，
// 遇到颜色不match的情况就return false

public class PossibleBipartition886 {

    public boolean possibleBipartition(int N, int[][] dislikes) {
        boolean[][] graph = new boolean[N][N];
        for (int[] dis : dislikes) {
            graph[dis[0]-1][dis[1]-1] = true;
            graph[dis[1]-1][dis[0]-1] = true;
        }
        int[] colors = new int[N]; // color for each person
        for (int i = 0; i < N; i++) {
            if(colors[i] == 0 && !paint(i, 1, colors, graph)) {
                return false;
            }
        }
        return true;

    }

    private boolean paint(int person, int color, int[] colors, boolean[][] graph) {
        // 如果发现已经染色，看颜色是否match
        if(colors[person] != 0) {
            return colors[person] == color;
        }

        colors[person] = color;
        for (int neighbor = 0; neighbor < colors.length; neighbor++) {
            // 尝试给dislike的邻居染色（-color）
            if(graph[person][neighbor] && !paint(neighbor, -color, colors, graph)){
                return false;
            }
        }
        return true;

    }
}
