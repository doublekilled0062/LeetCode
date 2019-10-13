/**
 * 313. 超级丑数
 *
 * 编写一段程序来查找第 n 个超级丑数。
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 *
 * 示例:
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 *
 * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，
 * 前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 *
 * 说明:
 * 1 是任何给定 primes 的超级丑数。
 * 给定 primes 中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
 * 第 n 个超级丑数确保在 32 位有符整数范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode313 {
    /**
     * 多指针一步一步往前算 leetcode264的加强版
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] pos =  new int[len];
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 1;
        while (i < n){
            int index = min(dp, pos, primes, len, dp[i-1]);
            int value = dp[pos[index]] * primes[index];
            pos[index] = pos[index] + 1;
            dp[i] = value;
            i++;
        }

        return dp[n - 1];
    }

    private int min(int[] dp, int[] pos, int[] primes, int len, int preValue){
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++){
            while (dp[pos[i]] * primes[i] <= preValue){
                pos[i] = pos[i] + 1;
            }
            if(dp[pos[i]] * primes[i] < minValue){
                minIndex = i;
                minValue = dp[pos[i]] * primes[i];
            }
        }
        return minIndex;
    }

    /**
     * 优化下代码 复杂度其实差不多
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber1(int n, int[] primes) {
        int len = primes.length;
        int[] pos =  new int[len];
        int[] dp = new int[n];
        dp[0] = 1;
        int value = 0;
        int index = 0;
        for(int i = 1; i < n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < len; j++){
                value = dp[pos[j]] * primes[j];
                if(value == dp[i-1]){
                    pos[j]++;
                    value = dp[pos[j]] * primes[j];
                }
                if(value < dp[i]){
                    dp[i] = value;
                    index = j;
                }
            }
            pos[index]++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        LeetCode313 leetCode313 = new LeetCode313();
        System.out.println(leetCode313.nthSuperUglyNumber1(12, new int[]{2,7,13,19}));
    }
}
