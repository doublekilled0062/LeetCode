/**
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode283 {
    /**
     * 双指针
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1){
            return;
        }
        int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length){
            while (i < nums.length && nums[i] != 0){
                i++;
                continue;
            }

            j = i + 1 > j ? i + 1 : j;

            while (j < nums.length && nums[j] == 0){
                j++;
                continue;
            }

            if(i < nums.length && j < nums.length){
                //交换i和j的值
                nums[i] = nums[j];
                nums[j] = 0;
                i++;
            }
        }
    }

    /**
     * 优化下写法
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        if(nums == null){
            return;
        }

        for(int noZero = 0, i = 0; noZero < nums.length; noZero++){
            if(nums[noZero] != 0){
                //交换i和zero
                int temp = nums[i];
                nums[i++] = nums[noZero];
                nums[noZero] = temp;
            }
        }
    }

    /**
     * 这个思路简单 但是效率会略慢
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int j = 0;
        // 不是0的放到前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }

        }
        // 后面直接赋值为0
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        LeetCode283 leetCode283 = new LeetCode283();
//        leetCode283.moveZeroes(new int[]{0,1,0,3,12});
//        leetCode283.moveZeroes(new int[]{1,0});
        leetCode283.moveZeroes(new int[]{2,1});
    }
}
