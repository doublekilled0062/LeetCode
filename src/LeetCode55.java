/**
 * 55. 跳跃游戏
 * <p>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class LeetCode55 {
    public boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return true;
        }
        int right = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            right = Math.max(i + nums[i], right);
            if (right >= nums.length - 1) {
                return true;
            }
            if (nums[i] == 0 && i == right && right < nums.length - 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从后往前算
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int nowIndex = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if ((nowIndex - i) <= (nums[i])) {
                nowIndex = i;
            }
        }
        if (nowIndex == 0) {
            return true;
        } else {
            return false;
        }
    }
}
