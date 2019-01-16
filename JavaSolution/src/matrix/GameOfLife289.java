package matrix;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population..
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

 Example:

 Input:
 [
 [0,1,0],
 [0,0,1],
 [1,1,1],
 [0,0,0]
 ]
 Output:
 [
 [0,0,0],
 [1,0,1],
 [0,1,1],
 [0,1,0]
 ]
 Follow up:

 Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

 */

// 参考：https://segmentfault.com/a/1190000003819277
// 为了inplace来解决（不用copy变化前的matrix），可以自己拓展编码来表示两个state的组合，最后再decode回去。
// 进一步优化可参考原文

// 如下所示，对于一个节点来说，如果它周边的点是1或者2，就说明那个点上一轮是活的。
// 这样对每个点计算8个方向的lives数量然后更新该点的state，
// 最后把编码再解回去，即0和2都变回0，1和3都变回1。

/*
自定义编码
0 : 上一轮是0，这一轮过后还是0
1 : 上一轮是1，这一轮过后还是1
2 : 上一轮是1，这一轮过后变为0
3 : 上一轮是0，这一轮过后变为1
*/
public class GameOfLife289 {
    public void gameOfLife(int[][] board) {

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // 对每个点，check八个方向，计算lives数量

                int lives = 0;
                // Check up
                if (i > 0) {
                    lives += board[i-1][j] == 1 || board[i-1][j] == 2 ? 1 : 0;
                }

                // Check left
                if(j > 0) {
                    lives += board[i][j-1] == 1 || board[i][j-1] == 2 ? 1 : 0;
                }

                // Check down
                if(i + 1 < m) {
                    lives += board[i+1][j] == 1 || board[i+1][j] == 2 ? 1 : 0;
                }

                // Check right
                if(j + 1 < n) {
                    lives += board[i][j+1] == 1 || board[i][j+1] == 2 ? 1 : 0;
                }

                // Check top left
                if(i > 0 && j > 0) {
                    lives += board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 2 ? 1 : 0;
                }

                // Check top right
                if(i > 0 && j + 1 < n) {
                    lives += board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 2 ? 1 : 0;
                }

                // Check bottom left
                if(i + 1< m && j > 0){
                    lives += board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 2 ? 1 : 0;
                }

                // Check bottom right
                if(i + 1< m && j + 1 < n){
                    lives += board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 2 ? 1 : 0;
                }


                // 根据lives数量来更新当前点
                if(board[i][j] == 0 && lives == 3) { // 从dead变live
                    board[i][j] = 3;
                }
                if(board[i][j] == 1) {
                    if(lives < 2 || lives > 3) { // 从live变dead
                        board[i][j] = 2;
                    }
                }
            }
        }

        // 解码还原，即0和2都变回0，1和3都变回1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] % 2;
            }
        }
    }
}
