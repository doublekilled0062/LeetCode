import java.util.*;

/**
 * 47. 全排列 II
 * <p>
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class LeetCode47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        permuteUnique(0, nums, result);
        return result;
    }

    /**
     * set判重 出现过的就不换位了
     * @param index
     * @param nums
     * @param result
     */
    public void permuteUnique(int index, int[] nums, List<List<Integer>> result) {
        if (index == nums.length - 1) {
            List<Integer> list = new LinkedList();
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
            }
            result.add(list);
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                if(i == index){
                    permuteUnique(index + 1, nums, result);
                }else {
                    int temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                    permuteUnique(index + 1, nums, result);
                    temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
            }
        }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(nums, res, path, new boolean[nums.length]);
        return res;
    }

    /**
     * 搜了个效率高点的答案
     * 主要是如何判断重复，方法就是对与重复的元素循环时跳过递归的调用只对第一个未被使用的进行递归，那么这一次的结果将会唯一出现在结果集中，而后重复的元素将会被略过；
     * 如果第一个重复元素还没在当前结果中，那么我们就不需要进行递归。
     * @param nums
     * @param res
     * @param path
     * @param used
     */
    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] used){
        if (nums.length == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]){
                    continue;
                }

                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                used[i] = true;
                path.add(nums[i]);
                dfs(nums, res, path, used);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode47 leetCode47 = new LeetCode47();
        leetCode47.permuteUnique(new int[]{1, 1, 2});
    }

}
