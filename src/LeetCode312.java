import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 312. 戳气球
 *
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。
 * 注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode312 {
    /**
     * 动态规划 dp[i][j]表示戳破(i+1)->(j-1)范围内的最大值
     * 这样原数组两头加上各加上一个哨兵 dp[0][n+1]就变成了戳破1->n之间球的最大值，即原数组所有球被戳破
     * 则 dp[i][j] = max(dp[i][k] + dp[k][j] + num[i]*num[k]*num[j])
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length + 2;
        int[][] dp = new int[len][len];
        int[] numsNew = new int[len];
        numsNew[0] = 1;
        numsNew[len-1] = 1;
        for(int i = 0; i < len - 2; i++){
            numsNew[i+1] = nums[i];
        }

        //只能按i->j的区间长度慢慢算 至少三个数(j-i=2) 至多len个数(j-i=len-1)
        for (int size = 2; size <= len - 1; size++) {
            for (int i = 0; i <= len - size - 1; i++) {
                int j = i + size;
                for (int k = i + 1; k <= j - 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], numsNew[i] * numsNew[k] * numsNew[j] + dp[i][k] + dp[k][j]);
                }
            }
        }

        return dp[0][len-1];
    }

    public static void main(String[] args) {
        LeetCode312 leetCode312 = new LeetCode312();
        System.out.println(leetCode312.maxCoins(new int[]{3,5,9,7,6,8}));
    }
}
