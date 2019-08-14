/**
 * 260. 只出现一次的数字 III
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 *
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode260 {
    /**
     * 主要思路是把两个数分开 转化成两个leetcode136的形式
     * 可以按某一位为0或者为1分开
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int i : nums){
            xor ^= i;
        }

        //可以把最右的一位作为分割标志位 xor & (-xor)效果一样 01100 & 10111 = 00100
        int mask = xor & (~xor + 1);

        int[] res = new int[2];
        for (int i : nums) {
            if ((i & mask) == 0){
                //== 0、 == mask 两种结果
                res[0] ^= i;
            } else{
                res[1] ^= i;
            }
        }
        return res;
    }
}
