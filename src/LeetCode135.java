import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.Arrays;

/**
 * 135. 分发糖果
 *
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class LeetCode135 {
    /**
     * 从前往后遍历如果遇到递增的就从1开始增
     * 从后往前开始遍历遇到递增的就从1开始增
     * 后往前的时候要更新 第一次计算的值如果比之前的小就加1 大就不管
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }
        int len = ratings.length;
        int[] nums = new int[len];
        //每人最少发一个糖
        Arrays.fill(nums, 1);
        //从前往后
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            }
        }
        //从后往前
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && nums[i] <= nums[i + 1]) {
                nums[i] = nums[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }
        int len = ratings.length;
        int[] nums = new int[len];
        //每人最少发一个糖
        Arrays.fill(nums, 1);
        //从前往后
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            }
        }
        //从后往前
        int sum = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && nums[i] <= nums[i + 1]) {
                nums[i] = nums[i + 1] + 1;
            }
            sum+= nums[i];
        }
        return sum;
    }

    public int candy3(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int len = ratings.length;
        int[] nums = new int[len];
        //从前往后
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            }
        }
        //从后往前
        int sum = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && nums[i] <= nums[i + 1]) {
                nums[i] = nums[i + 1] + 1;
            }
            sum+= nums[i];
        }
        //每人最少发一个糖
        return sum + len;
    }

    public static void main(String[] args) {
        LeetCode135 leetCode135 = new LeetCode135();
        System.out.println(leetCode135.candy3(new int[]{1,0,2}));
        System.out.println(leetCode135.candy3(new int[]{1,2,2}));
        System.out.println(leetCode135.candy3(new int[]{1,2,2,1}));
    }
}
