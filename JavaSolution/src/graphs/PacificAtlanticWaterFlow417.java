package graphs;

import java.util.*;

/**

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

*/

// 解法2：从两个ocean开始通过dfs判断连通性，取可达点的交集即为最终结果，比解法1更快（只从四个边上的点开始dfs）

class PacificAtlanticWaterFlow417 {
	public List<int[]> pacificAtlantic(int[][] matrix) { 
		List<int[]> res = new ArrayList<>();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }

        int m = matrix.length, n = matrix[0].length;
        boolean[][] canReachPacific = new boolean[m][n];
        boolean[][] canReachAtlantic = new boolean[m][n];

        for(int i=0; i<m; i++){
            dfs(matrix, canReachPacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, canReachAtlantic, Integer.MIN_VALUE, i, n-1);
        }

        for(int i=0; i<n; i++){
            dfs(matrix, canReachPacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, canReachAtlantic, Integer.MIN_VALUE, m-1, i);
        }

        for (int i = 0; i < m; i++) 
            for (int j = 0; j < n; j++) 
                if (canReachPacific[i][j] && canReachAtlantic[i][j]) 
                    res.add(new int[] {i, j});
        return res;


	}

	private int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

	// 判断连通性
	private void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
		int m = matrix.length, n = matrix[0].length;
		if(x<0 || x>=m || y<0 || y>=n || visited[x][y] || matrix[x][y] < height) return;

		visited[x][y] = true;
		for (int[] d : dir) {
			dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
		}

	}
}	


/**

// 解法1：dfs+memory，每个点开始向两个ocean做dfs，用set来做cache加速

class PacificAtlanticWaterFlow417 {
	private Set<int[]> willFlowToAtlantic;
	private Set<int[]> willFlowToPacific;

    public List<int[]> pacificAtlantic(int[][] matrix) {
    	willFlowToAtlantic = new HashSet<>();
    	willFlowToPacific = new HashSet<>();


        List<int[]> res = new ArrayList<>();
        int m = matrix.length;
        if(m == 0) return res;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if(canFlowToAtlantic(i, j, matrix, res, new boolean[m][n], Integer.MAX_VALUE) 
        			&& canFlowToPacific(i, j, matrix, res, new boolean[m][n], Integer.MAX_VALUE)) {
        			res.add(new int[]{i, j});
        		}
        	}
        }

        return res;
        
    }

    private boolean canFlowToAtlantic(int x, int y, int[][] matrix, List<int[]> res, boolean[][] visited, int prevHeight) {
    	if(willFlowToAtlantic.contains(new int[]{x, y})) {
    		return true;
    	}
    	

    	int m = matrix.length;
        int n = matrix[0].length;
    	if(x < 0 || y < 0 || x >= m || y >= n) {
    		return false;
    	}

    	if(visited[x][y]) return false;

    	if(matrix[x][y] > prevHeight) return false;

    	if(x == m-1 || y == n - 1) return true; // Reached Atlantic

    	visited[x][y] = true;

    	if (canFlowToAtlantic(x + 1, y, matrix, res, visited, matrix[x][y]) 
    		|| canFlowToAtlantic(x, y + 1, matrix, res, visited, matrix[x][y])
    		|| canFlowToAtlantic(x - 1, y, matrix, res, visited, matrix[x][y]) 
    		|| canFlowToAtlantic(x, y - 1, matrix, res, visited, matrix[x][y]) ) {

    		willFlowToAtlantic.add(new int[]{x, y});

    		return true;
    	}

    	return false;

    }

    private boolean canFlowToPacific(int x, int y, int[][] matrix, List<int[]> res, boolean[][] visited, int prevHeight) {
    	if(willFlowToPacific.contains(new int[]{x, y})) {
    		return true;
    	}
    	
    	int m = matrix.length;
        int n = matrix[0].length;
    	if(x < 0 || y < 0 || x >= m || y >= n) {
    		return false;
    	}
		if(visited[x][y]) return false;

		if(matrix[x][y] > prevHeight) return false;

    	if(x == 0 || y == 0) return true; // Reached Pacific

    	visited[x][y] = true;

    	if (canFlowToPacific(x - 1, y, matrix, res, visited, matrix[x][y]) 
    		|| canFlowToPacific(x, y - 1, matrix, res, visited, matrix[x][y])
    		|| canFlowToPacific(x + 1, y, matrix, res, visited, matrix[x][y]) 
    		|| canFlowToPacific(x, y + 1, matrix, res, visited, matrix[x][y])
    		) {
    		
    		willFlowToPacific.add(new int[]{x, y});

    		return true;
    	}

    	return false;
    }

   
}	
*/
