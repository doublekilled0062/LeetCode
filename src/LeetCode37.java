/**
 * 37. 解数独
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * <p>
 * 一个数独。
 * [
 * ["5","3","4","6","7","8","9","1","2"],
 * ["6","7","2","1","9","5","3","4","8"],
 * ["1","9","8","3","4","2","5","6","7"],
 * ["8","5","9","7","6","1","4","2","3"],
 * ["4","2","6","8","5","3","7","9","1"],
 * ["7","1","3","9","2","4","8","5","6"],
 * ["9","6","1","5","3","7","2","8","4"],
 * ["2","8","7","4","1","9","6","3","5"],
 * ["3","4","5","2","8","6","1","7","9"]
 * ]
 * <p>
 * 答案被标成红色。
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class LeetCode37 {
    private int[] indexMatrix = new int[81];    //已经处理过的数组 值为1-9
    private boolean[][] rowMatrix = new boolean[9][9];
    private boolean[][] colMatrix = new boolean[9][9];
    private boolean[][] blockMatrix = new boolean[9][9];

    public void solveSudoku(char[][] board) {
        //先用board初始化
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    continue;
                }else {
                    int num = board[i][j] - '1';
                    rowMatrix[i][num] = true;
                    colMatrix[j][num] = true;
                    //判断块是否重复
                    int blockIndex = (i/3) + (j/3)*3;
                    blockMatrix[blockIndex][num] = true;
                }
            }
        }
        solve(board, 0, indexMatrix);
    }

    private void solve(char[][] board, int index, int[] indexMatrix) {
        if(index == 81){
            //结束 把board和indexMatrix组合
            buildResult(board, indexMatrix);
            return;
        }
        int i = index / 9;
        int j = index % 9;
        if(board[i][j] != '.'){
            solve(board, index + 1, indexMatrix);
        }else {
            for(int k = 1; k <= 9; k++){
                indexMatrix[index] = k;
                if(isValidSudoku(board, index, indexMatrix)){
                    solve(board, index + 1, indexMatrix);
                }
            }
        }
    }

    private boolean isValidSudoku(char[][] board, int index, int[] indexMatrix) {
        boolean[][] rowMatrixTemp = new boolean[9][9];
        boolean[][] colMatrixTemp = new boolean[9][9];
        boolean[][] blockMatrixTemp = new boolean[9][9];
        for(int k = index; k >= 0; k--){
            int i = k / 9;
            int j = k % 9;
            if(board[i][j] != '.'){
                //board原始数据不用管
                continue;
            }else {
                int target = indexMatrix[k] - 1;
                if(rowMatrix[i][target] == true || rowMatrixTemp[i][target] == true){
                    return false;
                }else {
                    rowMatrixTemp[i][target] = true;
                }
                if(colMatrix[j][target] == true || colMatrixTemp[j][target] == true){
                    return false;
                }else {
                    colMatrixTemp[j][target] = true;
                }
                int blockIndex = (i/3) + (j/3)*3;
                if(blockMatrix[blockIndex][target] == true || blockMatrixTemp[blockIndex][target] == true){
                    return false;
                }else {
                    blockMatrixTemp[blockIndex][target] = true;
                }
            }
        }
        return true;
    }

    private void buildResult(char[][] board, int[] indexMatrix){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    board[i][j] = (char)(indexMatrix[i * 9 + j] + '0');
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode37 leetCode37 = new LeetCode37();
        leetCode37.solveSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        });
    }
}
