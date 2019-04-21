import java.util.*;

/**
 * 51. N皇后
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例:
 * 输入: 4
 * 输出: [
 *         [".Q..",  // 解法 1
 *          "...Q",
 *          "Q...",
 *          "..Q."],
 *         ["..Q.",  // 解法 2
 *         "Q...",
 *         "...Q",
 *         ".Q.."]
 *      ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class LeetCode51 {
    /**
     * 借用leetcode52
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> totalResult = new ArrayList<>();
        int[] result = new int[n];
        //只想到这一个标记位的优化
        boolean[] colUsed = new boolean[n];
        boolean[] dia1 = new boolean[2 * n - 1];
        boolean[] dia2 = new boolean[2 * n - 1];
        queens(n, 0, result, colUsed, dia1, dia2, totalResult, genQueueMap(n));
        return totalResult;
    }

    private void queens(int n, int row, int[] result, boolean[] colUsed, boolean[] dia1, boolean[] dia2, List<List<String>> totalResult, Map<Integer, String> map){
        if(row == n){
            genTotoQueueList(totalResult, map, result);
            return;
        }
        for(int col = 0; col < n; col++){
            if(colUsed[col] || dia1[row + col] || dia2[row - col + n - 1]){
                continue;
            }
            result[row] = col;
            colUsed[col] =  true;
            dia1[row + col] = true;
            dia2[row - col + n - 1] = true;
            queens(n, row + 1, result, colUsed, dia1, dia2, totalResult, map);
            colUsed[col] =  false;
            dia1[row + col] = false;
            dia2[row - col + n - 1] = false;
        }
    }

    /**
     * 每一个合法的摆放
     * @param totalResult
     * @param map
     * @param result
     */
    private void genTotoQueueList(List<List<String>> totalResult, Map<Integer, String> map, int[] result){
        List<String> temp = new ArrayList<>();
        for(int i = 0; i < result.length; i++){
            temp.add(new String(map.get(result[i])));
        }
        totalResult.add(temp);
    }

    /**
     * 初始化
     * @param n
     * @return
     */
    private Map<Integer, String> genQueueMap(int n){
        Map<Integer, String> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[i] = 'Q';
            map.put(i, new String(chars));
        }
        return map;
    }
}
