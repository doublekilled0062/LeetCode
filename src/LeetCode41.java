/**
 * 41. 缺失的第一个正数
 *
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 *
 */
public class LeetCode41 {
    /**
     * 类似原地排序 让num[i] = i + 1 找第一个不对应的数 如果都对应 就是len+1
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if(nums == null){
            return 1;
        }
        int len = nums.length;
        if(len == 0){
            return 1;
        }
        for(int i = 0; i < nums.length; i++){
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] - 1 != i){
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        LeetCode41 leetCode41 = new LeetCode41();
//        System.out.println(leetCode41.firstMissingPositive(new int[]{1,2,0}));
//        System.out.println(leetCode41.firstMissingPositive(new int[]{1,3,0}));
//        System.out.println(leetCode41.firstMissingPositive(new int[]{1,1,3,0}));
        System.out.println(leetCode41.firstMissingPositive(new int[]{3,4,-1,1}));
    }
}
