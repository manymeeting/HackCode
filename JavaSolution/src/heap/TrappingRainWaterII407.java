package heap;

import java.util.*;


// 图解：http://www.cnblogs.com/grandyang/p/5928987.html
// 用PQ来遍历，思想是从每次挑height最低的cell，访问其邻居，根据高度差来更新存水量，如果可以填则把该cell的高度更新成填满水之后的高度
// 最初先把所有border的cell加到pq里作为启动状态

class TrappingRainWaterII407 {

	// 为了用pq来遍历，需要一个class结构来存每个cell的坐标和height
    class Cell {
    	int row;
    	int col;
    	int height;

    	Cell(int row, int col, int height) {
    		this.row = row;
    		this.col = col;
    		this.height = height;
    	}

    }

    public int trapRainWater(int[][] heightMap) {

    	if(heightMap == null || heightMap.length == 0) return 0;

    	int m = heightMap.length;
    	int n = heightMap[0].length;

    	// 按照height从小到大排列（每次取出最小的一批height）
    	PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> {
    		return a.height - b.height;
    	});
    	boolean[][] visited = new boolean[m][n];


    	// Add all border cells to pq and mark as visited (边界肯定不能装水)
    	for (int i = 0; i < m; i++) {
    		visited[i][0] = true;
    		visited[i][n-1] = true;
    		pq.add(new Cell(i, 0, heightMap[i][0]));
    		pq.add(new Cell(i, n-1, heightMap[i][n-1]));
    	}

    	for (int i = 0; i < n; i++) {
    		visited[0][i] = true;
    		visited[m-1][i] = true;
    		pq.add(new Cell(0, i, heightMap[0][i]));
    		pq.add(new Cell(m-1, i, heightMap[m-1][i]));
    	}

    	int res = 0;
    	while(!pq.isEmpty()) {
    		Cell curr = pq.poll();

    		int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    		for (int[] dir : dirs ) {
    			// Check neighbors
    			int nr = dir[0] + curr.row;
    			int nc = dir[1] + curr.col;

    			if(nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc]) {
    				continue;
    			} 

    			// 把新的邻居加到visited，同时判断是否能存水
    			// 如果能存（邻居height比curr小），就把这个邻居的高度更新成填满水后的高度，放到pq里
    			// 否则直接按照邻居原本的height加到pq里，之后再遍历

    			visited[nr][nc] = true;
    			res += Math.max(0, curr.height - heightMap[nr][nc]);
    			pq.add(new Cell(nr, nc, Math.max(curr.height, heightMap[nr][nc])));


    		}
    	}

    	return res;
    }
}