/**
 * 11. 盛最多水的容器
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *       |       8                   8
 *       |       |*******************|*******7
 *       |       |   6               |       |
 *       |       |   |       5       |       |
 *       |       |   |       |   4   |       |
 *       |       |   |       |   |   |   3   |
 *       |       |   |   2   |   |   |   |   |
 *       |   1   |   |   |   |   |   |   |   |
 *       |   |   |   |   |   |   |   |   |   |
 *       ----------------------------------------------->
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。
 * 在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class LeetCode11 {
    /**
     * O(n^2)复杂度 超时了
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        int[][] result = new int[height.length][height.length];
        for(int i = 0; i < height.length; i++){
            for (int j = i + 1; j < height.length; j++){
                result[i][j] = (j - i) * Math.min(height[i], height[j]);
                if(result[i][j] > max){
                    max = result[i][j];
                }
            }
        }
        return max;
    }

    /**
     * 还好我机智 马上想出了这个方法
     * 数组的思路就那几个 遍历 双指针 分治 动态规划
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j){
            int area = (j - i) * Math.min(height[i], height[j]);
            if(area > max){
                max = area;
            }
            if(height[i] >= height[j]){
                j--;
            }else {
                i++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode11 leetCode11 = new LeetCode11();
        System.out.println(leetCode11.maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
