import java.util.ArrayList;
import java.util.List;

/**
 * 229. 求众数 II
 *
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: [3]
 *
 * 示例 2:
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode229 {
    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return res;
        }

        int num1 = nums[0];
        int num2 = nums[0];
        int count1 = 0;
        int count2 = 0;

        //遍历数组
        for (int num : nums) {
            if (num == num1) {
                //投1
                count1++;
            } else if (num == num2) {
                //投2
                count2++;
            } else if (count1 == 0) {
                //换1
                num1 = num;
                count1++;
            } else if (count2 == 0) {
                //换2
                num2 = num;
                count2++;
            }else {
                count1--;
                count2--;
            }
        }

        // 再来一遍确认 比如遇到只有两个数 一直在累加
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == num1){
                count1++;
            } else if (num == num2)
                count2++;
        }
        if (count1 > nums.length / 3){
            res.add(num1);
        }

        if (count2 > nums.length / 3){
            res.add(num2);
        }
        return res;
    }
}
