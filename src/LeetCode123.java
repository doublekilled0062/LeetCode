/**
 * 123. 买卖股票的最佳时机 III
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class LeetCode123 {
    /**
     * 算分隔点 对于一个i表示 在第i天卖出后 两边的最大值
     * 复杂度O(n^2) 刚刚通过
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int max = 0;
        for(int i = 0; i < prices.length; i++){
            //效率慢的原因是这里每次都需要重新遍历计算
            //但是第i+1的值其实是依赖i的值的 不用重复计算
            int max1 = maxProfit(prices, 0, i);
            int max2 = maxProfit(prices, i + 1, prices.length - 1);
            if(max1 < 0){
                max1 = 0;
            }
            if(max2 < 0){
                max2 = 0;
            }
            if(max1 + max2 > max){
                max = max1 + max2;
            }
        }
        return max;
    }

    /**
     * 从起始索引到结束索引的数组的股票最大值，只卖一次
     * @param prices
     * @param start
     * @param end
     * @return
     */
    private int maxProfit(int[] prices, int start, int end) {
        if(start >= end || end >= prices.length){
            return 0;
        }
        int max = 0;
        //表示当天卖出能赚的最大的钱
        int curMin = prices[start];
        for(int i = start + 1; i <= end; i++){
            if(prices[i] < curMin){
                curMin = prices[i];
            }else if(prices[i] - curMin > max){
                max = prices[i] - curMin;
            }
        }
        return max;
    }

    /**
     * 评论区大神
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        /**
         对于任意一天考虑四个变量:
         fstBuy: 在该天第一次买入股票可获得的最大收益
         fstSell: 在该天第一次卖出股票可获得的最大收益
         secBuy: 在该天第二次买入股票可获得的最大收益
         secSell: 在该天第二次卖出股票可获得的最大收益
         分别对四个变量进行相应的更新, 最后secSell就是最大
         收益值(secSell >= fstSell)
         **/
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int p : prices) {
            if(-p > fstBuy){
                fstBuy = -p;
            }
            if(fstBuy + p > fstSell){
                fstSell = fstBuy + p;
            }
            if(fstSell - p > secBuy){
                secBuy = fstSell - p;
            }
            if(secBuy + p > secSell){
                secSell = secBuy + p;
            }
        }
        return secSell;
    }

    /**
     * 动态规划 先从左边找第i天卖出的最大值
     * 再从右找最大值
     * 其实就是最上面算法的优化版 保存了中间结果集
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        //表示从第一天到第i天的数组的最大获利
        int[] dpLeft = new int[prices.length];
        int min = prices[0];
        dpLeft[0] = 0;
        for(int i = 1; i < prices.length; i++){
            if(min > prices[i]){
                min = prices[i];
            }
            dpLeft[i] = Math.max(dpLeft[i - 1], prices[i] - min);
        }

        //倒着算一遍 表示从第i天买入到最后一天之前卖出的最大获利
        int[] dpRight = new int[prices.length];
        int max = prices[prices.length - 1];
        dpRight[prices.length - 1] = 0;
        for(int i = prices.length - 2; i >= 0; i--){
            if(max < prices[i]){
                max = prices[i];
            }
            dpRight[i] = Math.max(dpRight[i + 1], max - prices[i]);
        }

        //再根据 dpLeft 和 dpRight 算 dpLeft[i] + dpRight[i+1] 的最大值
        int res = 0;
        for(int i = 1; i < prices.length - 1; ++i){
            if(dpLeft[i] + dpRight[i+1] > res){
                res = dpLeft[i] + dpRight[i+1];
            }
        }
        //dpLeft[prices.length - 1] 和 dpRight[0]其实是一个意思
        if(dpLeft[prices.length - 1] > res){
            return dpLeft[prices.length - 1];
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode123 leetCode123 = new LeetCode123();
        System.out.println(leetCode123.maxProfit3(new int[]{1,2,4,2,5,7,2,4,9,0}));
    }
}
