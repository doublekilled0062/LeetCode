import java.util.BitSet;

/**
 * 287. 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class LeetCode287 {
    /**
     * bitset么 显然不符合空间复杂度要求
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        BitSet bitSet = new BitSet(nums.length);
        for(int i = 0; i < nums.length; i++){
            if(bitSet.get(nums[i])){
                return nums[i];
            }else {
                bitSet.set(nums[i]);
            }
        }
        return -1;
    }

    /**
     * 答案厉害 两次快慢针
     * 先转化为 i = num[i] 有环
     * 第二次快慢针 快针速度和慢针一样 一定在入口相遇 按第二次快针和第一次慢针的距离 特点就能看出来 都等于 链长度 + x*环长度
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (true){
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(slow == fast){
                break;
            }
        }
        fast = 0;
        while (true){
            slow = nums[slow];
            fast = nums[fast];
            if(slow == fast){
                return slow;
            }
        }
    }

}
