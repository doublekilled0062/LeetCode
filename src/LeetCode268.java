/**
 * 268. 缺失数字
 *
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode268 {
    /**
     * 交换
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int i = 0;
        int len = nums.length;
        int res = len;
        while (i < len){
            if(nums[i] == i){
                i++;
                continue;
            }else if(nums[i] == len){
                res = i;
                i++;
                continue;
            }else {
                //i和num[i]索引下的换位置
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }

        return res;
    }

    /**
     * 还可以位运算
     * @param nums
     * @return
     */
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        int res = len;
        for(int i = 0; i < nums.length; i++){
            res ^= nums[i] ^ i;
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode268 leetCode268 = new LeetCode268();
        System.out.println(leetCode268.missingNumber1(new int[]{9,6,4,2,3,5,7,0,1}));
    }
}
