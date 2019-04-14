import java.math.BigInteger;

/**
 * 62. 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class LeetCode62 {
    /**
     * 一个是填格子
     * 一个是用公式走m+n-2步 挑m-1或者n-1的组合 即C(m+n-2, min(m-1, n-1))
     * 这个算法在100内可能会溢出 所以得用BigInteger
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int k = m + n - 2;
        int v = Math.min(m-1, n-1);
        BigInteger res = getFactorial(k);
        BigInteger res2 = getFactorial(v).multiply(getFactorial(k-v));
        res = res.divide(res2);
        return res.intValue();
    }

    private BigInteger getFactorial(int n){
        BigInteger res = new BigInteger("1");
        for(int i = 1; i <= n; i++){
            res = res.multiply(new BigInteger(String.valueOf(i)));
        }
        return res;
    }

    /**
     * BigInteger 编译不过 只能用别的算
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int k = m + n - 2;
        int v = Math.min(m-1, n-1);
        long res = 1;
        for(int i = 0; i < v; i++){
            res *= k;
            k--;
        }
        for(int i = 0; i < v; i++){
            res /= i + 1;
        }
        return (int)res;
    }

    /**
     * 填格子
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j]+dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        LeetCode62 leetCode62 = new LeetCode62();
        System.out.println(leetCode62.uniquePaths2(7, 3));
    }
}
