/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class LeetCode309 {
    // 对于每天来讲：只会出现在这三种状态之中：buy, sell, rest
    // buy[i] 计算今天状态为 buy(已经买入状态) 时的最好收益：
    // buy[i]  = max(rest[i-1]-price, buy[i-1])
    // 买入的状态 =  max（昨天冻结今天买，昨天已买入）
    // sell[i] 记算今天状态为 sell 时的最好收益：
    // sell[i] = max(buy[i-1]+price, sell[i-1])
    // 卖出的状态 = max（昨天为买今天卖出，昨天已卖出）
    // rest[i] 记算今天状态为 rest 时的最好收益：
    // rest[i] = sell[i-1]
    // 转化为
    // buy[i]  = max(sell[i-2] - price, buy[i-1])
    // sell[i] = max(buy[i-1] + price, sell[i-1])
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int buy = -prices[0];
        int sell = 0;
        int preBuy = 0;
        int preSell = 0;
        for(int i = 1; i < prices.length; ++i) {
            preBuy = buy;
            buy = Math.max(preBuy, preSell - prices[i]);
            preSell = sell;
            sell = Math.max(preSell, preBuy + prices[i]);
        }
        return sell;
    }
}
