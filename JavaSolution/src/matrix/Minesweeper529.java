package matrix;

/**
 * Let's play the minesweeper game (Wikipedia, online game)!

 You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

 Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

 If a mine ('M') is revealed, then the game is over - change it to 'X'.
 If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 Return the board when no more squares will be revealed.


 Example 1:

 Input:

 [['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

 Click : [3,0]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Explanation:

 Example 2:

 Input:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Click : [1,2]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Explanation:



 Note:

 The range of the input matrix's height and width is [1,50].
 The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 The input board won't be a stage when game is over (some mines have been revealed).
 For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.

 */

// DFS遍历，注意变换八个方向时的技巧（i，j增量都从-1到1变化）

public class Minesweeper529 {
    public char[][] updateBoard(char[][] board, int[] click) {

        int m = board.length;
        int n = board[0].length;

        int cr = click[0];
        int cc = click[1];

        if(board[cr][cc] != 'E' && board[cr][cc] != 'M') return board; // Already visited

        if(board[cr][cc] == 'M'){
            board[cr][cc] = 'X';
        }
        else {
            int mineCnt = 0;
            // Calculate adj mine count
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if(i == 0 && j == 0) continue; // 注意跳过起点位置
                    int nr = cr + i;
                    int nc = cc + j;
                    if(nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
                    if(board[nr][nc] == 'M') {
                        mineCnt++;
                    }
                }
            }

            // DFS遍历周围八个方向
            if(mineCnt == 0) {
                // 是empty，继续向周围遍历
                board[cr][cc] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if(i == 0 && j == 0) continue;
                        int nr = cr + i;
                        int nc = cc + j;
                        if(nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
                        updateBoard(board, new int[]{nr, nc});
                    }
                }
            }
            else {
                // 周围有mine，停止递归，直接标上mine的个数
                board[cr][cc] = (char) ('0' + mineCnt);
            }
        }

        return board;
    }
}

