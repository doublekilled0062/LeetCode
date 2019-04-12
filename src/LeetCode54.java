import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 *
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class LeetCode54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0;
        int j = 0;
        int dir = 0; //0左 1下 2右 3上
        int maxRow = row - 1;
        int maxCol = col - 1;
        int minRow = 0;
        int minCol = 0;
        //一共需要遍历这么多次
        for (int num = 0; num < row * col; num++) {
            result.add(matrix[i][j]);
            switch (dir) {
                case 0:
                    //左
                    if (j == maxCol) {
                        //换方向
                        dir = (dir + 1) % 4;
                        minRow++;
                        i++;
                    } else {
                        j++;
                    }
                    break;
                case 1:
                    //下
                    if (i == maxRow) {
                        dir = (dir + 1) % 4;
                        maxCol--;
                        j--;
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    //右
                    if (j == minCol) {
                        dir = (dir + 1) % 4;
                        maxRow--;
                        i--;
                    } else {
                        j--;
                    }
                    break;
                case 3:
                    //上
                    if (i == minRow) {
                        dir = (dir + 1) % 4;
                        minCol++;
                        j++;
                    } else {
                        i--;
                    }
                    break;
            }
        }
        return result;
    }
}
