import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 例如，给定三角形：
 * [
 *    [2],
 *    [3,4],
 *    [6,5,7],
 *    [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 说明：
 *
 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class LeetCode120 {
    /**
     * 这个题目描述是有问题的 下一行中相邻节点不能往左走
     * 所以用一维数组更新就会好做很多
     * 倒着计算会减少很多边界判断问题
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[triangle.get(row - 1).size()];
        //先放最后一行 倒着计算
        for(int i = 0; i < triangle.get(row - 1).size(); i++){
            dp[i] = triangle.get(row - 1).get(i);
        }
        for(int i = row - 2; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j],dp[j+1]);
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        LeetCode120 leetCode120 = new LeetCode120();
        //[
        //    [-1],
        //    [3,2],
        //    [-3,1,-1]
        // ]
        List<Integer> list1 = new ArrayList<Integer>(){{
            add(-1);
        }};
        List<Integer> list2 = new ArrayList<Integer>(){{
            add(3);
            add(2);
        }};
        List<Integer> list3 = new ArrayList<Integer>(){{
            add(-3);
            add(1);
            add(-1);
        }};
        List<List<Integer>> list = new ArrayList<List<Integer>>(){{
            add(list1);
            add(list2);
            add(list3);
        }};

    }
}
