import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class LeetCode188 {
    /**
     * 模仿leetcode123 来两个k的数组
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length <= 1 || k < 1){
            return 0;
        }

        //这步剪枝很重要
        if(k >= prices.length/2){
            return getTotalMax(prices);
        }

        int[] dpBuy = new int[k];
        Arrays.fill(dpBuy, Integer.MIN_VALUE);
        int[] dpsell = new int[k];

        for(int p : prices) {
            dpBuy[0] = dpBuy[0] > -p ? dpBuy[0] : -p;
            dpsell[0] = dpsell[0] > dpBuy[0] + p ? dpsell[0] : dpBuy[0] + p;

            for(int i = 1; i < k; i++){
                dpBuy[i] = dpBuy[i] > dpsell[i-1] - p ? dpBuy[i] : dpsell[i-1] - p;
                dpsell[i] = dpsell[i] > dpBuy[i] + p ? dpsell[i] : dpBuy[i] + p;
            }
        }
        return dpsell[k-1];
    }

    public int getTotalMax(int[] prices){
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }
    //local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润
    //global[i][j]为在到达第i天时最多可进行j次交易的最大利润
    //diff = prices[i] – prices[i – 1]
    //local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
    //global[i][j] = max(local[i][j], global[i - 1][j])，
    public int maxProfit2(int k, int[] prices) {
        if(prices == null || prices.length <= 1 || k < 1){
            return 0;
        }

        int days = prices.length;
        if(k >= prices.length/2){
            return getTotalMax(prices);
        }

        int[][] local = new int[days][k + 1];
        int[][] global = new int[days][k + 1];

        for (int i = 1; i < days ; i++) {
            int diff = prices[i] - prices[i - 1];

            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1], local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }

        return global[days - 1][k];
    }

    /**
     * 可以压缩成1维 但是需要倒着计算
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit3(int k, int[] prices) {
        if(prices == null || prices.length <= 1 || k < 1){
            return 0;
        }

        int days = prices.length;
        if(k >= prices.length/2){
            return getTotalMax(prices);
        }

        int[] local = new int[k+1];
        int[] global = new int[k+1];

        for (int i = 1; i < days ; i++) {
            int diff = prices[i] - prices[i - 1];

            for (int j = k; j > 0; j--) {
                local[j] = Math.max(global[j - 1], local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }

        return global[k];
    }

    public static void main(String[] args) {
        LeetCode188 leetCode188 = new LeetCode188();
        System.out.println(leetCode188.maxProfit3(2, new int[]{2,4,1}));
        System.out.println(leetCode188.maxProfit3(2, new int[]{3,2,6,5,0,3}));
        System.out.println(leetCode188.maxProfit3(10, new int[]{1,2,4,2,5,7,2,4,9,0}));
    }
}
