package others;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

 Example:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 Given word = "ABCCED", return true.
 Given word = "SEE", return true.
 Given word = "ABCB", return false.
 */

// backtracking，二维遍历，尝试从每个位置开始，看四个方向是否跟word中的下一位match

public class WordSearch79 {
    public boolean exist(char[][] board, String word) {
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(dfs(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] wordArr, int m, int n, int currLen) {
        if(currLen == wordArr.length) return true; //found

        if (m<0 || n<0 || m == board.length || n == board[0].length || board[m][n] == ' ') return false;
        if (board[m][n] != wordArr[currLen]) return false; // (currLen在后面才更新)

        char original = board[m][n];
        // backtracking
        board[m][n] = ' ';
        boolean exist = dfs(board, wordArr, m + 1, n, currLen+1)
                || dfs(board, wordArr, m - 1, n, currLen + 1)
                || dfs(board, wordArr, m, n + 1, currLen + 1)
                || dfs(board, wordArr, m, n - 1, currLen + 1);
        board[m][n] = original;
        return exist;
    }
}
