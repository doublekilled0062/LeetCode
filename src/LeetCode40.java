import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 */
public class LeetCode40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null){
            return result;
        }
        Arrays.sort(candidates);
        combination(candidates, candidates.length, 0, 0, target, new ArrayList<>(), result);
        return result;
    }

    /**
     * 比39题需要多注意的地方
     * 1.不能重复使用某个下标的值 但是可以使用下标不同但是值相同的元素
     * 2.遍历时候要排除下标不同但是值组合一样的
     * 3.可以模仿三数之和（16题）和4数之和（18题）的去重方式
     * @param candidates
     * @param len
     * @param index
     * @param sum
     * @param target
     * @param resultTemp
     * @param result
     */
    public void combination(int[] candidates, int len, int index, int sum, int target, List<Integer> resultTemp, List<List<Integer>> result) {
        if(index >= len){
            return;
        }
        if(candidates[index] + sum > target){
            return;
        }
        for(int i = index; i < len; i++){
            if(i > index && candidates[i] == candidates[i - 1]){
                continue;
            }
            if(candidates[i] + sum <= target){
                List<Integer> temp = new ArrayList<>(resultTemp);
                temp.add(candidates[i]);
                if(candidates[i] + sum < target){
                    combination(candidates, len, i + 1, candidates[i] + sum, target, temp, result);
                }else {
                    result.add(temp);
                }
            }
        }
    }
}
