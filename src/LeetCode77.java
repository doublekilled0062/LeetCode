import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 */
public class LeetCode77 {
    /**
     * 回溯 上机效率很低 原来有一句最关键的剪枝
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(0, n, k, new ArrayList<>(), result);
        return result;
    }

    public void combine(int index, int n, int k, List<Integer> temp, List<List<Integer>> result){
        if(k == 0){
            //找到
            result.add(new ArrayList<>(temp));
            return;
        }
        //剪枝关键 比如 1-5 挑3个只需要遍历1、2、3
        for(int i = index + 1; i <= n - k + 1; i++){
            temp.add(i);
            combine(i, n, k-1, temp, result);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        LeetCode77 leetCode77 = new LeetCode77();
        leetCode77.combine(4,2);
    }
}
