package matrix;

/**
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

 The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

 Here are the rules of Tic-Tac-Toe:

 Players take turns placing characters into empty squares (" ").
 The first player always places "X" characters, while the second player always places "O" characters.
 "X" and "O" characters are always placed into empty squares, never filled ones.
 The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 The game also ends if all squares are non-empty.
 No more moves can be played if the game is over.
 Example 1:
 Input: board = ["O  ", "   ", "   "]
 Output: false
 Explanation: The first player always plays "X".

 Example 2:
 Input: board = ["XOX", " X ", "   "]
 Output: false
 Explanation: Players take turns making moves.

 Example 3:
 Input: board = ["XXX", "   ", "OOO"]
 Output: false

 Example 4:
 Input: board = ["XOX", "O O", "XOX"]
 Output: true
 Note:

 board is a length-3 array of strings, where each string board[i] has length 3.
 Each board[i][j] is a character in the set {" ", "X", "O"}.
 */

// 先分析O和X的个数，然后再分析当有人赢了以后另一方个数的情况

public class ValidTicTacToeState794 {
    public boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;
        char[][] boardCh = new char[][]{board[0].toCharArray(), board[1].toCharArray(), board[2].toCharArray()};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(boardCh[i][j] == 'O') countO++;
                if(boardCh[i][j] == 'X') countX++;
            }

        }
        if(countO > countX || countX - countO > 1) return false; // O的个数不可能大于X，两者也不会差大于1

        // 一旦有人赢了就不能继续
        if(countX - countO == 1) {
            if(isFinished(boardCh, 'O')) {
                return false;
            }
        }
        else if (countX == countO){
            if(isFinished(boardCh, 'X')) {
                return false;
            }
        }

        return true;
    }

    private boolean isFinished(char[][] board, char ch) {
        // Row or col same
        for (int i = 0; i < 3; i++) {
            if(board[i][0] == ch && board[i][1] == ch && board[i][2] == ch) {
                return true;
            }
            if(board[0][i] == ch && board[1][i] == ch && board[2][i] == ch) {
                return true;
            }
        }

        // Diagonal same
        if((board[0][0] == ch && board[1][1] == ch && board[2][2] == ch)
                || (board[0][2] == ch && board[1][1] == ch && board[2][0] == ch)) {
            return true;
        }

        return false;
    }
}
