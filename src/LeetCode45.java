/**
 * 45. 跳跃游戏 II
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 *
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 */
public class LeetCode45 {
    /**
     * 动态规划 从前往后算 类似广度优先 然而直接算超时了
     * 所以采取深度策略 执行用时 : 6 ms, 在Jump Game II的Java提交中击败了86.22% 的用户
     * 第二次优化第二层for循环倒着计算 类似贪心算法从最远的到次远的 2ms 100%
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        for(int i = 0; i < len; i++){
            int step = nums[i];
            if(step >= len - 1 - i){
                //直接能走到
                if(i == len - 1){
                    return dp[i];
                }
                return 1 + dp[i];
            }
            for(int j = i + step; j >= i + 1; j--){
                if(dp[j] == 0){
                    dp[j] = dp[i] + 1;
                }else {
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
                if(nums[j] + j >= len - 1){
                    return dp[j] + 1;
                }
            }
        }
        return dp[nums.length - 1];
    }

    /**
     * 贪心算左右跨度的
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if(nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums[0];
        int step = 0;
        for(int i = left; i < nums.length; i++){
            right = Math.max(i + nums[i], right);
            if(right >= nums.length - 1) {
                return step + 1;
            }
            if(i == left){
                step++;
                left = right;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        LeetCode45 leetCode45 = new LeetCode45();
        System.out.println(leetCode45.jump(new int[]{2, 3, 1, 1, 4}));
    }
}
