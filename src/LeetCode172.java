/**
 * 172. 阶乘后的零
 *
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 */
public class LeetCode172 {
    /**
     * 阶乘的0由 偶数*5 或者10来
     * 归根结底还是5的数量
     * 分为5的倍数1个0 25的倍数2个0
     * 直接累计递加就好
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int res = 0;
        while (n >= 5){
            n = n/5;
            res += n;
        }
        return res;
    }
}
