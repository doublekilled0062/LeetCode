/**
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class LeetCode121 {
    /**
     * 传统方法二维动态规划
     * 这个方法超时了
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int[][] dp = new int[prices.length][prices.length];
        int max = 0;
        for(int i = 0; i < dp.length - 1; i++){
            for(int j = i + 1; j < dp.length; j++){
                dp[i][j] = prices[j] - prices[i];
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 标准一维动态规划
     * 写出这个来其实leetcode123 就不用开始用O(n^2)那个复杂度的解法了
     * 直接会跳到两次一维动态规划
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int[] dp = new int[prices.length];
        int curMin = prices[0];
        for(int i = 1; i < dp.length; i++){
            if(curMin > prices[i]){
                curMin = prices[i];
            }
            if(prices[i] - curMin > dp[i-1]){
                dp[i] = prices[i] - curMin;
            }else {
                dp[i] = dp[i-1];
            }
        }
        return dp[prices.length - 1];
    }

    /**
     * 肯定需要优化成一维 4ms 37.5%
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int max = 0;
        //表示当天卖出能赚的最大的钱
        int curMin = prices[0];
        for(int i = 1; i < prices.length; i++){
            max = Math.max(max, prices[i] - curMin);
            curMin = Math.min(prices[i], curMin);
        }
        return max;
    }

    /**
     * 优化一下上面 不用max函数 用if判断会快
     * 和题解答案一样都是2ms和3ms来回晃
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int max = 0;
        //表示当天卖出能赚的最大的钱
        int curMin = prices[0];
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < curMin){
                curMin = prices[i];
            }else if(prices[i] - curMin > max){
                max = prices[i] - curMin;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode121 leetCode121 = new LeetCode121();
        System.out.println(leetCode121.maxProfit4(new int[]{7,1,5,3,6,4}));
        System.out.println(leetCode121.maxProfit4(new int[]{7,6,4,3,1}));
    }
}
