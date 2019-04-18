/**
 * 85. 最大矩形
 *
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class LeetCode85 {
    /**
     * 复用上一题的代码
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i > 0 && matrix[i][j] == '1'){
                    matrix[i][j] = (char) ('1' + (matrix[i-1][j] - '0'));
                }
            }
            max = Math.max(max, largestRectangleArea(matrix[i]));
        }
        return max;
    }

    public int largestRectangleArea(char[] heights) {
        if ((heights == null) || (heights.length == 0)) return 0;
        final int N = heights.length;
        int[] s = new int[N + 1];
        int i, top = 0, hi, area = 0;
        s[0] = -1;
        for (i = 0; i < N; i++) {
            hi = (heights[i] - '0');
            while ((top > 0) && ((heights[s[top]] - '0') > hi)) {
                area = Math.max(area, (heights[s[top]] - '0') * (i - s[top - 1] - 1));
                top--;
            }
            s[++top] = i;
        }
        while (top > 0) {
            area = Math.max(area, (heights[s[top]] - '0') * (N - s[top -1] - 1));
            top--;
        }
        return area;
    }
}
