import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class LeetCode46 {
    /**
     * 两个解法都是递归+回溯 这个解法耗时少一些
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        permute(0, nums, result);
        return result;
    }

    public void permute(int index, int[] nums, List<List<Integer>> result){
        if(index == nums.length - 1){
            List<Integer> list = new LinkedList();
            for(int i = 0; i < nums.length; i++){
                list.add(nums[i]);
            }
            result.add(list);
            return;
        }
        for(int i = index; i < nums.length; i++){
            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
            permute(index + 1, nums, result);
            temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
        }
    }

    /**
     * 主要耗时在于list.add(i, nums[index]); 这块相比数组直接换位效率要低
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        permute2(0, nums, new LinkedList<>(), result);
        return result;
    }

    public void permute2(int index, int[] nums, List<Integer> temp, List<List<Integer>> result){
        if(index >= nums.length){
            result.add(temp);
            return;
        }
        int size = temp.size();
        for(int i = 0; i <= size; i++){
            List<Integer> list = new LinkedList<>(temp);
            list.add(i, nums[index]);
            permute2(index + 1, nums, list, result);
        }
    }

    public static void main(String[] args) {
        LeetCode46 leetCode46 = new LeetCode46();
        leetCode46.permute2(new int[]{1, 2, 3});
    }
}
