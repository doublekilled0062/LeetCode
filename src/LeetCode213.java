/**
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode213 {
    /**
     * 反证法证明最大值一定在 [0,n-2]和[1,n-1]里面
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int len = nums.length;

        if(len == 1){
            return nums[0];
        }
        int[] dp0 = new int[len - 1];
        int[] dp1 = new int[len - 1];
        //一遍遍历都算出来
        for(int i = 0; i < len; i++){
            if(i == 0){
                dp0[i] = nums[i];
            }else if(i == 1){
                if(i < len - 1){
                    dp0[i] = Math.max(nums[i-1], nums[i]);
                }
                dp1[i-1] = nums[i];
            }else if(i == 2){
                if(i < len - 1){
                    dp0[i] = Math.max(dp0[i-1], nums[i] + dp0[i-2]);
                }
                dp1[i - 1] =  Math.max(nums[i-1], nums[i]);
            }else if (i < nums.length - 1){
                dp0[i] = Math.max(dp0[i-1], nums[i] + dp0[i-2]);
                dp1[i-1] = Math.max(dp1[i-2], nums[i] + dp1[i-3]);
            }else {
                dp1[i-1] = Math.max(dp1[i-2], nums[i] + dp1[i-3]);
            }
        }
        return Math.max(dp0[nums.length - 2], dp1[nums.length - 2]);
    }
}
