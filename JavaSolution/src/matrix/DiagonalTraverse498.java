package matrix;

/**
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 Example:
 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output:  [1,2,4,7,5,3,6,8,9]
 Explanation:

 Note:
 The total number of elements of the given matrix will not exceed 10,000.

 */
// 不用传统的for来遍历，声明row和col，根据各自的值来判断是否越界，越界则开始转向，
// 转向用一个2*2的数组控制{{-1, 1}, {1, -1}}，注意top, right和bottom，left越界处理时的区别
    
public class DiagonalTraverse498 {
    public int[] findDiagonalOrder(int[][] matrix) {

        // matrix一定要check越界，否则matrix[0].length 会出error
        if(matrix == null || matrix.length == 0) return new int[0];

        int m = matrix.length;
        int n = matrix[0].length;

        int[] res = new int[m * n];
        // 通过d = 1 - d; 来改变行进方向
        int[][] direction = new int[][]{{-1, 1}, {1, -1}};

        int d = 0;
        int col = 0, row = 0; // 非正常顺序遍历，需单独定义idx
        for (int i = 0; i < m * n; i++) {
            res[i] = matrix[row][col];
            row += direction[d][0];
            col += direction[d][1];

            if(row >= m) {
                row = m - 1; col += 2; d = 1 - d;
            }
            if(col >= n) {
                col = n - 1; row += 2; d = 1 - d;
            }
            if(row < 0) {
                row = 0; d = 1 - d;
            }
            if(col < 0) {
                col = 0; d = 1 - d;
            }
        }
        return res;
    }

}
