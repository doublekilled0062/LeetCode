/**
 * 52. N皇后 II
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 *
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class LeetCode52 {
    int total = 0;

    /**
     * 回溯
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        total = 0;
        int[] result = new int[n];
        //只想到这一个标记位的优化
        boolean[] colUsed = new boolean[n];
        //剩下这俩比较考脑子 没想出来
        //col[i]:用于表示i列已经被占用
        //dia1[i]:如果横纵坐标和为i，就表示该位置被占用，看不懂可以自己画一个4皇后，自己将从右上斜向左下的坐标加一下
        //00 01 02 03
        //10 11 12 13
        //20 21 22 23
        //30 31 32 33
        //这里0+1=1+0；0+2=1+1=2+0.....
        //dia1[i]:如果横纵坐标差为i，就表示该位置被占用，看不懂可以自己画一个4皇后，自己将从左上斜向右下的坐标加一下
        //00 01 02 03
        //10 11 12 13
        //20 21 22 23
        //30 31 32 33
        //这里0-2=1-3；0-1=1-2=2-3；.....
        //这里：这样会出现负值，统一加上n-1，使其从0开始
        boolean[] dia1 = new boolean[2 * n - 1];
        boolean[] dia2 = new boolean[2 * n - 1];
        queens(n, 0, result, colUsed, dia1, dia2);
        return total;
    }

    private void queens(int n, int row, int[] result, boolean[] colUsed, boolean[] dia1, boolean[] dia2){
        if(row == n){
            total++;
            return;
        }
        for(int col = 0; col < n; col++){
            if(colUsed[col] || dia1[row + col] || dia2[row - col + n - 1]){
                continue;
            }
            if(check(n, row, col, result)){
                result[row] = col;
                colUsed[col] =  true;
                dia1[row + col] = true;
                dia2[row - col + n - 1] = true;
                queens(n, row + 1, result, colUsed, dia1, dia2);
                colUsed[col] =  false;
                dia1[row + col] = false;
                dia2[row - col + n - 1] = false;
            }
        }
    }

    /**
     * n皇后的效率在与check的优化上
     * @param n
     * @param row
     * @param col
     * @param result
     * @return
     */
    private boolean check(int n, int row, int col, int[] result){
        //行在循环的时候就是是考察过的 所以只需要逐行考察列 左对角 右对角
        int leftSlant = col - 1;        //左对角
        int rightSlant = col + 1;       //右对角
        //和解数独不同的是 大于row的数据是不用考虑的 只需要判断小于row的数据是否符合
//        for(int i = row - 1; i >= 0; i--){
//            if(result[i] == col){
//                return false;
//            }
//            if(leftSlant >= 0){
//                if(result[i] == leftSlant){
//                    return false;
//                }
//            }
//            if(rightSlant >= 0){
//                if(result[i] == rightSlant){
//                    return false;
//                }
//            }
//            leftSlant--;
//            rightSlant++;
//        }
        return true;
    }

    public int totalNQueens2(int n) {
        total = 0;
        if (n < 1) {
            return total;
        }
        dfs(n, 0, 0, 0, 0);
        return total;
    }

    /**
     * 位运算版n皇后 只能算64内的皇后
     * @param n
     * @param row 当前到第几行
     * @param col 列的站坑状态
     * @param leftSlant 左对角线占坑状态
     * @param rightSlant 右对角线占坑状态
     */
    private void dfs(int n, int row, long col, long leftSlant, long rightSlant) {
        //能放的初始状态 这个值就是一个n位的全1
        long bits = (1 << n) - 1;
        if (row >= n) {
            total++;
            return;
        }
        //row:            [ ][ ][ ][ ][ ][ ][ ][*]
        //ls:             [ ][ ][ ][ ][ ][ ][*][ ]
        //rs:             [ ][ ][ ][ ][ ][ ][ ][ ]
        //--------------------------------------
        //row|ls|rs:      [ ][ ][ ][ ][ ][ ][*][*]
        //或完 取反按位与全1 得到能放置的结果 标1位能放置
        long pos = (~(col | leftSlant | rightSlant)) & bits;
        while (pos != 0) {
            //long p = pos & (~pos + 1); 和下面等价 因为计算机是按补码存的
            //意思是取右边第一位1
            //原数  0 0 0 0 1 0 0 0    原数 0 1 0 1 0 0 1 1
            //取反  1 1 1 1 0 1 1 1    取反 1 0 1 0 1 1 0 0
            //加 1  1 1 1 1 1 0 0 0    加1  1 0 1 0 1 1 0 1
            long p = pos & -pos;
            // col | p，将当前列置1，表示记录这次皇后放置的列。
            // (ld + p) << 1，标记当前皇后左边相邻的列不允许下一个皇后放置。
            // (ld + p) >> 1，标记当前皇后右边相邻的列不允许下一个皇后放置。
            // 此处的移位操作实际上是记录对角线上的限制，只是因为问题都化归
            // 到一行网格上来解决，所以表示为列的限制就可以了。显然，随着移位
            // 在每次选择列之前进行，原来N×N网格中某个已放置的皇后针对其对角线
            // 上产生的限制都被记录下来了
            dfs(n, row + 1, col | p, (leftSlant | p) << 1, (rightSlant | p) >> 1);
            // 将pos最右边为1的bit清零
            // 也就是为获取下一次的最右可用列使用做准备，
            // 程序将来会回溯到这个位置继续试探
            pos = pos & pos - 1;
        }
    }

    public static void main(String[] args) {
        LeetCode52 leetCode52 = new LeetCode52();
        System.out.println(leetCode52.totalNQueens(4));
        System.out.println(leetCode52.totalNQueens(5));
        System.out.println(leetCode52.totalNQueens(6));
        System.out.println(leetCode52.totalNQueens(7));
        System.out.println(leetCode52.totalNQueens(8));
        System.out.println(leetCode52.totalNQueens2(9));
        System.out.println(leetCode52.totalNQueens(10));
    }


}
