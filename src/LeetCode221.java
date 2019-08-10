/**
 * 221. 最大正方形
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出:
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode221 {
    /**
     * 常规解法 稍微优化 每次都算除了这个点外的一圈
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int max = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == '0'){
                    continue;
                }
                //计算以matrix[i][j]为左上点的最大矩形面积
                boolean flag = true;
                int len = 1;
                //以i,j为顶点 一圈一圈算
                while (flag){
                    if(i + len >= row || j + len >= col || matrix[i + len][j + len] == '0'){
                        flag = false;
                        continue;
                    }

                    //算第i+len行到j+len列
                    boolean colFlag = true;
                    for(int k = j; k < j + len; k++){
                        if(matrix[i + len][k] == '0'){
                            colFlag = false;
                            break;
                        }
                    }
                    boolean rowFlag = true;
                    if(colFlag){
                        for(int k = i; k < i + len; k++){
                            if(matrix[k][j+len] == '0'){
                                rowFlag = false;
                                break;
                            }
                        }
                    }
                    if(rowFlag && colFlag){
                        len++;
                    }else {
                        flag = false;
                    }
                }
                max = Math.max(max, len * len);
            }
        }
        return max;
    }

    /**
     * 动态规划 以i,j 为右下顶点的正方体边长 是以 i-1,j  i-1,j-1  i,j-1为右下角顶点的正方体边长+1
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int max = 0;

        //第一列
        for(int i = 0; i < row; i++){
            if(matrix[i][0] == '1'){
                dp[i][0] = 1;
                max = 1;
            }

        }
        //第一行
        for(int j = 1; j < col; j++){
            if(matrix[0][j] == '1'){
                dp[0][j] = 1;
                max = 1;
            }
        }

        for(int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max*max;
    }

    /**
     * 上机6ms 其实是常规解法的一个优化 每次都从max+1开始验证
     * 但是这个解法再遇到 1 密集的情况下 效率其实并不高
     * 例如这种
     * 1 1 0 0 0 0 0
     * 1 1 1 0 0 0 0
     * 1 1 1 0 0 0 0
     * 1 1 1 1 0 0 0
     * 1 1 1 1 0 0 0
     * 1 1 1 1 0 0 0
     * 1 1 1 1 0 0 0
     * 1 1 1 1 1 0 0
     * 1 1 1 1 1 0 0
     * 1 1 1 1 1 0 0
     * 1 1 1 1 1 0 0
     * 1 1 1 1 1 0 0
     *
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                while (allOne(matrix, i, j, max + 1)) {
                    max = max + 1;
                }
            }
        }
        return max * max;
    }

    public boolean allOne(char[][] matrix, int x, int y, int len) {
        if (x + len > matrix.length || y + len > matrix[0].length) {
            return false;
        }
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (matrix[i][j] != '1') {
                    return false;
                }
            }
        }
        return true;
    }
}
