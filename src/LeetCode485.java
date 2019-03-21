/**
 * 485. 最大连续1的个数
 *
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class LeetCode485 {

    /**
     * 把Math.max换成二元运算符居然时间没提高
     * 和那个4ms的代码一样 居然我这跑8ms
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                count++;
            }else {
                if(count != 0){
                    max = max > count ? max : count;
                    count = 0;
                }
            }
        }
        return max > count ? max : count;
    }
}
