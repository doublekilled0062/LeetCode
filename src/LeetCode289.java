/**
 * 289. 生命游戏
 *
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
 * 每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 * 示例:
 * 输入:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 * 进阶:
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode289 {
    /**
     * 遍历两次 第一次先标记
     * 0 -> 2
     * 1 -> 3
     * 即按位或2
     * 这样最低位是保持不变的
     * 第二次再置回
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = check(board, row, col, i, j);
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if((board[i][j] & 2) != 0){
                    board[i][j] = (board[i][j] & 1) == 1 ? 0 : 1;
                }
            }
        }
    }

    /**
     * 按规则判断 算数量按位与1 取最后一位即可
     * @param board
     * @param i
     * @param j
     * @return
     */
    public int check(int[][] board, int row, int col, int i, int j){
        //确定遍历范围
        int left = Math.max(j - 1, 0);
        int right = Math.min(j + 1, col - 1);
        int up = Math.max(i - 1, 0);
        int down = Math.min(i + 1, row - 1);

        int count = 0;
        for(int k = up; k <= down; k++){
            for(int v = left; v <= right; v++){
                if(k == i && v == j){
                    continue;
                }
                count += board[k][v] & 1;
            }
        }
        if(board[i][j] == 1){
            if(count == 2 || count == 3){
                return 1;
            }else {
                return 3;
            }
        }else {
            if(count == 3){
                return 2;
            }else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode289 leetCode289 = new LeetCode289();
        leetCode289.gameOfLife(new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}});
    }
}
