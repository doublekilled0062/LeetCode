import java.util.Arrays;

/**
 * 164. 最大间距
 *
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 *
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 *
 */
public class LeetCode164 {
    /**
     * 先试试排序的nlog(n)到底超过多少
     * 结果最好成绩4ms 超过95%
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for(int i = 0; i < nums.length - 1; i++){
            max = nums[i+1]-nums[i ]>= max ? nums[i+1] - nums[i] : max;
        }
        return max;
    }

    /**
     * 线性O(n)应该是桶排序
     * 按最大值和最小值和个数分桶
     * 相邻的元素就是小桶的最大值和大桶的最小值之差（略过没有元素的桶）
     * 但是这个实际效率并不高 每次都是5ms 不如直接排序快
     * @param nums
     * @return
     */
    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }

        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        //求出范围
        for (int i = 0; i < nums.length; i++) {
            min = nums[i] < min ? nums[i] : min;
            max = nums[i] > max ? nums[i] : max;
        }

        if (max == min){
            return 0;
        }

        //准备 n + 1个桶
        boolean[] hasNum = new boolean[len + 1];
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];

        for (int i = 0; i < nums.length; i++) {
            int bucketId = (int) ((nums[i] - min + 0L) * len / (max - min));
            mins[bucketId] = hasNum[bucketId] ? Math.min(mins[bucketId], nums[i]) : nums[i];
            maxs[bucketId] = hasNum[bucketId] ? Math.max(maxs[bucketId], nums[i]) : nums[i];
            hasNum[bucketId] = true;
        }
        int res = 0;
        //第一个桶一定不空 最小值在
        int preMax = maxs[0];
        //找最近的非空桶 计算相邻数的差值 大桶最小-小桶最大
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) { // 是非空的
                res = res > mins[i] - preMax ? res : mins[i] - preMax;
                preMax = maxs[i];
            }
        }
        return res;
    }
}
