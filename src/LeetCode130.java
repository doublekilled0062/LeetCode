/**
 * 130. 被围绕的区域
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class LeetCode130 {
    /**
     * 先找边界 根据边界做BFS
     * @param board
     */
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int row = board.length;
        int col = board[0].length;

        for(int i = 0; i < row; i++){
            if(board[i][0] == 'O'){
                solve(board, i, 0, row, col);
            }
            if(board[i][col - 1] == 'O'){
                solve(board, i, col - 1, row, col);
            }
        }
        for(int i = 1; i < col - 1; i++){
            if(board[0][i] == 'O'){
                solve(board, 0, i, row, col);
            }
            if(board[row - 1][i] == 'O'){
                solve(board, row - 1, i, row, col);
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == 'P'){
                    board[i][j] = 'O';
                    continue;
                }
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                    continue;
                }
            }
        }
    }

    public void solve(char[][] board, int r, int c, int row, int col) {
        if (r < 0 || r >= row || c < 0 || c >= col) {
            return;
        }
        if(board[r][c] == 'O'){
            board[r][c] = 'P';
            solve(board, r - 1, c, row, col);
            solve(board, r, c - 1, row, col);
            solve(board, r + 1, c, row, col);
            solve(board, r, c + 1, row, col);
        }
    }
}
