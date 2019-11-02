import java.util.Arrays;

/**
 * 324. 摆动排序 II
 *
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 示例 1:
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 *
 * 示例 2:
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 *
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wiggle-sort-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode324 {
    /**
     * 先排序试试 [1, 5, 1, 1, 6, 4] -> [1, 1, 1, 4, 5, 6] 倒着插入
     * O(n)的算法应该先用BFPRT算法求出中位数，然后三分数组，比中位数小、与中位数等、比中位数大三段在插入
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length <= 1){
            return;
        }
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        int mid = (nums.length - 1)/2;
        int index = 0;
        for(int i = nums.length - 1; i > (nums.length - 1)/2; i--){
            res[index++] = nums[mid--];
            res[index++] = nums[i];
        }
        if(index == nums.length - 1){
            res[index] = nums[0];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = res[i];
        }
    }

    public static void main(String[] args) {
        LeetCode324 leetCode324 = new LeetCode324();
        leetCode324.wiggleSort(new int[]{4,5,5,6});
        leetCode324.wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
        leetCode324.wiggleSort(new int[]{1, 5, 1, 2, 1, 6, 4});
    }
}
