/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode279 {
    /**
     * 动态规划
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            int min = dp[i - 1];
            for(int j = 1; j * j <= i; j++){
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }

        return dp[n];
    }

    /**
     * 数学问题 甘拜下风
     * @param n
     * @return
     */
    public int numSquares1(int n) {
        if (n <= 0) {
            return 0;
        }
        // 首先使用拉格朗日四平方和定理求解：
        // 1、任何一个整数都可以表示为不超过4个数的平方和
        // 2、推论：当n可以表示为4个数的平方和时，n = (4^a)*(8b+7)
        // 根据上述推论，首先判断结果是否是4
        int tmp = n;
        while ((tmp & 3) == 0) {  // tmp % 4 == 0
            tmp >>= 2;          // tmp = tmp / 4
        }
        if (((tmp + 1) & 7) == 0) {  // tmp % 8 == 7
            return 4;
        }
        // 判断是否是1
        tmp = (int)Math.sqrt(n);
        if (tmp * tmp == n) {
            return 1;
        }
        // 判断是否是2
        tmp = (int)Math.sqrt(n / 2);
        for (int i = 1; i <= tmp; i++) {
            int j = (int)Math.sqrt(n - i*i);
            if (i*i + j*j == n) {
                return 2;
            }
        }
        return 3;
    }

    public static void main(String[] args) {
        LeetCode279 leetCode279 = new LeetCode279();
        System.out.println(leetCode279.numSquares(1));
        System.out.println(leetCode279.numSquares(2));
        System.out.println(leetCode279.numSquares(3));
        System.out.println(leetCode279.numSquares(4));
        System.out.println(leetCode279.numSquares(12));
        System.out.println(leetCode279.numSquares(13));
        System.out.println(leetCode279.numSquares(48));
    }
}
