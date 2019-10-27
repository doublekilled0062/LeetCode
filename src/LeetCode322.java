import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode322 {
    /**
     * 动态规划
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0){
            return -1;
        }
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0; i < coins.length; i++){
            if(coins[i] <= amount){
                dp[coins[i]] = 1;
            }
        }
        //如果所有的dp[j-coin[i]]为-1，则没有一种组合
        for(int i = 1; i <= amount; i++){
            int min = -1;
            for(int k = 0; k < coins.length; k++){
                if(i < coins[k]){
                    break;
                }else if(i == coins[k]){
                    min = 1;
                }else {
                    if(dp[i - coins[k]] != -1){
                        min = min == -1 ? dp[i - coins[k]] + 1 : Math.min(min, dp[i - coins[k]] + 1);
                    }
                }
            }
            dp[i] = min;
        }
        return dp[amount];
    }


    private int minCount = -1;

    /**
     * 尽量用大额数值 往回递归
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        // 递归求解
        dfs(amount, 0, coins, coins.length - 1);
        return minCount;
    }

    /**
     *
     * @param amount 当前需要计算的钱数
     * @param count 当前已经算出来的数量
     * @param coins
     * @param index 递归索引
     */
    private void dfs(int amount, int count, int[] coins, int index) {
        // 若剩余金额为0，递归结束
        if (amount == 0) {
            minCount = minCount == -1 ? count : Math.min(minCount, count);
            return;
        }
        // 剪枝 不用再往下算了
        if (index < 0 || (minCount != -1 && count + amount / coins[index] >= minCount)) {
            return;
        }
        int num = amount / coins[index];
        // 能够整除则不需要考虑更小面额硬币
        if (amount == num * coins[index]) {
            minCount =  minCount == -1 ? count + num : Math.min(minCount, count + num);
            return;
        }
        // 循环求解
        for (int i = num; i >= 0; i--) {
            int remain = amount - coins[index] * i;
            dfs(remain, count + i, coins, index - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode322 leetCode322 = new LeetCode322();
        System.out.println(leetCode322.coinChange1(new int[]{6, 7, 1}, 19));
    }
}
