import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class LeetCode39 {

    /**
     * 递归+回溯
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null){
            return result;
        }
        Arrays.sort(candidates);
        combination(candidates, candidates.length, 0, 0, target, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯
     * @param candidates
     * @param len
     * @param index
     * @param target
     * @param resultTemp
     */
    private void combination(int[] candidates, int len, int index, int sum, int target, List<Integer> resultTemp, List<List<Integer>> result){
        if(candidates[0] > target){
            return;
        }
        for(int i = index; i < len; i++){
            if(candidates[i] + sum <= target){
                List<Integer> temp = new ArrayList<>(resultTemp);
                temp.add(candidates[i]);
                if(candidates[i] + sum == target){
                    result.add(temp);
                }else {
                    combination(candidates, len, i, candidates[i] + sum, target, temp, result);
                }
            }else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode39 leetCode39 = new LeetCode39();
        leetCode39.combinationSum(new int[]{2,3,6,7}, 7);
    }
}
