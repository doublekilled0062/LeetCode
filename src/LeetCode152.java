/**
 * 152. 乘积最大子序列
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */
public class LeetCode152 {
    /**
     * 二维动态规划 会超时
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++){
            for(int j = i; j < len; j++){
                if(i == j){
                    dp[i][j] = nums[i];
                }else {
                    dp[i][j] = dp[i][j-1] * nums[j];
                }
                if(dp[i][j] > max){
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    /**
     * 渐进式dp 开始来一个数组更新乘积 开始是一个数 来一个负数加一个数
     * 遇到0 就清空结果 重新计算
     * 结果又超时 负数太多 接近O(n^2)
     * @param nums
     * @return
     */
    public int maxProduct1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = 1;
        int start = 0;
        int end = 0;
        for(int i = 0; i < len; i++){
            if(nums[i] == 0){
                start = 0;
                end = 0;
                dp[0] = 1;
                if(max < 0){
                    max = 0;
                }
            }else {
                for(int j = start; j <= end; j++){
                    dp[j] = dp[j] * nums[i];
                    max = max > dp[j] ? max : dp[j];
                }
                if(nums[i] < 0){
                    dp[++end] = 1;
                }
            }
        }
        return max;
    }

    /**
     * 先找出0来 然后用0两边的分别计算
     * 2ms 99%
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        //有上面判断 max可以初始为0
        int max = 0;
        int len = nums.length;
        int start = 0;
        int total = 0;
        while (start < len){
            if(nums[start] == 0){
                start++;
                continue;
            }
            int end = start;
            while (end < len){
                if(nums[end] != 0){
                    total = total == 0 ? nums[end] : total * nums[end];
                    end++;
                }else {
                    int temp = maxProductNoZero(nums, start, end - 1, total);
                    max = max > temp ? max : temp;

                    start = end + 1;
                    end = start;
                    total = 0;
                }
            }
            //最后的边界 再算一次
            if(end >= len){
                int temp = maxProductNoZero(nums, start, len -1, total);
                max = max > temp ? max : temp;
                start = end;
            }
        }
        return max;
    }

    /**
     * 不含0的序列最大乘积和
     * @param nums
     * @param start
     * @param end
     * @param total 目前数组start到end的乘积
     * @return
     */
    public int maxProductNoZero(int[] nums, int start, int end, int total) {
        if(start == end){
            return total;
        }

        if(total > 0){
            //整数不用管 肯定最大
            return total;
        }

        //奇数个负数，最大值的乘积肯定是去掉某个负数 这样可以遍历到这个负数排除这个求两边的积
        int max = total;
        int product = 1;
        for(int i = start; i <= end; i++){
            int temp = nums[i];
            if(nums[i] < 0){
                if(i == start || i == end){
                    temp = total/nums[i];
                }else {
                    //这个负数 有两边的值需要比较
                    int remain = total/(product * nums[i]);
                    temp = product > remain ? product : remain;
                }
            }
            max = max > temp ? max : temp;
            product *= nums[i];
        }
        return max;
    }


    /**
     * 看了解答的2ms的
     * @param nums
     * @return
     */
    public int maxProduct3(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }

        int len = nums.length;
        int result = nums[0];

        int[] max = new int[len];
        int[] min = new int[len];

        min[0] = nums[0];
        max[0] = nums[0];

        for(int i = 1; i < len; ++i){
            if(nums[i] >= 0){
                max[i] = Math.max(max[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(min[i - 1] * nums[i], nums[i]);
            }else{
                max[i] = Math.max(min[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(max[i - 1] * nums[i], nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        LeetCode152 leetCode152 = new LeetCode152();
        System.out.println(leetCode152.maxProduct2(new int[]{0, -2, 0}));
        System.out.println(leetCode152.maxProduct2(new int[]{2,3,-2,4}));
        System.out.println(leetCode152.maxProduct2(new int[]{-2,0,-1}));
    }
}
