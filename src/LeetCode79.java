/**
 * 79. 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class LeetCode79 {
    /**
     * 回溯 类似深度优先 优化半天发现性能瓶颈在used要提前创建
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        //先初始化
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(find(board, i, j, word, 0, used)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean find(char[][] board, int row, int col,
                        String chars, int index, boolean[][] used){
        if(index == chars.length()){
            return true;
        }else if(row < 0 || row >= board.length
                || col < 0 || col >= board[0].length
                || used[row][col]
                || board[row][col] != chars.charAt(index)){
            return false;
        }else {
            used[row][col] = true;
            boolean match = (find(board, row - 1, col, chars, index + 1, used)
                    || find(board, row + 1, col, chars, index + 1, used)
                    || find(board, row, col - 1, chars, index + 1, used)
                    || find(board, row, col + 1, chars, index + 1, used)) ? true : false;
            used[row][col] = false;
            return match;
        }
    }


    boolean result = false;
    /**
     * 回溯 类似深度优先
     * @param board
     * @param word
     * @return
     */
    public boolean exist2(char[][] board, String word) {
        if(word == null || word.length() == 0
                || board == null || board.length == 0 || board[0].length == 0){
            return false;
        }
        char[] chars = word.toCharArray();
        boolean[][] used = new boolean[board.length][board[0].length];
        //先初始化
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(result){
                    return result;
                }
                if(board[i][j] == chars[0]){
                    find(board, i, j, chars, 0, used);
                }
            }
        }
        return result;
    }

    public void find(char[][] board, int row, int col,
                     char[] chars, int index, boolean[][] used){
        if(result){
            return;
        }
        if(index == chars.length - 1){
            result = true;
            return;
        }
        used[row][col] = true;
        if(row > 0 && !used[row - 1][col] && board[row - 1][col] == chars[index + 1]){
            find(board, row - 1, col, chars,index + 1, used);
        }
        if(row < board.length - 1 && !used[row + 1][col] && board[row + 1][col] == chars[index + 1]){
            find(board, row + 1, col, chars,index + 1, used);
        }

        if(col > 0  && !used[row][col - 1] && board[row][col - 1] == chars[index + 1]){
            find(board, row, col - 1, chars,index + 1, used);
        }
        if(col < board[0].length - 1 && !used[row][col + 1] && board[row][col + 1] == chars[index + 1]){
            find(board, row, col + 1, chars,index + 1, used);
        }
        used[row][col] = false;
    }

    public static void main(String[] args) {
        LeetCode79 leetCode79 = new LeetCode79();
        System.out.println(leetCode79.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},
        "ABCCED"));
    }
}
