/**
 * 137. 只出现一次的数字 II
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class LeetCode137 {
    /**
     * 用ones记录到当前计算的变量为止，二进制1出现“1次”（mod 3 之后的 1）的数位。
     * 用twos记录到当前计算的变量为止，二进制1出现“2次”（mod 3 之后的 2）的数位。
     * 当ones和twos中的某一位同时为1时表示二进制1出现3次，此时需要清零。
     * 即用二进制模拟三进制计算。最终ones记录的是最终结果。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        int xthrees = 0;
        for(int i = 0; i < nums.length; i++) {
            twos |= (ones & nums[i]);
            ones ^= nums[i];
            xthrees = ~(ones & twos);
            ones &= xthrees;
            twos &= xthrees;
        }
        return ones;
    }
}
