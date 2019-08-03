import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode216 {
    /**
     * 回溯 效率就在下面的三句剪枝
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        int total = ((9 + 9 - k + 1) * k)/2;
        if(total < n){
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(k, n, 0, res, new ArrayList<>());
        return res;
    }

    public void combinationSum(int k, int n, int sum, List<List<Integer>> res, List<Integer> temp){
        if(temp.size() == k){
            if(sum == n){
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        int i = temp.isEmpty() ? 1 : temp.get(temp.size() - 1) + 1;
        //给后面预留不同，所以没比较计算了
        int max = 9 - (k - temp.size()) + 1;
        for(; i <= max; i++){
            //最小值和都超过和 往下就不用算了
            if(getSum(i, i + k - temp.size() - 1) > n - sum){
                continue;
            }
            //最大和都不够 也不用往下算了
            if(getSum(9 - (k - temp.size()) + 1, 9) < n - sum){
                continue;
            }

            if(sum + i > n){
                break;
            }

            temp.add(i);
            combinationSum(k, n, sum + i, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    private int getSum(int start, int end){
        return ((start + end) * (end - start + 1))/2;
    }
    public static void main(String[] args) {
        LeetCode216 leetCode216 = new LeetCode216();
        leetCode216.combinationSum3(3, 10);
    }
}
