import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class LeetCode119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        if(rowIndex == 0){
            pre.add(1);
            return pre;
        }
        for(int i = 0; i < rowIndex; i++){
            List<Integer> result = new ArrayList<>();
            result.add(1);
            for(int j = 1; j <= pre.size() - 1; j++){
                result.add(pre.get(j - 1) + pre.get(j));
            }
            if(rowIndex != 0){
                result.add(1);
            }
            pre = result;
        }
        return pre;
    }

    /**
     * 找规律算法
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> result = new ArrayList();
        long val = 1;
        for(int i = 0; i <= rowIndex; i ++){
            result.add((int)val);
            val = val * (rowIndex - i) / (i + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode119 leetCode119 = new LeetCode119();
        leetCode119.getRow2(3);
    }
}
